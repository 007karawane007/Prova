import java.util.*;

public class Singelton {
    private static Singelton istance;
    Vector<Integer> v = new Vector<Integer>();

    private Singelton(int a[]){
        for(int i=0; i<a.length; i++){
            this.v.add((Integer)a[i]);
        }
    }

    public static Singelton getIstance(int a[]){
        if(istance == null){
            istance = new Singelton(a);
        }
        return istance;
    }

    public Iterator<Integer> iterator(){
        return this.v.iterator();
    }
}