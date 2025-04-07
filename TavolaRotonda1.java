public class TavolaRotonda1 {
    private int numero;
    private int brocca;         //indice del cavaliere con la brocca
    private int []cavalieri;
    public TavolaRotonda1(int n){
        numero = n;
        brocca = 0;
        cavalieri = new int[2*n-1];
        for(int i=0; i<n; i++){
            cavalieri[i]=i+1;
        }
    }
    public int cavalieriRimasti(){
        return numero;
    }
    public int cavaliereConBrocca(){
        return cavalieri[brocca];
    }
    public void servi(){
        cavalieri[brocca+1]=cavalieri[brocca];
        brocca=brocca+1;
        numero=numero-1;
    }
    public void passa(){
        cavalieri[brocca+numero]=cavalieri[brocca];
        brocca=brocca+1;
    }
}
