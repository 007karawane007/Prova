import java.util.*;
public class Esercitazione
{
    public static int llcs3 (String t, String u, String v){
        if(t.equals("") || u.equals("") || v.equals("")){
            return 0;
        }else if(t.charAt(0) == u.charAt(0) && t.charAt(0) == v.charAt(0)
                && v.charAt(0) == u.charAt(0)){
            return 1 + llcs3(t.substring(1), u.substring(1), v.substring(1));
        }else{
            return Math.max(Math.max(llcs3(t.substring(1), u, v), llcs3(t, u.substring(1), v)), 
                            llcs3(t, u, v.substring(1)));               
        }
    }
    public static final int UNKNOWN = -1;
    public static int llcs3M (String t, String u, String v){
        int [][][] mem = new int [t.length()+1][u.length()+1][v.length()+1];
        for(int i=0; i<t.length(); i++){
            for(int j=0; j<u.length(); j++){
                for(int k=0; k<v.length(); k++){
                    mem[i][j][k] = UNKNOWN;
                }
            }
        }
        return llcs3Mem(t, u, v, 0, 0, 0, mem);
    }
    private static int llcs3Mem(String t, String u, String v, int i, int j, int k, int[][][]mem){
        if(mem[i][j][k] == UNKNOWN){
            if(t.equals("") || u.equals("") || v.equals("")){
                mem[i][j][k] = 0;
            }else if(t.charAt(0) == u.charAt(0) && t.charAt(0) == v.charAt(0)
                    && v.charAt(0) == u.charAt(0)){
                mem[i][j][k] = 1 + llcs3Mem(t.substring(1), u.substring(1), v.substring(1), i+1, j+1, k+1, mem);
            }else{
                mem[i][j][k] = Math.max(Math.max(llcs3Mem(t.substring(1), u, v, i+1, j, k, mem), llcs3Mem(t, u.substring(1), v, i, j+1, k, mem)), 
                                llcs3Mem(t, u, v.substring(1), i, j, k+1, mem));               
            }
        }
        
        return mem[i][j][k];
    }//v

    public static boolean isSimmetrica(int[][] m){
        for(int i=0; i<m.length; i++){
            for(int j=0; j<m.length; j++){
                if(m[i][j] != m[j][i]){
                    return false;
                }
            }
        }
        return true;
    }//v
    
    public static int shortestCodeLength( Nodo root ) {
        int sc = root.peso();
        Stack<Nodo> stack = new Stack<Nodo>();
        Stack<Integer> depth = new Stack<Integer>();
        stack.push( root );
        depth.push( 0 );
        do {
            Nodo n = stack.pop();
            int d = depth.pop();
            if ( n.foglia() ) {
                sc = Math.min( sc, d );
            } else if ( d+1 < n.peso()) {
                stack.push(n.destro());
                stack.push(n.sinistro());
                depth.push(d+1);
                depth.push(d+1);
            }
        } while ( !stack.empty()  );
        return sc;
    }//v
    
    public static double[] closestPair(double[] v){ //new double[] {0.3, 0.1, 0.6, 0.8, 0.5, 1.1}
        double mem = 1;
        double[] r = new double[2];
        int x = 0;
        int y = 0;
        for(int i=0; i<v.length; i++){
            for(int j=i+1; j<v.length; j++){
                if(mem>v[i]-v[j] && v[i]-v[j]>=0){
                    mem = v[i]-v[j];
                    x=i;
                    y=j;
                }else if(v[i]-v[j]<0 && mem>(v[i]-v[j])*(-1)){
                    mem = (v[i]-v[j])*(-1);
                    x=i;
                    y=j;
                }
            }
        }
        r[0]=v[y];
        r[1]=v[x];
        return r;
    }//v
    
    public static int commonStretches(String s, String t){//"1|01|0|110|1|10", "1|10|0|011|1|01"
        int i=0;
        int j=0;
        int k=0;
        int contoUniS=0;
        int contoZeriS=0;
        int contoUniT=0;
        int contoZeriT=0;
        while(i<s.length() && j<t.length()){
            if(s.charAt(i) == '1'){
                contoUniS++;
            }else{
                contoZeriS++;
            }
            if(t.charAt(i) == '1'){
                contoUniT++;
            }else{
                contoZeriT++;
            }
            if(s.charAt(i) == t.charAt(j) && contoUniS == contoUniT && contoZeriS == contoZeriT){
                k++;
                i++;
                j++;
            }else{
                i++;
                j++;
            }
        }
        return k;
    }//v
    
    
    public static int codeSizeIter( Nodo root ) {
        long bits = 0;
        Stack<Frame1> stack = new Stack<Frame1>();
        stack.push(new Frame1(root, 0));
        do {
            Frame1 current = stack.pop();
            Nodo n = current.node;
            int depth = current.depth;
            if ( n.foglia() ) {
                 bits = bits + depth * n.peso();
            } else {
                 stack.push(new Frame1(n.sinistro(), depth+1));
                 stack.push(new Frame1(n.destro(), depth+1));
            }
        } while ( !stack.empty() );
        return (int) ( bits / 7 ) + ( (bits%7 > 0) ? 1 : 0 );
    }//v
    
    public static boolean heapCheck(double[] v){
        for(int j=2; j<v.length; j++){
            if(v[j/2] > v[j]){
                return false;
            }
        }
        return true;
    }//v
    
    public static String lps( String s ) { // longest palindromic subsequence
        int n = s.length();
            if ( n < 2 ) { // stringa vuota o di un solo carattere: palindrome
                return s;
            } else if ( s.charAt(0) == s.charAt(n-1) ) { // caratteri estremi uguali: fanno parte del risultato
                return s.charAt(0) + lps( s.substring(1,n-1) ) + s.charAt(n-1);
            } else { // caratteri estremi diversi: almeno uno va scartato
                return longer( lps(s.substring(0,n-1)), lps(s.substring(1,n)) );
        }
    }
    private static String longer (String u, String v){
        int m = u.length();
        int n = v.length();
        if(m<n){
            return v;
        }else if(m>n){
            return u;
        }else if(Math.random() < 0.5){
            return u;
        }else{
            return v;
        }
    }
    public static String lpsDP( String s ) {
    int n = s.length();
    String[][] mem = new String[n+1][n+1];
    for ( int k=0; k<=n; k=k+1 ) {
        for ( int i=0; i<=n-k; i=i+1 ) {
            // k :lunghezza della sottostringa s* di s considerata;
            // i :posizione di s* in s:
            // s* corrisponde al potenziale argomento di una invocazione ricorsiva di lps.
            if ( k < 2 ) {
                mem[k][i] = s.substring(i, i+k);
            } else if ( s.charAt(i) == s.charAt(i+k-1) ) {
                mem[k][i] = s.charAt(i) + mem[k-2][i+1] + s.charAt(i+k-1);
            } else {
                mem[k][i] = longer(mem[k-1][i+1], mem[k-1][i]);
            }
    }}
        return mem[n][0];
    }//v
}//class Esercitazione
