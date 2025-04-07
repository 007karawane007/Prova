# Come avrei valutato questo elaborato


## Completezza

Il codice così com'è non funziona dato che mancano dei pezzi
e ci sono errori sintattici e forse anche semantici.

Però se andiamo a vedere il testo d'esame e quello che chiede vediamo che 
ci sono la classe `EventiLive` che fornisce l'API necessaria e i metodi per 
fare i rendiconti e per acquistare biglietti.

Il metodo `buyTickets()` consente di prenotare un insieme di biglietti e 
di proceder all'acquisto. Ovviamente non si sono implementati i meccanismi
di addebito con banche o simili. Ma il metodo è ragionevolmente completo e
si vede che delega ad altre classi altre operazioni. Bene!

Il metodo `generateReportByEvent` consente di estrarre informazioni sulle vendite e
produrle in un oggetto che poi può essere facilmente usato per produrre vari tipi di 
rapporti (ad es. testuali, o CSV, o in JSON).
Quel metodo è implementato completamente e anche lui delega operazioni 
ad altre classi. Bene!

Sono stati previsti, ma non implementati per mancanza di tempo, gli altri metodi per il 
reporting: `generateReportByEventEdition` e `generateReportByEventEditionAndSeatType`.
Ma lo schema sarebbe simile al precedente. Anche il contratto. Bene!

Il `Main` contiene due esempi di come si possono usare questi metodi. Bene!

## Single Responsibility Principle

Se andiamo a vedere in profondità vediamo che l'implementazione ha coperto anche
altre classi. Ad es. il `buyTickets` delega a `PreSale` la verifica della
disponibilità di posti e l'acquisto vero e proprio (dato che è `Presale` a 
sapere il prezzo di ciascun posto e le diverse fasce).
Delega anche a `TicketRegister` la gestione della memorizzazione dei biglietti venduti. 
Quindi qs sono responsabilità che `Presale` non si prende. Bene!

Vediamo anche che `Presale` gestisce l'acquisto, calcolando il prezzo del biglietto
e in qualche modo nascondendo i dettagli della complessa transazione da fare con la
banca (dettagli non implementati).

Analogamente per la gestione dei rapporti. Il metodo `generateReportByEvent` delega a
`ReportData` il compito di memorizzare le informazioni. Esso si limita a dire
quali sono le info da memorizzare. Bene.

## Contratti

Se andiamo a vedere i principali metodi implementati e una parte di quelli 
solamente specificati ma non implementati (per mancanza di tempo)
scopriamo che sono corredati di commenti che spiegano quali sono le precondizioni
che devono essere vere affinché il metodo produca i risultati attesi e 
quali sono i risultati attesi (le postcondizioni).

Sono commenti non banali. Ad es. `EventiLive.buyTickets` prevede che gli
venga fornita una lista di biglietti richiesti (con le varie informazioni necessarie)
, un cliente, la `Presale` a cui si è interessati. Dalla segnatura si vede 
che devono essere non-null e dal commento si desumono altre precondizioni.
Si vede anche che il metodo può emettere delle eccezioni in certe condizioni. 

In molti casi ci sono metodi non implementati, ma per far capire che si 
aveva le idee chiare su cosa avrebbero dovuto fare, si sono descritti i
relativi contratti.
Bene!

## Tipi di dati astratti

Se andiamo a vedere per tutte le classi implementate si è data una descrizione della 
mission, cioè del ruolo che diamo a quella classe.

E per molte sono stati descritti anche lo stato astratto, lo stato concreto 
(quando non ovvio dal codice) e in certi casi invarianti di rappresentazione. 
Bene!

## Clean code

Se andiamo a vedere la qualità del codice scritto scopriamo che i vari metodi hanno dei body
molto piccoli, facili da capire al volo.
Il codice ha dei nomi di metodi, di parametri, di classi, di variabili che sono
evocativi del loro significato.

Scopriamo che vengono usati stream, filtri e iteratori per lavorare su collezioni
di dati.

Scopriamo anche che viene posta attenzione ai test di uguaglianza, ad es. in 
`Ticket`. Cose che vengono sfruttate in `TicketRegister`.

Sarebbe stato meglio se invece di emettere `Exception`, per i metodi che lanciano eccezioni,
si fossero individuate delle 
classi di eccezione più specifiche. Questo difetto costa la lode!

## Pattern di design

Per questo problema non si sono state usati pattern complicati. Il caro e vecchio
`Factory` può essere sempre usato ed è sttao introdotto anche qui.
Si sarebbe potuto benissimo fare a meno, ma tant'è: male non fa. Bene!

# Conclusione finale

È ovvio che un problema come quello dato non lo si risolve decentemente 
in 2 ore. Quindi un certo grado di incompletezza e di sub-ottimalità nella qualità
dell'elaborato ci sarà sempre.

L'elaborato qui presentato varrebbe 30/30.

L'esame di POO è volto a far capire al docente se si sono imparati
dei principi di design orientato agli oggetti e se si è fatta un po' di 
pratica di programmazione in Java.

Se uno non ha fatto pratica con le varie classi e librerie di Java, e non ha 
dimestichezza nel metterle insieme, in 2 ore riesce solamente ad abbozzare 3-4 
classi usate come contenitore di dati e una classe usata come
coordinatore dove ci sono tutti gli algoritmi. In alcuni casi con complessi 
annidamenti di loop e if.

Inoltre non si sarà ricordato di scrivere contratti (pensando di farlo alla 
fine - ma non ci sarà tempo per farlo) e tantomeno di specificare gli ADT.

E se non si è fatta pratica, non verranno in mente idee su come usare pattern di
design.

Il risultato è un cattivo design, codice non clean, probabilemente molto
incompleto rispetto alle specifiche.