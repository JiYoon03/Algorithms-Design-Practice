import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/*
* Write a function Lab11: Data Compression: LZW Algorithm
* created Apr 16, 2023
*/
public class LZW{
    public String readFile(String filename) {
        StringBuffer sb = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new
        FileReader(filename))) {
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        } catch (Exception ee) {
            System.err.println("Problem reading file" + ee);
        }
        System.out.println("Bytes read " + sb.length());
        return sb.toString();
    }
    public static void writeFile(List<Integer> compressedName){
         try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("BW.bin"))) {
            for(int i =0;i<compressedName.size();i++){
                int writeGet = compressedName.get(i);
                dos.writeInt(writeGet);
            }
        } catch (Exception ee) {
            System.err.println(ee);
        }
    }
    public static List<Integer> lzwcompression(String text){
        int dp = 256;
        Map<String,Integer> dictionary = new HashMap<>();
        for (int j= 0;j<dp;j++){
            dictionary.put(String.valueOf((char)j),j);
        }
        String foundCh = "";
        List<Integer> result = new ArrayList<>();
        for(char ch : text.toCharArray()){
            String chAdd = foundCh + ch;
            if(dictionary.containsKey(chAdd)){
                foundCh = chAdd;
            }else{
                result.add(dictionary.get(foundCh));
                dictionary.put(chAdd,dp++);
                foundCh = String.valueOf(ch);
            }
        }
        if(!foundCh.isEmpty()){
            result.add(dictionary.get(foundCh));
        }
        System.out.println("# entries in Dictionary (compress): dp= "+dp);//+" dictionary hashmap.size()="+dictionary.size()
        return result;
    }
    public static void main (String[] args){
        LZW Reader = new LZW();
        String sample = "It was the best of times, it was the worst of times.";
        System.out.println("the string: "+sample);

        int sizeOfText1 = sample.getBytes().length;//same as sample.length()
        System.out.println("the size of text: "+sizeOfText1);
        List<Integer> compressed = lzwcompression(sample);
        int outputOfComp = 4 * compressed.size();
        System.out.println("the output of compress: "+outputOfComp);
        System.out.println(compressed);

        String macbeth = "That struts and frets his hour upon the stage, and then is heard no more. It is a tale told by an idiot, full of sound and fury, signifying nothing.";
        int sizeOfMac = macbeth.getBytes().length;
        System.out.println("Macbeth) the size of text: "+sizeOfMac);
        List<Integer> compressed2 = lzwcompression(macbeth);
        int outputOfComp1 = 4* compressed2.size();
        System.out.println("the output of compress: "+outputOfComp1);
        System.out.println(compressed2);

        String mobyDick = Reader.readFile("moby10b.txt");
        int sizeOfMD = mobyDick.getBytes().length;
        System.out.println("MobyDick) the size of text: "+sizeOfMD);
        List<Integer> compressed3 = lzwcompression(mobyDick);
        int outputOfComp2 = 4*compressed3.size();
        System.out.println("the output of compress: "+outputOfComp2);
        //System.out.println(compressed3);
        writeFile(compressed3);
    }

}