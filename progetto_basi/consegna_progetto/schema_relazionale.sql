SET	search_path TO public;

CREATE TABLE dipartimento (
	nome_dipartimento text,
	telefono bigint,
	numero_dipendenti bigint,
	PRIMARY KEY (nome_dipartimento),
	check (telefono is NOT NULL and numero_dipendenti is NOT NULL)
);

CREATE TABLE competenza (
	codice_competenza integer,
	nome_competenza text,
	PRIMARY KEY (codice_competenza),
	UNIQUE(nome_competenza)
);

CREATE TABLE città (
	nome text,
	numero_abitanti integer,
	PRIMARY KEY (nome)
);

CREATE TABLE fornitore (
	nome text,
	indirizzo text,
	PRIMARY KEY (nome)
);

CREATE TABLE progetto (
	numero_progetto integer,
	budget float,
	città text NOT NULL,
	PRIMARY KEY (numero_progetto),
	FOREIGN KEY (città) REFERENCES città 
		on update cascade 
		on delete no action 
);

CREATE TABLE impiegato (
	matricola integer,
	nome text NOT NULL,
	cognome text NOT NULL,
	data_assunzione date NOT NULL,
	qualifica text,
	dipartimento text NOT NULL,
	coniuge integer UNIQUE,
	data_matrimonio date,
	PRIMARY KEY (matricola),
	FOREIGN KEY (dipartimento) REFERENCES dipartimento
		on update cascade
		on delete no action,
	FOREIGN KEY (coniuge) REFERENCES impiegato
		on update cascade
		on delete set null
);

CREATE TABLE lingua (
	lingua text,
	PRIMARY KEY (lingua)
);

CREATE TABLE laurea (
	tipo_laurea text,
	PRIMARY KEY (tipo_laurea)
);

CREATE TABLE competenza_usata_nel_progetto (
	impiegato integer,
	competenza integer,
	progetto integer,
	PRIMARY KEY (impiegato, competenza, progetto),
	FOREIGN KEY (impiegato) REFERENCES impiegato
		on update cascade
		on delete no action,
	FOREIGN KEY (competenza) REFERENCES competenza
		on update cascade
		on delete set null,
	FOREIGN KEY (progetto) REFERENCES progetto
		on update cascade
		on delete no action
);

CREATE TABLE fornisce (
	dipartimento text,
	fornitore text,
	PRIMARY KEY (dipartimento, fornitore),
	FOREIGN KEY (dipartimento) REFERENCES dipartimento
		on update cascade
		on delete no action,
	FOREIGN KEY (fornitore) REFERENCES fornitore
	on update cascade
		on delete no action
);

CREATE TABLE possiede_competenza (
	impiegato integer,
	competenza integer,
	PRIMARY KEY (impiegato, competenza),
	FOREIGN KEY (impiegato) REFERENCES impiegato
		on update cascade
		on delete no action,
	FOREIGN KEY (competenza) REFERENCES competenza
		on update cascade
		on delete no action
);

CREATE TABLE laureato_in (
	impiegato integer,
	laurea text,
	PRIMARY KEY (impiegato, laurea),
	FOREIGN KEY (impiegato) REFERENCES impiegato
		on update cascade
		on delete no action,
	FOREIGN KEY (laurea) REFERENCES laurea
		on update cascade
		on delete no action
);

CREATE TABLE parla (
	impiegato integer,
	lingua text,
	PRIMARY KEY (impiegato, lingua),
	FOREIGN KEY (impiegato) REFERENCES impiegato
		on update cascade
		on delete no action,
	FOREIGN KEY (lingua) REFERENCES lingua
		on update cascade
		on delete no action
);

/*
	------------------------------------	TRIGGER		------------------------------------
*/

/*
TRIGGER NUMERO 1:	Devo garantire che solo gli impiegati con la qualifica di 'segretario' possano essere
					presenti nella relazione PARLA -->  ON INSERT | UPDATE
*/
create or replace function controlla_qualifica_parla_trigger_function()
returns trigger
language plpgsql 
as $$
begin
    if EXISTS (
        SELECT * 
        FROM impiegato I
        WHERE I.matricola = NEW.impiegato and I.qualifica = 'Segretario'
    ) then
        return new;
    else
        raise exception 'Impiegato % non è un segretario', NEW.impiegato;
    end if;
