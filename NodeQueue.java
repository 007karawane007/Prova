public class NodeQueue
{
    private int dimensione;
    private Nodo[] v;//private final Nodo[] v;
    
    public NodeQueue(){//costruttore: creazione della coda di nodi vuota
        dimensione=0;
        v = new Nodo [128];//v = new Nodo [dimensione];
    }
    
    public int size(){//restituisce il numero di elementi contenuti nella coda
        return dimensione;
    }
    
    public Nodo peek(){//restituisce l’elemento con “peso minore” (senza rimuoverlo dalla coda)
       return v[0];
    }
    
    public Nodo poll(){//restituisce e rimuove dalla coda l’elemento con “peso minore”
        Nodo c = peek();
        if(c == null){
            return c;
        }else{
           v[0]=null;
            for(int i=0; i<dimensione-1; i++){
                v[i] = v[i+1];
                v[i+1] = null;
            }
            dimensione--;
            return c; 
        }
    }
    
    public void add(Nodo n){//aggiunge un nuovo elemento n alla coda
        dimensione++;
        if(v[0] == null){
            v[0] = n;
        }else{
            if(dimensione%128 == 0){
                Nodo[] f = new Nodo[2*dimensione];
                for(int k=0; k<dimensione; k++){
                    f[k]=v[k];
                }
                v = new Nodo[2*dimensione];
                for(int k=0; k<dimensione; k++){
                    v[k]=f[k];
                }
            }
            int j=0;
            while(v[j]!=null){
                j++;
            }
            v[j] = n;
            j=0;
            while(v[j+1] != null && v[j] != null){
                if(v[j].compareTo(v[j+1]) == 1){
                    final Nodo temp = v[j+1];
                    v[j+1] = v[j];
                    v[j] = temp;
                    j++;
                }else{
                    j++;
                }
            }
        }
    }
}
