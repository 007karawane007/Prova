import huffman_toolkit.*;
public class EsempiIO {
    public static int copiaFile(String src, String dst){
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        int conto = 0;
        while(in.bitsAvailable()){//in.textAvailable()
            //String linea = in.readTextLine(); //Linea per linea
            //out.writeTextLine(linea);
            //char c = in.readChar();//Carattere per carattere
            //out.writeChar(c);
            //int bit = in.readBit();//Bit a bit
            //out.writeBit(bit);
            String sette = in.readCode(7);
            out.writeCode(sette);
            
            conto++;
        }
        in.close();
        out.close();
        
        return conto;
    }
}//class Esempi IO
