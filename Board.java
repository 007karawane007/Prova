/*
 * Board;
 * b = new Board(n);
 * b.addQueen(i, j) : Board;
 * b.size() : Int;
 * b.queensOn() : Int;
 * b.underAttac(i, j) : Boolean;
 * b.arrangement() : String;
 */
import java.util.function.*;
public class Board {
   private static final String ROWS = " 123456789ABCDEF";
   private static final String COLS = " abcdefghijklmno";
   private final int size;
   private final int queens;
   private final String config;
   private final BiPredicate<Integer, Integer> attack;
    public Board(int n){
    size = n;
    queens = 0;
    config = " ";
    attack = (x, y) -> false; //(lambda(x y) false)
   }
   private Board(int n, int q,BiPredicate<Integer, Integer> a, String c ){
    size = n;
    queens = q;
    config = c;
    attack = a; //(lambda(x y) false)
   }
   public int size(){
    return size;
   }
   public int queensOn(){
    return queens;
   }
   public Boolean underAttac(int i, int j){
    return attack.test(i, j);
   }
   public Board addQueen(int i, int j){
    return new Board(size, queens+1, (x, y) -> (x == i) || (y == j) ||
                                                (x-y == i-j) || (x+y == i+j) ||
                                                attack.test(x, y), 
                                                config + COLS.charAt(j) + ROWS.charAt(i) + " ");
   }
   public String arrangement(){
    return config;
   } 
   public String toString(){
    return "[" + arrangement() + "]";
   }
}//class Board
