/*
 * Mission: Rappresentare un elenco di merci con il relativo prezzo
 * Inv: Il catalogo è unico, puo essere modificato ma non duplicato, ogni merce è diversa dall'altra
 */
public class Catalogo {
    Vector<String> merci = new Vector<String>();
    Vector<Integer> prezzi = new Vector<Integer>();

    /*
     * @precond:
     * @postcond:Modifica lo stato concreto aggiungendo la merce indicata con il suo prezzo, se merce == null || merce == "" || prezzo<=0 throw new IllegalArgumentException
     */
    static void aggiungiMerce(String merce, int prezzo) throws IllegalArgumentException {

    }

    /*
     * @precond:
     * @postcond:Modifica lo stato concreto rimuovendo la merce indicata con il suo prezzo, se merce == null || merce == "" || merce !£ Catalogo non fa nulla
     */
    static void rimuoviMerce(String merce){

    }

    /*
     * @precond:
     * @postcond:
     */
    static void modificaPrezzo(String merce){

    }

    static int getPrezzo(String merce){

    }

    static String[] getMerce(int prezzo){

    }
}
