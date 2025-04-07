import java.util.*;
import huffman_toolkit.*;
public class CodificaHuffman1
{
    public static void testoRandom(String dst){
        OutputTextFile out = new OutputTextFile(dst);
        for(int i=1; i<=100000; i++){
            out.writeChar((char)(128*Math.random()));
        }
        out.close();
        Huffman.comprimi(dst, "fileCompresso.txt");
        Huffman.decomprimi("fileCompresso.txt", "fileDecompresso.txt");
    }
}
