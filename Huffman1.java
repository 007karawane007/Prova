import java.util.*;
import huffman_toolkit.*;
public class Huffman1
{
    public static int[] freqCar(String src){
        InputTextFile in = new InputTextFile(src);
        int[] conto = new int[InputTextFile.CHARS];//128
        int contoRighe = 0;
        int contoCaratteri = 0;
        for(int i=0; i<conto.length; i++){
            conto[i]=1;
        }
        while(in.textAvailable()){
            String linea = in.readTextLine();
            contoRighe++;
        }
        in.close();
        in = new InputTextFile(src);
        while(in.textAvailable()){
            char caratteri = in.readChar();
            contoCaratteri++;
        }
        //System.out.println("contoRighe=" + contoRighe);
        //System.out.println("contoCaratteri=" + contoCaratteri);
        conto['a']=(int)(contoCaratteri*(8.167/100));
        conto['b']=(int)(contoCaratteri*(1.492/100));
        conto['c']=(int)(contoCaratteri*(2.782/100));
        conto['d']=(int)(contoCaratteri*(4.253/100));
        conto['e']=(int)(contoCaratteri*(12.702/100));
        conto['f']=(int)(contoCaratteri*(2.228/100));
        conto['g']=(int)(contoCaratteri*(2.015/100));
        conto['h']=(int)(contoCaratteri*(6.094/100));
        conto['i']=(int)(contoCaratteri*(6.966/100));
        conto['j']=(int)(contoCaratteri*(0.153/100));
        conto['k']=(int)(contoCaratteri*(0.772/100));
        conto['l']=(int)(contoCaratteri*(4.025/100));
        conto['m']=(int)(contoCaratteri*(2.406/100));
        conto['n']=(int)(contoCaratteri*(6.749/100));
        conto['o']=(int)(contoCaratteri*(7.507/100));
        conto['p']=(int)(contoCaratteri*(1.929/100));
        conto['q']=(int)(contoCaratteri*(0.095/100));
        conto['r']=(int)(contoCaratteri*(5.987/100));
        conto['s']=(int)(contoCaratteri*(6.327/100));
        conto['t']=(int)(contoCaratteri*(9.056/100));
        conto['u']=(int)(contoCaratteri*(2.758/100));
        conto['v']=(int)(contoCaratteri*(0.978/100));
        conto['w']=(int)(contoCaratteri*(2.361/100));
        conto['x']=(int)(contoCaratteri*(0.150/100));
        conto['y']=(int)(contoCaratteri*(1.974/100));
        conto['z']=(int)(contoCaratteri*(0.074/100));
        conto['A']=(int)(contoCaratteri*(8.167/100));
        conto['B']=(int)(contoCaratteri*(1.492/100));
        conto['C']=(int)(contoCaratteri*(2.782/100));
        conto['D']=(int)(contoCaratteri*(4.253/100));
        conto['E']=(int)(contoCaratteri*(12.702/100));
        conto['F']=(int)(contoCaratteri*(2.228/100));
        conto['G']=(int)(contoCaratteri*(2.015/100));
        conto['H']=(int)(contoCaratteri*(6.094/100));
        conto['I']=(int)(contoCaratteri*(6.966/100));
        conto['J']=(int)(contoCaratteri*(0.153/100));
        conto['K']=(int)(contoCaratteri*(0.772/100));
        conto['L']=(int)(contoCaratteri*(4.025/100));
        conto['M']=(int)(contoCaratteri*(2.406/100));
        conto['N']=(int)(contoCaratteri*(6.749/100));
        conto['O']=(int)(contoCaratteri*(7.507/100));
        conto['P']=(int)(contoCaratteri*(1.929/100));
        conto['Q']=(int)(contoCaratteri*(0.095/100));
        conto['R']=(int)(contoCaratteri*(5.987/100));
        conto['S']=(int)(contoCaratteri*(6.327/100));
        conto['T']=(int)(contoCaratteri*(9.056/100));
        conto['U']=(int)(contoCaratteri*(2.758/100));
        conto['V']=(int)(contoCaratteri*(0.978/100));
        conto['W']=(int)(contoCaratteri*(2.361/100));
        conto['X']=(int)(contoCaratteri*(0.150/100));
        conto['Y']=(int)(contoCaratteri*(1.974/100));
        conto['Z']=(int)(contoCaratteri*(0.074/100));
        conto['.']=(int)(contoRighe*(100/100));
        conto[',']=(int)(contoRighe*(45.0/100));
        conto['\'']=(int)(contoRighe*(40.0/100));
        conto[':']=(int)(contoRighe*(5.0/100));
        conto['"']=(int)(contoRighe*(5.0/100));
        conto[' ']=(int)(contoCaratteri/5.1);//spazi ogni parola
        conto['\n']=contoRighe/3;
        in.close();
        return conto;
    }
    
