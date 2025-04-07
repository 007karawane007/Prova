/*
    QUERY 1:	Tutti i dipartimenti in cui lavorano almeno 6 impiegati laureati in informatica
			    e al massimo 8 impiegati laureati in lettere
*/
CREATE VIEW l_info AS
SELECT nome_dipartimento AS dipartimento, COUNT (*) AS num_inf
FROM (dipartimento JOIN impiegato ON nome_dipartimento = impiegato.dipartimento) JOIN laureato_in ON matricola = laureato_in.impiegato
WHERE laurea = 'Ingegneria Informatica - Computer Engineering'
GROUP BY nome_dipartimento;

CREATE VIEW l_lettere AS
SELECT nome_dipartimento AS dipartimento, COUNT (*) AS num_lett
FROM (dipartimento JOIN impiegato ON nome_dipartimento = impiegato.dipartimento) JOIN laureato_in ON matricola = laureato_in.impiegato
WHERE laurea = 'Lettere - Literature'
GROUP BY nome_dipartimento;

SELECT dipartimento
FROM l_info
WHERE num_inf >= 6

INTERSECT

SELECT dipartimento
FROM l_lettere
WHERE num_lett <= 8;


/*
    QUERY 2:	Tutti gli impiegati che lavorano solo in progetti con sede a Milano
*/
SELECT DISTINCT impiegato 
FROM competenza_usata_nel_progetto c
WHERE NOT EXISTS (SELECT *
                FROM competenza_usata_nel_progetto c1, progetto p
                WHERE c1.impiegato = c.impiegato AND
                    c1.progetto = p.numero_progetto AND
                    p.città <> 'Milano');


/*
    QUERY 3:	Numero di impiegati non sposati che afferiscono ad un dipartimento
			    con meno di 325 dipendenti 
*/
SELECT COUNT (*) AS impiegati_nubili_celibi
FROM impiegato i
WHERE coniuge IS NULL and EXISTS(SELECT *
								FROM dipartimento d
								WHERE d.nome_dipartimento = i.dipartimento AND
										d.numero_dipendenti < 325
);



/* 
    QUERY 4:    Numero di impiegati e di fornitori per dipartimento 
*/
SELECT nome_dipartimento, numero_dipendenti, n_fornitori
FROM dipartimento JOIN (SELECT dipartimento, count(*) AS n_fornitori 
                        FROM fornisce
                        GROUP BY dipartimento) AS forniture
                    ON dipartimento.nome_dipartimento = forniture.dipartimento;


/*
    QUERY 5:    La distribuzione in percentuale delle lingue parlate dai segretari
*/
SELECT lingua, count (*) as valore
FROM parla
GROUP BY lingua;


/*
    QUERY 6:    Numero di impiegati assunti per anno
*/
SELECT data_assunzione 
FROM impiegato 
WHERE data_assunzione < '2025/01/01';