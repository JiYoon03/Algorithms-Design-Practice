import java.io.File;
import java.io.BufferedReader;    
import java.io.FileReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
public class nextfit {
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
    public static void lowerbound(int weight[], int c){
        int tw =0; //sum of all package weights
        for(int i =0;i < weight.length; i++){
            tw += weight[i];
        }
        int b = tw/c;
        int floorB = (int)Math.floor(b);
        if(b == floorB){
        System.out.println("minimum number of containers is B: "+ b);
        }else{
        System.out.println("minimum number of containers is B: "+ (floorB+1));
        } 
    }
    public static int nextFit(int weight[], int c){
        int wc=0;  // weight of the current package 
        int tw=0;  // the amount of weight in the current bin
        int bc=1;// the number of bins used
 
        for (int i = 0; i < weight.length; i++) {
            wc = weight[i];
            if (tw+wc > c) {// If this item can't fit in current bin
                bc++;
                tw =wc;
            }else{
                tw= tw+wc;
            }
        }
        return bc;
    }
    public static void main(String[] args){
        nextfit Reader = new nextfit();
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
        int result = nextFit(weightOnLine,c);
        long end = System.nanoTime();

        long s1 = System.nanoTime();
        int resultA = nextFit(weightOffAsc,c);
        long e1 = System.nanoTime();

        long s2 = System.nanoTime();
        int resultD = nextFit(weightOffDec,c);
        long e2 = System.nanoTime();

        long execution = (end - start)/1000;
        long executionA = (e1 - s1)/1000;
        long executionD = (e2 - s2)/1000;
        
        System.out.print("theoretical lower bound: ");
        lowerbound(weightOnLine,c);
        System.out.println("Algorithm          Bins   Speed(microseconds)");
        System.out.println("NextFit On-Line:    " + result+"      "+execution);
        System.out.println("asc SortedNextFit:  " + resultA+"      "+executionA);
        System.out.println("desc SortedNextFit: " + resultD+"      "+executionD);
    }
}