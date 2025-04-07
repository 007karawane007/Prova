public class StringSList
{
    public static final StringSList NULL_STRINGSLIST = new StringSList();
    private final boolean empty;
    private final String first;
    private final StringSList rest;
    
    public StringSList(){
        empty=true;
        first="";
        rest=null;
    }
    public StringSList (String e, StringSList sl){
        empty=false;
        first=e;
        rest=sl;
    }
    public boolean isNull(){
        return empty;
    }
    public String car(){
        return first;
    }
    public StringSList cdr(){
        return rest;
    }
    public StringSList cons(String e){
        return new StringSList(e, this);
    }
    public int length(){
        if(isNull()){
            return 0;
        }else{
            return 1 + this.cdr().length();
        }
    }
    public boolean equals(StringSList sl){
        if ( isNull() || sl.isNull() ) {
      return ( isNull() && sl.isNull() );
    } else if ( car() == sl.car() ) {
      return cdr().equals( sl.cdr() );
    } else {
      return false;
    }
    }
    public StringSList append(StringSList sl){
        if(isNull()){
            return sl;
        }else{
            return (cdr().append(sl)).cons(car());
        }
    }
    public StringSList reverse(){
        StringSList rl = this;
        StringSList sl = NULL_STRINGSLIST;
        while(!rl.isNull()){
            sl=sl.cons(rl.car());
            rl=rl.cdr();
        }
        return sl;
    }
    public String listRef(int n){
        return listRefRec(this, n);
    }
    private String listRefRec(StringSList sl, int n){
        if(n == 0){
            return sl.car();
        }else{
            return listRefRec(sl.cdr(), n-1);
        }
    }
    public String toString(){
        if(isNull()){
            return"()";
        }else{
            String txt="( " + car();
            StringSList r= cdr();
            while(!r.isNull()){
                txt=txt + ", "+ r.car();
                r = r.cdr();
            }
            return txt + " )";
        }
    }
}