    public static Nodo alberoHuffman(int[] freq){
        //PriorityQueue<Nodo> coda = new PriorityQueue<Nodo>();
        NodeQueue coda = new NodeQueue();
        for(int c=0; c<freq.length; c++){
            if(freq[c]>0){
                Nodo nodo = new Nodo((char) c, freq[c]);
                coda.add(nodo);
            }
        }
        while(coda.size()>1){
            Nodo l = coda.poll();
            Nodo r = coda.poll();
            Nodo rad = new Nodo(l, r);
            coda.add(rad);
        }
        return coda.poll();
    }

    /*public static String[] tabHuffman(Nodo rad){
        String[] tab = new String[InputTextFile.CHARS];

        compilaTab(rad, "", tab);
        return tab;
    }
    private static void compilaTab(Nodo n, String cod, String[] tab){
        if(n.foglia()){
            tab[n.simbolo()] = cod;
        }else{
            compilaTab(n.sinistro(), cod + "0", tab);
            compilaTab(n.destro(), cod + "1", tab);
        }
    }*/
    
    public static String[] tabHuffman(Nodo rad){
        Stack<Coppia> stack = new Stack<Coppia>();
        stack.push(new Coppia(rad, ""));
        
        String[] tab = new String[InputTextFile.CHARS];

        do{
            Coppia coppia = stack.pop();
            Nodo nodo = coppia.nodo;
            String percorso = coppia.percorso;
            
            if(nodo.foglia()){
                tab[nodo.simbolo()] = percorso;
            }else{
                stack.push(new Coppia(nodo.destro(), percorso+"1"));
                stack.push(new Coppia(nodo.sinistro(), percorso+"0"));
            }
        }while(!stack.empty());
        return tab;
    }
    
    /*public static String codificaAlbero(Nodo nodo){
        if(nodo.foglia()){
            char c = nodo.simbolo();
            if((c == '@') || (c == '\\')){
                return "\\" + c;
            }else{
                return "" + c;
            }
        }else{
            return "@" + codificaAlbero(nodo.sinistro()) + codificaAlbero(nodo.destro());
        }
    }*/
    
    public static String codificaAlbero(Nodo radice){
        Stack<Nodo> stack = new Stack<Nodo>();
        stack.push(radice);
        String codifica = "";
        do{
            Nodo nodo = stack.pop();
            
            if(nodo.foglia()){
                char c = nodo.simbolo();
                if((c == '@') || (c == '\\')){
                    codifica = codifica + "\\" + c;
                }else{
                    codifica = codifica + c;
                }
            }else{
                codifica = codifica + "@";
                stack.push(nodo.destro());
                stack.push(nodo.sinistro());
            }
        }while(!stack.empty());
        return codifica;
    }
    private static String Nome = "";
    public static void comprimi(String src, String dst){
        int[] freq = freqCar(src);
        Nodo radice = alberoHuffman(freq);
        String[] tab = tabHuffman(radice);
        Nome = src;
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        
        out.writeTextLine(""+radice.peso());
        
        while(in.textAvailable()){
            char c = in.readChar();//Carattere per carattere
            out.writeCode(tab[c]);
        }
        in.close();
        out.close();
        
    }

    /*public static Nodo ripristinaAlbero(InputTextFile in){
        char c = in.readChar();
        if(c == '@'){
            //Nodo Intermedio
            Nodo l = ripristinaAlbero(in);
            Nodo r = ripristinaAlbero(in);
            return new Nodo(l, r);
        }else{
            //Carattere
            if(c == '\\'){
                c = in.readChar();
            }
            return new Nodo(c, 0);
        }
    }*/
    
    public static Nodo ripristinaAlbero(InputTextFile in){
        Stack<Frame> stack = new Stack<Frame>();
        stack.push(new Frame());
        Nodo nodo = null;
        do{
            Frame f = stack.peek();
            switch (f.stato){
                case 0 :{
                    char c = in.readChar();
                    if(c == '@'){
                        stack.push(new Frame());
                        f.stato = 1;
                    }else{
                        if(c == '\\'){
                            c = in.readChar();
                        }
                        nodo = new Nodo(c, 0);
                        stack.pop();
                    }
                    break;
                }
                case 1 :{
                    f.sin = nodo;
                    stack.push(new Frame());
                    f.stato = 2;
                    break;
                }
                case 2 :{
                    f.des = nodo;
                    nodo = new Nodo(f.sin, f.des);
                    stack.pop();
                    break;
                }
            }
        }while(!stack.empty());
        
        return nodo;
    }
    
    public static void decomprimi(String src, String dst){
        
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        
        int[] freq = freqCar(Nome);
        Nodo radice = alberoHuffman(freq);
        int conto = Integer.parseInt(in.readTextLine());
        
        String dummy = in.readTextLine();
        for(int i=0; i<conto; i++){
            Nodo n = radice;
            do {
                int bit = in.readBit();
                n = (bit == 0) ? n.sinistro() : n.destro();
            }while (!n.foglia());
            char c = n.simbolo();
            out.writeChar(c);
        }
        in.close();
        out.close();
        
    }
}
