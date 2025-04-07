/*
 * Board;
 * b = new Board(n);
 * b.addQueen(i, j) : Board;
 * b.size() : Int;
 * b.queensOn() : Int;
 * b.underAttac(i, j) : Boolean;
 * b.arrangement() : String;
 */
import queens.*;
public class Queens {
    public static int numeroSoluzioni(int n){
        return numeroCompletamenti(new Problema12(n));
    }
    public static SList<Board> listaSoluzioni(int n){
        return listaCompletamenti(new Board(n));
    }
    public static void listSettaggi(int n){
        listSettaggiRec(listaSoluzioni(n), n);
    }
    private static int numeroCompletamenti(Problema12 b){
        int n = b.size();
        int q = b.queensOn();
        if(q == n){
            return 1;
        }else{
            int i = q+1;
            int count = 0;
            for(int j=1; j<=n; j++){
                if(!b.underAttac(i, j)){
                    count = count + numeroCompletamenti(b.addQueen(i, j));
                }
            }
            return count;
        }
    }
    private static SList<Board> listaCompletamenti(Board b){
        int n = b.size();
        int q = b.queensOn();
        if(q == n){
            return (new SList<Board>()).cons(b);
        }else{
            int i = q+1;
            SList<Board> list = new SList<Board>();
            for(int j=1; j<=n; j++){
                if(!b.underAttac(i, j)){
                    list = list.append(listaCompletamenti(b.addQueen(i, j)));
                }
            }
            return list;
        }
    }
    private static void listSettaggiRec(SList<Board> lista, int n){
        if(lista.cdr().isNull()){
            queens.ChessboardView view = new queens.ChessboardView(n);
            view.setQueens(lista.car().arrangement());
        }else{
            queens.ChessboardView view = new queens.ChessboardView(n);
            view.setQueens(lista.car().arrangement());
            listSettaggiRec(lista.cdr(), n);
        }
    }
}//class Queens
