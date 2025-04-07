public class SchemeJava
{
   //Calcolo della superficie totale di un cilindro in Java
    public static double supTot(double r, double h){
       return ((2*Math.PI*r)*(h+r));
   }
   
   //Plurale femminile in Java
   public static String pluraleF(String s){
       return s.substring(0, s.length()-1)+"e";
   }
   
   //Verifica se femminile
   public static boolean femminile(String s){
       return(s.charAt(s.length()-1) == 'a'); //return(s.substring(s.length()-1).equals("a"));
   }
   
   //Lato più lungo di un foglio Ak
   public static double s(int k){
       if(k>=2){
           return s(k-2)/2;
       }else if(k==0){
           return s0;
       }else{
           return s1;
       }
   }
   public static final double s0=Math.pow(2, 0.25);
   public static final double s1=Math.pow(2, -0.25);
   
   //btr val
   public static int btrVal(String btr){
       int k=btr.length()-1;
       if(k==0){
           return btdVal(btr);
       }else{
           return (3*btrVal(btr.substring(0,k))+btdVal(btr.substring(k)));
       }
   }
   private static int btdVal(String btd){
       if(btd.equals("-")){         //switch(btd.charAt(0)){
           return -1;               //case "-" : return -1;
       }else if(btd.equals(".")){   //case "." : return 0;
           return 0;                //case "+" : return 1;
       }else{
           return 1;
       }
   }
   
   public static int ufoBu(int x){
       int [] u = new int[x+1];
       u[1]=1;
       for(int k=2; k<=x; k++){
           if(k%2==0){
               u[k]=2*u[k/2]-1;
           }else{
               u[k]=2*u[k/2]+1;
           }
       }
       return u[x];
   }
   public static int ufo(int x){
       int [] u = new int[logSize(x)];
       u[0]=x;
       int i=0;
       while(x>1){
           x=x/2;
           i++;
           u[i]=x;
       }
       int y=1;
       for(int j=i-1; j>=0; j--){
           if(u[j]%2==0){
               y=2*y-1;
           }else{
               y=2*y+1;
           }
       }
       return y;
   }
   private static int logSize(int n){
       return ((int) (Math.log(n)/Math.log(2))+1);
   }
   
   public static int btrVal2(String btr){
       int n=0;
       for(int i=0; i<btr.length(); i++){
           n=3*n+btdVal(btr.substring(i, i+1));
       }
       return n;
   }
   
   //Insertion Sort
   public static void inSort(int []v){
       for(int k=1; k<v.length; k++){
           int x=v[k];
           int i=k-1;
           while(i>=0 && x<v[i]){
               v[i+1]=v[i];
               i--;
           }
           v[i+1]=x;
       }
   }
   public static void inSort2(int[]v){
       for(int i=1; i<v.length; i++){
           for(int j=i; j>=0; j--){
               if(v[i]<v[j]){
                   int x=v[i];
                   v[i]=v[j];
                   v[j]=x;
               }
           }
       }
   }
   public static void verifica(int []v){
       inSort2(v);
       for(int j=0; j<v.length; j++){
           System.out.println(v[j]);
       }
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
