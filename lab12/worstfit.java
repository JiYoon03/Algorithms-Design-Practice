import java.io.File;
import java.io.BufferedReader;    
import java.io.FileReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class worstfit{
    public ArrayList readFile(String fileName) {
        ArrayList arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                arr.add(line.trim());
            }
            return arr;
        } catch (Exception ee) {
            System.err.println(ee);                
            return arr;
        }
    }
    public static void reverse(int[] array){
        int n = array.length;
        for (int i = 0; i < n / 2; i++) {
             int temp = array[i];
             array[i] = array[n - i - 1];
             array[n - i - 1] = temp;
        }
    }
    static int worstFit(int weight[], int c, int bin[]){
        int wc = 0;
        int bc = 0;
        
        // Place items one by one
        for (int i = 0; i < weight.length; i++){
            wc= weight[i];
            int j;
            int mx = -1;
            int wi = 0;
    
            for (j = 0; j < bc; j++) {
                if (bin[j] >= wc && bin[j] - wc > mx) {
                    wi = j;
                    mx = bin[j] - wc;
                }
            }
            if (mx == -1) {
                bin[bc] = c - wc;
                bc++;
            }else{
                bin[wi] -= wc;
            }
        }
        return bc;
    }
 
    public static void main(String[] args){
        worstfit Reader = new worstfit();
        String filename = "sample.txt";
        String filename1 = "d100.txt";
        String filename2 = "d1000.txt";
        String filename3 = "d10000.txt";
        String filename4 = "d100000.txt";
        String filename5 = "d1000000.txt";
        String filename6 = "d10000000.txt";
    
        ArrayList words = Reader.readFile(filename6);
        System.out.println(filename6);
        int count = words.size()-1;
        int weightOnLine[]= new int[count];
        int weightOffAsc[]=new int[count];
        int weightOffDec[]= new int[count];
        String c1s = words.get(0).toString();
        int c = Integer.parseInt(c1s);
        for(int i =1;i<words.size();i++){
            String obj = words.get(i).toString();
            int adder = Integer.parseInt(obj);
            weightOnLine[i-1]=adder;
            weightOffAsc[i-1]=adder;
            weightOffDec[i-1]=adder;
        }
        Arrays.sort(weightOffAsc);
        reverse(weightOffDec);

        long start = System.nanoTime();
        int []bin = new int[weightOnLine.length];
        int result = worstFit(weightOffDec, c,bin);
        long end = System.nanoTime();

        long s1 = System.nanoTime();
        int []bin1 = new int[weightOnLine.length];
        int resultA = worstFit(weightOffAsc,c,bin1);
        long e1 = System.nanoTime();

        long s2 = System.nanoTime();
        int []bin2 = new int[weightOnLine.length];
        int resultD = worstFit(weightOffDec,c,bin2);
        long e2 = System.nanoTime();

        long execution = (end - start)/1000;
        long executionA = (e1 - s1)/1000;
        long executionD = (e2 - s2)/1000;
        
        System.out.println("Algorithm          Bins   Speed(microseconds)");
        System.out.println("WorstFit On-Line:    " + result+"      "+execution);
        System.out.println("asc SortedWorstFit:  " + resultA+"      "+executionA);
        System.out.println("desc SortedWorstFit: " + resultD+"      "+executionD);
    }
}