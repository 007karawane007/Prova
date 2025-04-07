
public class Problema12 {
   private static final String ROWS = " 123456789ABCDEF";
   private static final String COLS = " abcdefghijklmno";
   private final String config;
   private final int size;
   private final int queens;
   private final SList<SList<Integer>> coordinateList;
   private final SList<Integer> coordinate;
    public Problema12(int n){
    config = " ";
    size = n;
    queens = 0;
    coordinateList = new SList<SList<Integer>>();
    coordinate = new SList<Integer>();
   }
   private Problema12(int n, int q, SList<SList<Integer>> coL, SList<Integer> co, String c){
    size = n;
    queens = q;
    coordinate = co;
    coordinateList = coL;
    config = c;
   }
   public int size(){
    return size;
   }
   public int queensOn(){
    return queens;
   }
   public Boolean underAttac(int i, int j){
    for(int k=0; k<coordinateList.length(); k++){
        if(i == coordinateList.listRef(k).car()){
            return true;
        }
    }
    for(int k=0; k<coordinateList.length(); k++){
        if(j == coordinateList.listRef(k).cdr().car()){
            return true;
        }
    }
    for(int k=0; k<coordinateList.length(); k++){
        if(i+j == coordinateList.listRef(k).car()+coordinateList.listRef(k).cdr().car()){
            return true;
        }
    }
    for(int k=0; k<coordinateList.length(); k++){
        if(i-j == coordinateList.listRef(k).car()-coordinateList.listRef(k).cdr().car()){
            return true;
        }
    }
    return false;
   }
   public Problema12 addQueen(int i, int j){
    return new Problema12(size, queens+1, coordinateList.cons(coordinate.cons(j).cons(i)), coordinate.cons(j).cons(i), config + COLS.charAt(j) + ROWS.charAt(i) + " ");
   }
   public String arrangement(){
    return config;
   } 
}//class Problema12
