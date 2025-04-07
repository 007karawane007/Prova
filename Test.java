public class Test
{
   public static SList<Integer> intervallo(int inf, int sup){
       if(inf>sup){
           return new SList<Integer>(); //return new IntSList();
       }else{
           //return intervallo(inf+1, sup).cons(inf);
           return new SList<Integer>(inf, intervallo(inf+1, sup));
       }
   }
}
