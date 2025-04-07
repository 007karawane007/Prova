library(RPostgreSQL)

set.seed(3)

user <- readline(prompt = "Username: ")
password <- readline(prompt = "Password: ")

drv <- dbDriver("PostgreSQL")
con <- dbConnect(
                drv, 
                dbname = "progetto_basi", 
                host = "localhost", 
                user=user,
                password=password
                )

dbGetQuery(con, "SET search_path TO public;")

# popolamento competenze
v_competenza <- readLines("data_for_samples/competenze.txt")
competenze_df <- data.frame(
                            codice_competenza = sample(10000:99999, 82, replace = F),
                            nome_competenza = sample(v_competenza, 82, replace = F)
                            )

dbWriteTable(
            con,
            name="competenza",
            value=competenze_df,
            append=T,
            row.names=F
            )


#popolamento dipartimento
v_dipartimento <- readLines("data_for_samples/dipartimenti.txt")
dipartimenti_df <- data.frame(
                            nome_dipartimento = sample(v_dipartimento, 30,replace=F),
                            telefono = sample(1000000000:9999999999, 30, replace=F),
                            numero_dipendenti = 0
                            )

dbWriteTable(
            con,
            name="dipartimento",
            value=dipartimenti_df,
            append = T,
            row.names=F
            )


#popolamento città
v_città <- readLines("data_for_samples/città.txt")
città_df <- data.frame(
                    nome = sample(v_città, 51, replace=F),
                    numero_abitanti=sample(10000:1000000, 51, replace=T)
                    ) 

dbWriteTable(
            con,
            name="città",
            value=città_df,
            append = T,
            row.names=F
            )


#popolamento fornitore
v_fornitori <- readLines("data_for_samples/fornitori.txt")
v_indirizzi <- readLines("data_for_samples/indirizzi.txt")
fornitori_df <- data.frame(
                        nome = sample(v_fornitori, 250, replace=F),
                        indirizzo = sample(v_indirizzi, 250, replace=T)
                        )

dbWriteTable(
            con,
            name="fornitore",
            value=fornitori_df,
            append = T,
            row.names=F
            )


#popolamento progetto
temp_città <- dbGetQuery(con, "SELECT nome FROM città")
temp_città <- temp_città$nome
progetti_df <- data.frame(
                        numero_progetto=sample(10000:99999, 500, replace=F),
                        budget=sample(seq(300:1000000), 500, replace=T),
                        città=sample(temp_città, 500, replace=T)
                        )

dbWriteTable(
            con,
            name="progetto",
            value=progetti_df,
            append = T,
            row.names=F
            )


#popolamento impiegato
v_nomi <- readLines("data_for_samples/nomi.txt")
v_cognomi <- readLines("data_for_samples/cognomi.txt")
v_qualifiche <- readLines("data_for_samples/qualifiche.txt")
temp_dip <- dbGetQuery(con, "SELECT nome_dipartimento FROM dipartimento")
temp_dip <- temp_dip$nome_dipartimento
impiegati_df <- data.frame(
                        matricola=sample(10000:99999, 10000, replace=F),
                        nome=sample(v_nomi, 10000, replace=T),
                        cognome=sample(v_cognomi, 10000, replace=T),
                        data_assunzione=sample(seq(as.Date('1980/01/01'), Sys.Date(), by="day"), 10000, replace=T),
                        qualifica=sample(v_qualifiche, 10000, replace=T),
                        dipartimento=sample(temp_dip, 10000, replace=T),
			            coniuge=rep(NA, 10000),
			            data_matrimonio=rep(NA, 10000)
			            )

dbWriteTable(
            con,
            name="impiegato",
            value=impiegati_df,
            append = T,
            row.names=F
            )


#popolamento lingua
lingue <- c("italiano", "inglese", "spagnolo", "francese", "tedesco", "portoghese", "russo", "cinese", "giapponese", "coreano", "arabo")
lingue_df <- data.frame(
                        lingua=lingue
                        ) 

dbWriteTable(
            con,
            name="lingua",
            value=lingue_df,
            append = T,
            row.names=F
            )


#popolamento laurea
v_lauree <- readLines("data_for_samples/lauree.txt")
lauree_df <- data.frame(
                    tipo_laurea=sample(v_lauree, 48, replace=F)
                    )

dbWriteTable(
            con,
            name="laurea",
            value=lauree_df,
            append = T,
            row.names=F
            )


#popolamento fornisce
temp_forn <- dbGetQuery(con, "SELECT nome FROM fornitore")
temp_forn <- temp_forn$nome
temp_dip <- dbGetQuery(con, "SELECT nome_dipartimento FROM dipartimento")
temp_dip <- temp_dip$nome_dipartimento
fornisce_df <- data.frame(
                        dipartimento=sample(temp_dip, 10000, replace=T),
                        fornitore=sample(temp_forn, 10000, replace=T)
                        )

dbWriteTable(
            con,
            name="fornisce",
            value=unique(fornisce_df),
            append = T,
            row.names=F
            )


#popolamento laureato_in
temp_lau <- dbGetQuery(con, "SELECT tipo_laurea FROM laurea")
temp_lau <- temp_lau$tipo_laurea
temp_imp <- dbGetQuery(con, "SELECT matricola FROM impiegato")
temp_imp <- temp_imp$matricola
laureato_in_df <- data.frame(
                            impiegato=sample(temp_imp, 8000, replace=T),
                            laurea=sample(temp_lau, 8000, replace=T)
                            )

dbWriteTable(
            con,
            name="laureato_in",
            value=unique(laureato_in_df),
            append = T,
            row.names=F
            )


