public class esercitazioneSullaCodificaInJava
{
    public static String btrSucc(String btr){
        int n = btr.length();
        char lsb = btr.charAt(n-1);
        if(n==1){
            if(lsb == '+'){
                return "+-";
            }else{
                return "+";
            }
        }else{
            String pre = btr.substring(0, n-1);
            if(lsb == '+'){
                return btrSucc(pre) + "-";
            }else{
                return pre + (lsb=='-'? "." : "+");
            }
        }
    }
    public static String complementoUno(String b){
        String s = "";
        if(b.equals("")){
            return "";
        }else{
            for(int i=0; i<b.length(); i++){
                if(b.charAt(i) == '0'){
                    s = s + "1";
                }else{
                    s = s + "0";
                }
            }
            return s;
        }
    }
}
