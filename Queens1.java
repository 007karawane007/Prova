public class Queens1
{
    public static int numeroDiSoluzioni(int n) {
        return numeroDiCompletamenti( new Board1(n) ); //passo come argomento una
                                                     //scacchiera vuota
    }
    public static int numeroDiCompletamenti(Board1 b) { //calcola il n° di soluz.
        int n = b.size(); //dimensione della scacchiera
        int q = b.queensOn(); //numero di regine già presenti
        if (q == n) { //se il numero di regine presenti è lo stesso di n:
            return 1; //allora ho trovato una soluzione!
        } else {
            int count = 0; //contatore
            int i = q+1; //fissata la riga successiva libera
            for (int j=1; j<=n; j=j+1) { //considero tutte le posizioni
                                         //(colonne) possibili in quella riga
                 if (!b.underAttac(i, j)) { //se quella posizione non è minacciata
                    b.addQueen(i,j); //modifico lo stato della scacchiera
                    count = count + numeroDiCompletamenti(b);
                    b.removeQueen(i, j);                //eseguo la chiamata ricorsiva sulla scacchiera modificata
             }
            }
        return count; //count ha accumulato tutte le possibili soluzioni
        }
    }
    public static SList<Board1> listaDiSoluzioni(int n) {
        return listaDiCompletamenti( new Board1(n) ); //passo come argomento una
                                                     //scacchiera vuota
    }
    public static SList<Board1> NULL_BOARDSLIST = new SList<Board1>();
    private static SList<Board1> listaDiCompletamenti(Board1 b) { //lista di soluz.
        int n = b.size();
        int q = b.queensOn();
        if (q == n) { //se il numero di regine presenti è lo stesso di n:
            return NULL_BOARDSLIST.cons(b); //allora la scacchiera
                                            //presente è una soluzione!
        } else {
            SList<Board1> list = NULL_BOARDSLIST; //lista da riempire
            int i = q+1; //fissata la riga successiva libera
            for (int j=1; j<=n; j=j+1) { //considero tutte le posizioni
                                         //(colonne) possibili in quella riga
                 if (!b.underAttac(i, j)) { //se quella posizione non è minacciata
                        b.addQueen(i,j); //modifico lo stato della scacchiera
                        list = list.append( listaDiCompletamenti(b));
                                        //eseguo la chiamata ricorsiva sulla scacchiera modificata
                 }
            }
        return list; //list ha “accumulato” tutte le possibili soluzioni
        }
    }
}
