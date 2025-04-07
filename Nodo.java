public class Nodo implements Comparable<Nodo> {
    private final char car;
    private final int peso;
    private final Nodo sin;
    private final Nodo des;
    
    public Nodo(char c, int w){
        car = c;
        peso = w;
        sin = null;
        des = null;
    }
    public Nodo(Nodo l, Nodo r){
        car = (char) 0;//int-->char
        peso = l.peso()+r.peso();
        sin = l;
        des = r;
    }

    public boolean foglia(){
        return (sin == null);
    }
    public char simbolo(){
        return car;
    }
    public int peso(){
        return peso;
    }
    public Nodo sinistro(){
        return sin;
    }
    public Nodo destro(){
        return des;
    }

    public int compareTo(Nodo altro){
        if(peso < altro.peso()){
            return -1;
        }else if(peso == altro.peso()){
            return 0;
        }else{
            return +1;
        }
    }
}//class Nodo
