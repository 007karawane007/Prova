public class GiuseppeFlavio1 {
    public static int ultimoCavaliere(int n){
        TavolaRotonda1 t = new TavolaRotonda1(n);
        while(t.cavalieriRimasti() > 1){
            t.servi();
            t.passa();
        }
        return t.cavaliereConBrocca();
    }
}
