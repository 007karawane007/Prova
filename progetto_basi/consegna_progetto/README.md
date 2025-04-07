Istruzioni per l'implementazione fisica del database da terminale 
con l'utilizzo di 'psql - PostgreSQL interactive terminal':


1.  da terminale, spostarsi nella cartella contenente i file:
    
    - schema_relazionale.sql
    - query.sql
    - popolamento.r

    e la sottocartella:
    
    - data_for_samples/


2.  effettuare l'accesso a PostgreSQL utilizzando username e password:

    $ psql -U <username>
    > password: <password>


3.  creare un nuovo database con nome 'progetto_basi'
    (ATTENZIONE: il nome dev'essere esattamente 'progetto_basi' affinché il successivo popolamento avvenga correttamente):

    $ CREATE DATABASE progetto_basi;


4.  collegarsi al database 'progetto_basi':

    $ \c progetto_basi


5.  definire le tabelle:

    $ \i schema_relazionale.sql


6.  per popolare le tabelle, aprire un altro terminale nella cartella come al passo 1,
    lanciare una sessione R:

    $ R

    eseguire il programma per il popolamento del database:
    (ATTENZIONE: lo script richiede username e password, inserire gli stessi con cui si è effettuato l'accesso a PostgreSQL)

    $ source("popolamento.r")


7.  per eseguire le query, da PostgreSQL, eseguire da terminale il comando:

    $ \i query.sql