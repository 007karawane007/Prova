public class Problema10_2
{
    public static StringSList btrSuccN(String s, int n){
        StringSList l = new StringSList();
        for(int i=0; i<n; i++){
            l = new StringSList(s , l);
            s=esercitazioneSullaCodificaInJava.btrSucc(s);
        }
        return l.reverse();
    }
}
