import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZWde {     
    public List<Integer> readCompressedFile(String fileName) {
        ArrayList<Integer> ret = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            while (true) {
                ret.add(in.readInt());
            }
        } catch (EOFException ignored) {
            //System.out.println("[EOF]");
        } catch (Exception ee) {
            System.err.println("Problem " + ee);
        }
        return ret;
    }
    /** Decompress a list of output indices from LZW compress() to a string. */
    public String decompress(List<Integer> indices) {
    	//System.out.println("Decompressing...");
        int dictSize = 256;
        Map<Integer,String> codes = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            codes.put(i, "" + (char)i);
 
        StringBuffer result = new StringBuffer();
        String previous = codes.get(indices.remove(0));
        result.append(previous);

        for (int current : indices) {
            String s;
            if (codes.containsKey(current))
                s = codes.get(current);
            else if (current == dictSize)
                s = previous + previous.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed current: " + current);
 
            result.append(s);
            codes.put(dictSize++, previous + s.charAt(0));
 
            previous = s;
        }
        //System.out.println("Dictionary size: " + codes.size() + " entries.");
        return result.toString();
    } // decompress()
 
    public static void main(String[] args) {
        LZWde de = new LZWde();
        //List<Integer> compressed = de.compress("It was the best of times, it was the worst of times.");
        List<Integer> compressed = de.readCompressedFile(args[0]);
        //System.out.println(compressed);
        String decompressed = de.decompress(compressed);
        System.out.println(decompressed);
    } // main()
} // class LZWde
