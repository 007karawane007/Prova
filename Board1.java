public class Board1 {
   private static final String ROWS = " 123456789ABCDEF";
   private static final String COLS = " abcdefghijklmno";

   private final int size;
   private int queens;
   private String config;
   private int[] rws;
   private int[] cls;
   private int[] d1s;
   private int[] d2s;

    public Board1(int n){
        size = n;
        queens = 0;
        config = " ";
        rws = new int[n];
        cls = new int[n];
        d1s = new int[2*n-1];
        d2s = new int[2*n-1];
        for(int i=0; i<n; i++){
            rws[i]=0;
            cls[i]=0;
        }
        for(int j=0; j<2*n-1; j++){
            d1s[j]=0;
            d2s[j]=0;
        }
   }
   public int size(){
        return size;
   }
   public int queensOn(){
        return queens;
   }
   public Boolean underAttac(int i, int j){
        int n=size;
        return ((rws[i-1]>0)||(cls[j-1]>0)||(d1s[i-j+n-1]>0)||(d2s[i+j+n-1]>0));
   }
   public void addQueen(int i, int j){
        queens=queens+1;
        int n=size;
        rws[i-1]=rws[i-1]+1;
        cls[j-1]=cls[j-1]+1;
        d1s[i-j+n-1]=d1s[i-j+n-1]+1;
        d2s[i+j-2]=d2s[i+j-2]+1;
        config= config + COLS.charAt(j) + ROWS.charAt(i) + " "; 
   }
   public void removeQueen(int i, int j){
     queens=queens-1;
     int n=size;
     rws[i-1]=rws[i-1]-1;
     cls[j-1]=cls[j-1]-1;
     d1s[i-j+n-1]=d1s[i-j+n-1]-1;
     d2s[i+j-2]=d2s[i+j-2]-1;
     String pos= "" + COLS.charAt(j) + ROWS.charAt(i); 
     int k=config.indexOf(pos);//indice della sottostringa pos in config
     config= config.substring(0, k) + config.substring(k+3); 
   }
   public String arrangement(){
        return config;
   } 
   public String toString(){
        return "[" + arrangement() + "]";
   }
   public boolean isFree(int i){
       for(int j=0; j<rws.length; j++){
           if(i==rws[j]){
               return false;
           }
       }
       return true;
   }
   public void addQueen(String pos){
        queens=queens+1;
        int n=size;
        int i=0;
        int j=0;
        int k=0;
        while(k<COLS.length()){
            if(pos.charAt(0) != (COLS.charAt(i))){
                i++;
            }else{
                k=COLS.length();
            }
        }
        while(k<ROWS.length()){
            if(pos.charAt(1) != (ROWS.charAt(j))){
                j++;
            }else{
                k=ROWS.length();
            }
        }
        rws[i-1]=rws[i-1]+1;
        cls[j-1]=cls[j-1]+1;
        d1s[i-j+n-1]=d1s[i-j+n-1]+1;
        d2s[i+j-2]=d2s[i+j-2]+1;
        config= config + COLS.charAt(j) + ROWS.charAt(i) + " "; 
   }
}