end;
$$;

create trigger controlla_qualifica_parla_trigger
before insert or update
on parla
for each row
execute function controlla_qualifica_parla_trigger_function();


/*
TRIGGER NUMERO 2:	Devo garantire che se un impiegato è sposato con un altro, 
					allora anche il coniuge deve risultare sposato con l'impiegato --> ON UPDATE
*/
create or replace function controlla_coniuge_update_trigger_function()
returns trigger
language plpgsql 
as $$
begin
    if NEW.coniuge is NULL then
        if ( ((SELECT I.coniuge FROM impiegato I where I.matricola = OLD.coniuge) = NULL ) OR
             ((SELECT I.coniuge FROM impiegato I where I.matricola = OLD.coniuge) <> new.matricola) ) then
             return new;
        else  
            raise exception 'Errore: l''ex coniuge % ha ancora come coniuge %', OLD.coniuge, NEW.matricola;
        end if;
    else        
        if NEW.coniuge is not NULL then 
            if (SELECT I.coniuge FROM impiegato I WHERE I.matricola = NEW.coniuge) = NEW.matricola then
                return new;
            else
                raise exception 'Errore: il coniuge % non risulta sposato con %', NEW.coniuge, NEW.matricola;
			end if;
        end if;
    end if;
end;
$$;

create constraint trigger controlla_coniuge_update_trigger
after update
on impiegato
DEFERRABLE INITIALLY DEFERRED
for each row
execute function controlla_coniuge_update_trigger_function();


/*
TRIGGER NUMERO 3:	Devo garantire che se un impiegato è sposato con un altro, 
					allora anche il coniuge deve risultare sposato con l'impiegato --> ON INSERT
*/
create or replace function controlla_coniuge_insert_trigger_function()
returns trigger
language plpgsql 
as $$
begin
    if NEW.coniuge is NULL then
        if NOT EXISTS (SELECT * FROM impiegato I where I.matricola <> NEW.matricola and I.coniuge = NEW.matricola) then
             return new;
        else  
            raise exception 'Errore: impiegato % risulta gia coniugato con un altro impiegato', NEW.impiegato;
        end if;
    else        
		if (SELECT I.coniuge FROM impiegato I WHERE I.matricola = NEW.coniuge) = NEW.matricola then
			return new;
		else
			raise exception 'Errore: impiegato % risulta gia coniugato con un altro impiegato', NEW.coniuge;
		end if;
    end if;
end;
$$;

create constraint trigger controlla_coniuge_insert_trigger
after insert
on impiegato
DEFERRABLE INITIALLY DEFERRED
for each row
execute function controlla_coniuge_insert_trigger_function();


/*
TRIGGER NUMERO 4:	Devo garantire che ogni competenza di un impiegato sia utilizzata in almeno un progetto --> ON INSERT
*/
create or replace function controlla_competenze_impiegato_trigger_function()
returns trigger
language plpgsql 
as $$
begin
    if EXISTS (
        SELECT *
        FROM competenza_usata_nel_progetto cup    
        WHERE NEW.impiegato = cup.impiegato AND NEW.competenza = cup.competenza
    ) then   
        return new;
    else
        raise exception 'Errore: non esiste un progetto di % in cui usa la competenza %', NEW.impiegato, NEW.competenza;  
    end if;
end;
$$;

create constraint trigger controlla_competenze_impiegato_trigger
after insert or update
on possiede_competenza
DEFERRABLE INITIALLY DEFERRED
for each row
execute function controlla_competenze_impiegato_trigger_function();


/*
TRIGGER NUMERO 5:	Aggiornamento del numero di dipendenti in un dipartimento --> ON INSERT
*/
create or replace function incremento_numero_dipendenti()
returns trigger
language plpgsql
as $$
begin
	UPDATE dipartimento
	SET numero_dipendenti = numero_dipendenti + 1
	WHERE nome_dipartimento = new.dipartimento;	
	return NULL;
end;
$$;

create trigger incremento_numero_dipendenti_trigger
after insert
on impiegato
for each row
execute function incremento_numero_dipendenti();