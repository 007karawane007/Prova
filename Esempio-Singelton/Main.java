import java.util.*;

public class Main {
    public static void main(String []args){
        int []a = new int [10];
        int []b = new int [10];

        for(int i=0; i<a.length; i++){
            a[i]=i;
            b[i]=0;
        }

        Singelton s = Singelton.getIstance(a);
        Singelton t = Singelton.getIstance(b);

        Iterator<Integer> j = s.iterator();
        Iterator<Integer> k = t.iterator();

        while(j.hasNext()){
            System.out.println("a" + (int) j.next());
        }

        while(k.hasNext()){
            System.out.println("b" + (int) k.next());
        }
    }
}