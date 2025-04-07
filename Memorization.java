public class Memorization {
    //Fattoriale
    /*
     * R(n) > (3/2)^n-1
     */
    public static int  fib (int n){
    if (n<2){
            return 1;
        }else{
            return (fib(n-2) + fib(n-1));
        }
    }
    public static long fibM(int n){
        //Mem viene creata e inizializzata
        long [] mem = new long [n+1];
        for(int i=0; i<=n; i++){
            mem[i]=UNKNOWN;
        }
        return fibMem(n, mem);
    }
    private static final int UNKNOWN = -1;//0
    private static long  fibMem (int n, long[] mem){
        if(mem[n] == UNKNOWN){//non è conosciuto
            if(n<2){
            mem[n] = 1;
            }else{
                mem[n] = (fibMem(n-2, mem) + fibMem(n-1, mem));
            }
        }
        return mem[n];
    }

    //Manattan
    public static long manh (int i, int j){
        if(i==0 || j==0){
            return 1;
        }else{
            return (manh(i-1, j) + manh(i, j-1));
        }
    }
    public static long manhM(int i, int j){
        long[][] mem = new long[i+1][j+1];
        for(int x=0; x<=i; x++){
            for(int y=0; y<=j; y++){
                mem[x][y]=UNKNOWN;
            }
        }
        return manhMem(i, j, mem);
    }
    private static long manhMem (int i, int j, long[][] mem){
        if(mem[i][j] == UNKNOWN){
            if(i==0 || j==0){
                mem[i][j] = 1;
            }else{
                mem[i][j] = (manhMem(i-1, j, mem) + manhMem(i, j-1, mem));
            }
        }
        return mem[i][j];
    }
    public static long manhDp (int i, int j){
        long[][] mem = new long[i+1][j+1];
        for(int x=0; x<=i; x++){
            for(int y=0; y<=j; y++){
                if(x==0 || y==0){
                    mem[x][y]=1;
                }else{
                    mem[x][y]=mem[x-1][y] + mem[x][y-1];
                }
            }
        }
        return mem[i][j];
    }

    //Sottosequenza piu lunga
    public static int llcs (String u, String v){
        int m = u.length();
        int n = v.length();
        if((n==0)||(m==0)){
            return 0;
        }else if(u.charAt(0) == v.charAt(0)){
            return 1 + llcs(u.substring(1), v.substring(1));
        }else{
            return Math.max(llcs(u.substring(1), v), llcs(u, v.substring(1)));
        }
    }
    public static int llcsM(String u, String v){
        int m = u.length();
        int n = v.length();
        int [][] mem = new int [m+1][n+1];
        for(int x=0; x<=m;x++){
            for(int y=0; y<=n;y++){
                mem[x][y]=UNKNOWN;
            }
        }
        return llcsMem(u, v, mem);
    }
    private static int llcsMem (String u, String v, int [][]mem){
        int m = u.length();
        int n = v.length();
        if(mem[m][n]==UNKNOWN){   
            if((n==0)||(m==0)){
                mem[m][n]=0;
            }else if(u.charAt(0) == v.charAt(0)){
                mem[m][n]= 1 + llcsMem(u.substring(1), v.substring(1), mem);
            }else{
                mem[m][n]= Math.max(llcsMem(u.substring(1), v, mem), llcsMem(u, v.substring(1), mem));
            }
        }
        return mem[m][n];
    }
    public static String lcsM(String u, String v){
        int m = u.length();
        int n = v.length();
        String [][] mem = new String [m+1][n+1];
        for(int x=0; x<=m;x++){
            for(int y=0; y<=n;y++){
                mem[x][y]=null;
            }
        }
        return lcsMem(u, v, mem);
    }
    public static String lcsMem(String u, String v, String [][] mem){
        int m = u.length();
        int n = v.length();
        if(mem[m][n]==null){
            if((n==0)||(m==0)){
                mem[m][n]=null;
            }else if(u.charAt(0) == v.charAt(0)){
                mem[m][n]= 1 + lcsMem(u.substring(1), v.substring(1), mem);
            }else{
                mem[m][n]= longer(lcsMem(u.substring(1), v, mem), lcsMem(u, v.substring(1), mem));
            }
        }
        return mem[m][n];   
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
    public static int llcsDp(String u, String v){ //Bottom up
        int m = u.length();
        int n = v.length();
        int [][] mem = new int [m+1][n+1];
        for(int y=0; y<=n;y++){
            mem[0][y] = 0;
        }
        for(int x=0; x<=m; x++){
            mem[x][0] = 0;
        }
        for(int x=1; x<=m;x++){
            for(int y=1; y<=n;y++){
                if(u.charAt((m-x))==v.charAt((n-y))){
                    mem[x][y]=1 + mem[x-1][y-1];
                }else{
                    mem[x][y]=Math.max(mem[x-1][y], mem[x][y-1]);
                }
            }
          }
        return mem[m][n];
    }
    public static String lcsDp(String u, String v){ //Bottom up
        int m = u.length();
        int n = v.length();
        int [][] mem = new int [m+1][n+1];
        for(int y=0; y<=n;y++){
            mem[0][y] = 0;
        }
        for(int x=0; x<=m; x++){
            mem[x][0] = 0;
        }
        for(int x=1; x<=m;x++){
            for(int y=1; y<=n;y++){
                if(u.charAt((m-x))==v.charAt((n-y))){
                    mem[x][y]=1 + mem[x-1][y-1];
                }else{
                    mem[x][y]=Math.max(mem[x-1][y], mem[x][y-1]);
                }
            }
        }
        String s = "";
        int i = m;
        int j = n;
        while(mem[i][j]>0){
            if(u.charAt((m-i))==v.charAt((n-j))){
                s=s+u.charAt(m-i);
                i--;
                j--;
            }else if(mem[i-1][j]<mem[i][j-1]){
                j--;
            }else if(mem[i-1][j]>mem[i][j-1]){
                i--;
            }else if(Math.random()<0.5){
                j--;
            }else{
                i--;
            }
        }
        return s;
    }

    //Stirling
    public static long stirling(int n, int k){
        if((k==1)||(k==n)){
            return 1;
        }else{
            return stirling(n-1, k-1)+k*stirling(n-1, k);
        }
    }

    public static long stirlMem(int n, int k){
        long [][]mem = new long [n+1][];
        for(int i=1; i<=n; i++){
            int x=Math.min(i, k);
            mem[i] = new long[x+1];
            for(int j=1; j<=x; j++){
                mem[i][j]=UNKNOWN;
            }
        }
        return stirlRec(n, k, mem);
    }
    private static long stirlRec(int n, int k, long [][]mem){
        if(mem[n][k]==UNKNOWN){
            if((k==1)||(k==n)){
                mem[n][k] = 1;
            }else{
                mem[n][k] = stirlRec(n-1, k-1, mem)+k*stirlRec(n-1, k, mem);
            }
        }
        return mem[n][k];
    }
    public static long stirlDp(int n, int k){
        long []mem = new long [k+1];
        for(int i=1; i<=n; i++){
            int x=Math.min(i, k);
            mem[x]=(x==i) ? 1 : mem[x-1]+x*mem[x];
            for(int j=x-1; j>1; j--){
                mem[j]=mem[j-1]+j*mem[j];
            }
            mem[1]=1;
        }
        return mem[k];
    }
}//class Memorization


















































