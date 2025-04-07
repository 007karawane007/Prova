protected class AutoCarro{
    private int quantitaDiCarico;
    private String tipo;
    private String targa;

    protected Autocarro(){
        quantitaDiCarico = 0;
        tipo = "";
        targa = "";
    }

    /*
     * Metodo oggetto Autocarro
     * @precond: tipoMerce != NULL && quantitaDiMerce >=0
     * @postcond: Modifica le variabili di istanza del chiamante
     */
    protected void impostaTipoMerceQuantitaMerce(String tipoMerce, int quantitaDiMerce){
        assert(tipoMerce!=null);
        this.quantitaDiCarico = quantitaDiMerce;
        this.tipo = tipoMerce;
        this.targa = generaTarga();//Metodo che genera una targa per il mezzo ... To do
    }

    /*
     * Metodo classe Autocarro
     * @precond: this != NULL
     * @postcond: Restituisce la quantità di carico del chiamante
     */
    protected int carico(){
        return this.quantitaDiCarico;
    }

    /*
     * Metodo classe Autocarro
     * @precond: this != NULL
     * @postcond: Restituisce il tipo di carico del chiamante
     */
    protected String tipoCarico(){
        return this.tipo;
    }

    /*
     * Metodo classe Autocarro
     * @precond: this != NULL && quantitàDiMerce >=0
     * @postcond: Modifica il valore di quantità di carico del chiamante
     */
    protected void aggiungiMerce(int quantitaDiMerce){
        this.quantitaDiCarico = this.quantitaDiCarico + quantitaDiMerce;
    }
    
    /*
     * Metodo classe Autocarro
     * @precond: this != NULL && quantitàDiMerce >=0
     * @postcond: Modifica il valore di quantità di carico del chiamante
     */
    protected void rimuoviMerce(int quantitaDiMerce){
        this.quantitaDiCarico = this.quantitaDiCarico - quantitaDiMerce;
    }

    /*
     * Metodo di confronto fra classi Autocarro
     * @precond: this != null && camion != null
     * @postcond: Restituisce vero se i due oggetti Autocarro hanno stesso tipo di merce, stessa targa altrimenti falso
     */
    protected boolean equals(AutoCarro camion){
        return (this.tipo.equals(camion.tipo) && this.targa.equals(camion.targa));
    }
}