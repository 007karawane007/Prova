import java.util.Date;

public class AziendaTrasporti {

    protected enum merce{
        ColliSfusi,
        Liquidi,
        Gas,
        DaFrigo
    }

    //Viaggio v[] = new Viaggio[64]; nel main

    public Viaggio[] prenota(String[] tratta, String tipoMerce, int kg, String dataPartenza, String dataArrivo, Viaggio v[]) throws Exception{
        Date dataCorrente = getDate();
        if(dataCorrente.compareTo(dataPartenza) <= 0){
            throw new IllegalArgumentException();
        }else if(dataCorrente.compareTo(dataArrivo) <= 0){
            throw new IllegalArgumentException();
        }
            int i=0;
            while(v[i] != null && i<v.length){
                i++;
            }
            v[i] = new Viaggio();
            v[i].creaAutocarro(tipoMerce, kg);
            v[i].creaPercorso(tratta);
            v[i].impostaDataPartenza(dataPartenza);
            v[i].impostaDataArrivo(dataArrivo);
            return v;
    }

    public void reinstrada(String[] tratta, Viaggio v){
        v.creaPercorso(tratta);
    }
    
    public Viaggio[] cerca(AutoCarro path, Viaggio v[]){
        int i=0;
        while(!(camion(v[i]).equals(path))){
            i++;
        }
        return v[i];
    }
    public Viaggio[] cerca(Percorso path, Viaggio v[]){
        int i=0;
        while(!(tratta(v[i]).equals(path))){
            i++;
        }
        return v[i];
    }
    public Viaggio[] cerca(Date path, Viaggio v[]){
        int i=0;
        while(!(data(v[i]).equals(path))){
            i++;
        }
        return v[i];
    }
}
