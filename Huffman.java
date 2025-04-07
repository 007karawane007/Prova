import java.util.*;
import huffman_toolkit.*;

public class Huffman {
    public static int[] freqCar(String src){
        InputTextFile in = new InputTextFile(src);
        int[] conto = new int[InputTextFile.CHARS];//128
        for(int i=0; i<conto.length; i++){
            conto[i]=0;
        }
        while(in.textAvailable()){
            char c = in.readChar();//Carattere per carattere
            conto[c] = conto[c] + 1;
        }
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
    
    public static void comprimi(String src, String dst){
        int[] freq = freqCar(src);
        Nodo radice = alberoHuffman(freq);
        String[] tab = tabHuffman(radice);

        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);

        out.writeTextLine(""+radice.peso());
        out.writeTextLine(codificaAlbero(radice));

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
        
        //Nodo radice = ;
        int conto = Integer.parseInt(in.readTextLine());
        Nodo radice = ripristinaAlbero(in);
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
}//class Huffman
