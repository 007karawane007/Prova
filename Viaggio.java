import java.util.Date;

protected class Viaggio{

    private AutoCarro mezzo;
    private Percorso percorso;
    private Date dataPartenza;
    private Date dataArrivo;

    /*
     * Costruttore oggetto Viaggio
     * @postcond: Creo un oggetto di tipo Viaggio
     */
    protected Viaggio(){
        mezzo = new AutoCarro();
        percorso = new Percorso();
        dataPartenza = new Date();
        dataArrivo = new Date();
    }

    /*
     * Metodo classe Viaggio
     * @precond:merce != null && carico >= 0
     * @postcond: Modifico la variabile di istanza mezzo in base ai parametri inseriti
     */
    protected void creaAutocarro(String merce, int carico){
        assert(merce != null);
        this.mezzo.impostaTipoMerceQuantitaMerce(merce, carico);
    }

    /*
     * Metodo classe Viaggio
     * @precond:partenza,arrivo != null &&  percorso != null
     * @postcond: Modifico la variabile di istanza mezzo in base ai parametri inseriti
     */
    protected void creaPercorso(String[] percorso){
        assert(percorso != null);
        this.percorso.impostaTratta(percorso);
    }

    protected void impostaDataPartenza(String data){
        this.dataPartenza = new Date(data);
    }

    protected void impostaDataArrivo(String data){
        this.dataArrivo = new Date(data);
    }

    protected AutoCarro camion(Viaggio v){
        return v.mezzo;
    }
    protected Percorso tratta(Viaggio v){
        return v.percorso;
    }
    protected Date data(Viaggio v){
        return v.dataArrivo;
    }

    /*
     * Metodo di confronto tra oggetti di tipo Viaggio
     * @precond: v != null
     * @postcond: restituisce vero se le due variabili di istanza del chiamate sono uguali a quelle del chiamato
     */
    protected boolean equals(Viaggio v){
        if(this.mezzo.equals(v.mezzo) && this.percorso.equals(v.percorso)){
            return true;
        }else{
            return false;
        }
    }
}