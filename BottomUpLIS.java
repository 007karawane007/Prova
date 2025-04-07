public class BottomUpLIS {
      public static int llis( int[] s ) { // s[i] > 0 per i in [0,n-1], dove n = s.length 
        return llisRec( s, 0, 0 );
    }
    private static int llisRec( int[] s, int i, int t ) {
        if ( i == s.length ) { // i = n : coda di s vuota
            return 0;
        } else if ( s[i] <= t ) {   // x = s[i] ≤ t : x non può essere scelto
            return llisRec( s, i+1, t );
        } else { // x > t : x può essere scelto o meno
            return Math.max( 1+llisRec(s,i+1,s[i]), llisRec(s,i+1,t) );
      }
    }

    // Length of Longest Increasing Subsequence (LLIS):
    // Programmazione dinamica bottom-up
    
    public static int llisDP( int[] s ) {
    
      int n = s.length;
      
      int[][] mem = new int[ n+1 ][ n+1 ];
      
      // Matrice: valori delle ricorsioni di llisRec
      // relativi a diversi valori degli argomenti
      
      for ( int j=0; j<=n; j=j+1 ) {

        // --------------------------------------------------
        //  Inserisci qui i comandi per registrare i valori
        //  corrispondenti ai casi base della ricorsione
        // --------------------------------------------------
        mem[j][n]=0;
      }
      
      for ( int i=n-1; i>=0; i=i-1 ) {
        for ( int j=0; j<=n; j=j+1 ) {
        
          // ------------------------------------------------
          //  Inserisci qui le strutture di controllo
          //  appropriate e i comandi per registrare
          //  i valori corrispondenti ai casi ricorsivi
          // ------------------------------------------------
        if(j == n){
              mem[j][i] = Math.max(1+mem[i][i+1], mem[j][i+1]);
        }else{
            if (s[i] == s[j]) { // x > t : x può essere scelto o meno
                mem[j][i]=-1;
            }else if ( s[i] < s[j] ) {   // x = s[i] ≤ t : x non può essere scelto
                mem[j][i] = mem[j][i+1];
            }else if ( s[i] > s[j] ){
                mem[j][i] = Math.max(1+mem[i][i+1], mem[j][i+1]);
            }
        }
      }}
      
      // ----------------------------------------------------
      //  Inserisci di seguito l'elemento della matrice
      //  il cui valore corrisponde a llis(s) :
      
      return  mem[n][0] /* elemento appropriato della matrice */;
      
      // ----------------------------------------------------
    }
    
    
    // Longest Increasing Subsequence (LIS):
    // Programmazione dinamica bottom-up
    
public static int[] lisDP( int[] s ) {
    
    int n = s.length;
      
    int[][] mem = new int[ n+1 ][ n+1 ];
      
      // 1. Matrice: valori delle ricorsioni di llisRec
      //    calcolati esattamente come per llisDP
      
      // ------------------------------------------------
      //  Replica qui il codice del corpo di llisDP
      //  che registra nella matrice i valori
      //  corrispondenti alle ricorsioni di llisRec
      // ------------------------------------------------
    for ( int j=0; j<=n; j=j+1 ) {

        // --------------------------------------------------
        //  Inserisci qui i comandi per registrare i valori
        //  corrispondenti ai casi base della ricorsione
        // --------------------------------------------------
        mem[j][n]=0;
    }
      
    for ( int i=n-1; i>=0; i=i-1 ) {
        for ( int j=0; j<=n; j=j+1 ) {
        
          // ------------------------------------------------
          //  Inserisci qui le strutture di controllo
          //  appropriate e i comandi per registrare
          //  i valori corrispondenti ai casi ricorsivi
          // ------------------------------------------------
        if(j == n){
            mem[j][i] = Math.max(1+mem[i][i+1], mem[j][i+1]);
        }else{
            if (s[i] == s[j]) { // x > t : x può essere scelto o meno
                mem[j][i]=-1;
            }else if ( s[i] < s[j] ) {   // x = s[i] ≤ t : x non può essere scelto
                mem[j][i] = mem[j][i+1];
            }else if ( s[i] > s[j] ){
                mem[j][i] = Math.max(1+mem[i][i+1], mem[j][i+1]);
            }
        }
    }}
      // 2. Cammino attraverso la matrice per ricostruire
      //    un esempio di Longest Increasing Subsequence
      
      // ----------------------------------------------------
      //  Inserisci di seguito l'elemento della matrice
      //  il cui valore corrisponde a llis(s) :
  
    int m =  mem[n][0]/* elemento appropriato della matrice */;
      
      // ----------------------------------------------------
      
    int[] r = new int[ m ];  // per rappresentare una possibile LIS
      
      // ----------------------------------------------------
      //  Introduci e inizializza qui gli indici utili
      //  per seguire un cammino attraverso la matrice e
      //  per assegnare gli elementi della sottosequenza r
      // ----------------------------------------------------
    int i = n;
    int j = 0;
    int k = 1;
    while ( mem[i][j] > 0 ) {
      
        int t = ( j == n ) ? 0 : s[j];
        // --------------------------------------------------
        //  Inserisci qui strutture di controllo e comandi
        //  per scegliere e seguire un percorso appropriato
        //  attraverso la matrice in modo da ricostruire in
        //  r una possibile LIS relativa alla sequenza s
        // --------------------------------------------------
        if(mem[i][j] == m){
            if(mem[i][j] > mem[i][j+1]){
                r[j] = t;
                i=j;
                j++;
            }else{
                j++;  
            }
        }else{
            if(mem[i][j] > mem[i][j+1]){
                r[k] = t;
                i=j;
                j++;
                k++;
            }else{
                j++;  
            }
        }
    }
      return r;       
}
}// class BottomUpLIS  