/*
 * Board;
 * b = new Board(n);
 * b.addQueen(i, j) : Board;
 * b.size() : Int;
 * b.queensOn() : Int;
 * b.underAttac(i, j) : Boolean;
 * b.arrangement() : String;
 */

public class Problema11 {
   private final int size;
   private final int queens;
   private final SList<Integer> rows;
   private final SList<Integer> cols;
   private final SList<Integer> diagUp;
   private final SList<Integer> diagDown;
    public Problema11(int n){
    size = n;
    queens = 0;
    rows = new SList<Integer>();
    cols = new SList<Integer>();
    diagUp = new SList<Integer>();
    diagDown = new SList<Integer>();
   }
   private Problema11(int n, int q, SList<Integer> r, SList<Integer> c, SList<Integer> dU, SList<Integer> dD){
    size = n;
    queens = q;
    rows = r;
    cols = c;
    diagUp = dU;
    diagDown = dD;
   }
   public int size(){
    return size;
   }
   public int queensOn(){
    return queens;
   }
   public Boolean underAttac(int i, int j){
    for(int k=0; k<rows.length(); k++){
        if(i == rows.listRef(k)){
            return true;
        }
    }
    for(int k=0; k<cols.length(); k++){
        if(j == cols.listRef(k)){
            return true;
        }
    }
    for(int k=0; k<diagUp.length(); k++){
        if(i-j == diagUp.listRef(k)){
            return true;
        }
    }
    for(int k=0; k<diagDown.length(); k++){
        if(i+j == diagDown.listRef(k)){
            return true;
        }
    }
    return false;
   }
   public Problema11 addQueen(int i, int j){
    return new Problema11(size, queens+1, rows.cons(i), cols.cons(j), diagUp.cons(i-j), diagDown.cons(i+j));
   }
}//class Laboratorio3

