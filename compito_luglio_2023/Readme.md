# Compito della biglietteria eventi del 26/7


## Schema generale di classi e loro metodi
(fatto all'inizio, come base da cui partire; le classi implementate sono
un po' diverse. )

Artista: nome

Cliente: nome, contatti (email, tel)

Evento: nome, locazione, stato (previsto, in-corso, concluso)
 annulla(): per un evento previsto o in-corso, lo mette chiuso

Registro eventi : elenco di eventi, tutti indipendentemente dal loro stato
  memorizza anche la eventuale prevendita di un evento (se c'e')
  pubblica(): crea un evento, ne definisce locazione e repliche, lo mette attivo
  findPresale(evento)

Locazione: nome, indirizzo, elenco tipo posti, capacità max di ogni fascia, e le varie fasce
 queryCapacità()
 queryFasce()

Replica: una delle repliche dell'evento
 evento, data della replica, per ogni fascia i posti ancora disponibili

Biglietto: cliente, prevendita, modalità pagamento, data acquisto, importo

Biglietto richiesto: la richiesta fatta da un cliente
 evento, replica, tipo posto, prezzo

Registro vendite: elenco biglietti venduti
 add()
 find()

Prevendita: rappresenta la prevendita di un determinato evento con le sue repliche
 evento, data apertura, prezzo posti per ogni tipo e replica, 
 stato (prevista, attiva, chiusa), num posti disponibili per ogni tipo
 attiva(): apre la prevendita e la rende attiva
 Prevendita(): che setta i vari campi
 postiLiberi(fascia, replica): dice quanti ce n'è
 acquistaBiglietto()
 prezzo(fascia, replica): ritorna il prezzo


Agenzia: raggruppa ed espone i metodi pubblici 
 contiene: elenco clienti, storico eventi, registro vendite, registro eventi
 calcolaRendiconto(): che produce i dati che alimentano un report
   dato un periodo, estrae numero biglietti e ricavi
   => ReportData
 acquistaBiglietti(): dati num biglietti desiderati e loro caratteristiche (evento, replica, tipo posto)
  restituisce N biglietti, o tutti o nessuno, procede all'acquisto, aspetta che il pagamento vada a buon fine

ReportData: contiene lista di eventi e repliche fatte, il num di biglietti venduti per ogni tipo di posto, 
  e i ricavi corrispondenti
   {nomeEvento -> {data -> {fascia -> [num biglietti, ricavi]}}}
 add(evento, data, fascia, tot biglietti, ricavo): aggiunge quella voce