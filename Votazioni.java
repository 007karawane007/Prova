public class Votazioni{
    public static void main (String arc[]){
        int []a={1, 2, 1, 3, 1};
        int conto=1;
        int mem=0;
        int i=0;
        vota(a, conto, mem, i);
    }
    private static int vota(int[]a, int conto, int mem, int i){
        if(conto > a.length/2){
            return mem;
        }else{
            if(mem == a[i]){
                conto++;
            }else{
                vota(a, conto++, a[i], i++);
            }
        }
    }
}