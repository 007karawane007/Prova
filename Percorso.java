protected class Percorso{

    private String tratta[];

    /*
     * Costruttore oggetto Percorso
     */
    protected Percorso(){
        tratta = new String[64];
    }

    /*
     * Metodo classe Percorso
     * @precond: this != null && percorso != null
     * @postcond: Modifica la tratta del chiamante con il percorso scelto
     */
    protected void impostaTratta(String percorso[]){
        assert(percorso != null);
        for(int i=0; i<percorso.length; i++){
            this.tratta[i]=percorso[i];
        }
    }

    /*
     * Metodo di confronto tra oggetti Percorso
     * @precond: this != null && perc != null
     * @postcond: Ritorna True se la data di arrivo, la data di partenza e la tratta sono uguali altrimenti falso
     */
    protected boolean equals(Percorso perc){
        for(int i=0; i<perc.length; i++){
            if(this.tratta[i] != perc.tratta[i]){
                return false;
            }
        }
        return true;
    }
}