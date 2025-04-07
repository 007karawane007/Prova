public class TopDown {
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
    private static final int UNKNOWN = -1;//0
    public static int llisM( int[] s){
        int n = s.length;
        int [][]mem = new int [n+1][n+1];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                mem[i][j]=UNKNOWN;
            }
        }
        return llisMem1(s, 0, n, mem);
    }
    private static int llisMem( int[]s, int i, int t, int[][] mem){
        if(mem[i][t]==UNKNOWN){
            if ( i == s.length ) { // i = n : coda di s vuota
                mem[i][t] = 0;
            } else if ( s[i] <= t ) {   // x = s[i] ≤ t : x non può essere scelto
                mem[i][t] = llisMem( s, i+1, t, mem);
            } else { // x > t : x può essere scelto o meno
                mem[i][t] = Math.max( 1+llisMem(s,i+1,s[i], mem), llisMem(s,i+1,t, mem) );
            }
        }
        return mem[i][t];
    }

    private static int llisMem1( int[]s, int i, int j, int[][] mem){
        if(mem[i][j]==UNKNOWN){//se 0<=j<n => t=s[j]
                if ( i == s.length ) { // i = n : coda di s vuota
                    mem[i][j] = 0;
                }else if (j == s.length) { // x > t : x può essere scelto o meno
                    mem[i][j] = Math.max( 1+llisMem1(s, i+1, i, mem), llisMem1(s, i+1, j, mem) );
                }else if ( s[i] <= s[j] ) {   // x = s[i] ≤ t : x non può essere scelto
                    mem[i][j] = llisMem1( s, i+1, j, mem);
                }else{
                    mem[i][j] = Math.max( 1+llisMem1(s, i+1, i, mem), llisMem1(s, i+1, j, mem) );
                }
        }
        //stampaMatrice(mem, s.length);
        return mem[i][j];
    }
    public static void stampaMatrice (int[][] m, int n){
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
}
