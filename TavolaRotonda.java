public class TavolaRotonda
{
    private final int numero;
    private final int brocca;
    private final SList<Integer> altri;
    private final SList<Integer> coda;
    public TavolaRotonda(int n){
        numero = n;
        brocca = 1;
        altri = Test.intervallo(2, n);
        coda = new SList<Integer>();
    }
    private TavolaRotonda(int num, int bro, SList<Integer> alt, SList<Integer> cod){
        numero = num;
        brocca = bro;
        altri = alt;
        coda = cod;
    }
    public int cavalieriRimasti(){
        return numero;
    }
    public int cavaliereConBrocca(){
        return brocca;
    }
    public TavolaRotonda servi(){
        //return new TavolaRotonda(numero-1,brocca, altri.cdr());
        if(numero>1){
            if(altri.isNull()){
                return new TavolaRotonda(numero-1, brocca, coda.reverse().cdr(), new SList<Integer>());
            }else{
                return new TavolaRotonda(numero-1, brocca, altri.cdr(), coda);
            }
        }else{
            return this;
        }
    }
    public TavolaRotonda passa(){
        if(numero>1){
            //return new TavolaRotonda(numero, altri.car(), altri.cdr().append(IntSList.NULL_INTLIST.cons(brocca)));
            if(altri.isNull()){
                SList<Integer> il = coda.cons(brocca).reverse();
                return new TavolaRotonda(numero, il.car(), il.cdr(), new SList<Integer>());
            }else{
                return new TavolaRotonda(numero, altri.car(), altri.cdr(), coda.cons(brocca));
            }
        }else{
            return this;
        }
    }
}//class TavolaRotonda