#popolamento parla
temp_segr <- dbGetQuery(con, "SELECT matricola FROM impiegato WHERE qualifica='Segretario'")
temp_segr <- temp_segr$matricola
temp_lingue <- dbGetQuery(con, "SELECT lingua FROM lingua")
probabilità <- c(0.20, 0.15, 0.10, 0.11, 0.08, 0.09, 0.05, 0.07, 0.07, 0.04, 0.04)
parla_df <- data.frame(
                    impiegato=sample(temp_segr, 600, replace=T),
                    lingua=sample(temp_lingue$lingua, 600, replace=T, prob=probabilità)
                    )

dbWriteTable(
            con,
            name="parla",
            value=unique(parla_df),
            append = T,
            row.names=F
            )


#popolamento possiede_competenza
temp_comp <- dbGetQuery(con, "SELECT codice_competenza FROM competenza")
temp_comp <- temp_comp$codice_competenza
possiede_competenza_df <- data.frame(
                                    impiegato=sample(temp_imp, 16000, replace=T),
                                    competenza=sample(temp_comp, 16000, replace=T)
                                    )


#popolamento competenza_usata_nel_progetto
temp_pro <- dbGetQuery(con, "SELECT numero_progetto FROM progetto")
temp_pro <- temp_pro$numero_progetto
competenza_usata_nel_progetto_df <- data.frame(
                                            impiegato=possiede_competenza_df$impiegato,
                                            competenza=possiede_competenza_df$competenza,
                                            progetto=sample(temp_pro, 16000,replace=T)
                                            )


dbBegin(con)
tryCatch({
    dbWriteTable(con,
             name="possiede_competenza",
             value=unique(possiede_competenza_df),
             append = T,
             row.names=F)

    dbWriteTable(con,
             name="competenza_usata_nel_progetto",
             value=unique(competenza_usata_nel_progetto_df),
             append = T,
             row.names=F)

    dbCommit(con)
}, error = function(e) {
    message(e$message)
    dbRollback(con)
})


# inserimento coppie di dipendenti sposati tramite transazioni con controlli sui vincoli differiti

# estrazione delle coppie di impiegati coniugati e della data di matrimonio per ciascuna coppia
matricole <- dbGetQuery(con, "SELECT matricola FROM impiegato")
matr_1 <- matricole$matricola[grep("^1", matricole$matricola)]
matr_1 <- sample(matr_1, 200, replace=FALSE)
matr_9 <- matricole$matricola[grep("^9", matricole$matricola)]
matr_9 <- sample(matr_9, 200, replace=FALSE)
data_matrimonio <- sample(seq(as.Date('1970/01/01'), Sys.Date(), by="day"), 200, replace=T)
data_matrimonio <- as.character(data_matrimonio)
coppie_coniugate <- cbind(matr_1, matr_9, data_matrimonio)


# inizio transazione
dbBegin(con)

for (i in 1:nrow(coppie_coniugate)) {
    matr_1 <- as.numeric(coppie_coniugate[i, 1])
    matr_9 <- as.numeric(coppie_coniugate[i, 2])
    data_matrimonio <- coppie_coniugate[i, 3]

    query1 <- sprintf("UPDATE impiegato SET coniuge = %d, data_matrimonio = '%s' WHERE matricola = %d;",
                        matr_9, data_matrimonio, matr_1)
    query2 <- sprintf("UPDATE impiegato SET coniuge = %d, data_matrimonio = '%s' WHERE matricola = %d;",
                        matr_1, data_matrimonio, matr_9)

    dbExecute(con, query1)
    dbExecute(con, query2)
}

# fine transazione
dbCommit(con)



# ---------------------------------------------     SEZIONE GRAFICI     ---------------------------------------------


# GRAFICO 1: "numero di dipendenti per dipartimento" (istogramma)
df_dip <- dbGetQuery(con, "SELECT nome_dipartimento, numero_dipendenti, n_fornitori
                        FROM dipartimento JOIN (SELECT dipartimento, count(*) AS n_fornitori 
                                                FROM fornisce
                                                GROUP BY dipartimento) AS forniture
                                            ON dipartimento.nome_dipartimento = forniture.dipartimento")

mat <- t(as.matrix(df_dip[, c("numero_dipendenti", "n_fornitori")]))

barplot(mat,  beside = TRUE, names.arg = df_dip$nome_dipartimento, ylim=c(0,400), las=2, cex.names = 0.5,
        col = c("blue", "red"), legend.text = c("Dipendenti", "Fornitori"),
        main = "Dipendenti e Fornitori per Dipartimento", ylab = "Conteggio"
        )
x11()


# GRAFICO 2: "percentuale di lingue parlate dai segretari" (grafico a torta)
df_lingue <- dbGetQuery(con, "SELECT lingua, count (*) as valore
                            FROM parla
                            GROUP BY lingua"
                        )


df_lingue$Percentuale <- round(df_lingue$valore / sum(df_lingue$valore) * 100, 1)
etichette <- paste(df_lingue$lingua, df_lingue$Percentuale, "%")

pie(df_lingue$valore, labels = etichette, col = rainbow(nrow(df_lingue)),
    main = "Lingue parlate dai segretari"
    )
x11()


# GRAFICO 3: "numero impiegati assunti per anno" (grafico a linee)
df_assunzioni <- dbGetQuery(con, "SELECT data_assunzione FROM impiegato WHERE data_assunzione < '2025/01/01'")
assunzioni_per_anno <- as.numeric(format(df_assunzioni$data_assunzione, "%Y"))
assunzioni <- table(assunzioni_per_anno)

plot(assunzioni, "l", ylim=c(0,500), xlab = "Anno", ylab = "Assunzioni", main = "Numero assunzioni per anno (2025 escluso)")


dbDisconnect(con)