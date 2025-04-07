public class Test1
{
    public static SList<String> listaDiStringhe(String s){
        SList<String> t = new SList<String>();
        for(int i=0; i<s.length(); i++){
            t = new SList<String>(s.substring(i, i+1), t);
        }
        return t.reverse();
    }
}
