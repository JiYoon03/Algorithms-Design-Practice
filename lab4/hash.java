/*
* Write a Hash Tables & Hash Functions  in Java
* created Feb 26-27, 2023
*/
import java.io.File;
import java.io.BufferedReader;    
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Hashtable;
import java.util.Map;
import java.lang.Math;
import java.util.Random;
import java.lang.reflect.Method;
import java.util.Scanner;  // Import the Scanner class


public class hash{ 
    public static int horner(String s){
        int sm = 0;
        int mul = 80;
        for (int j = 0; j < s.length(); j++) {
        int letter = s.charAt(j) - 96; // For lowercase
        sm = sm * mul + letter;
        }
        return sm;
    }
    
    public ArrayList readFile(String fileName) {
        ArrayList arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                arr.add(line.trim().toLowerCase());
            }
            return arr;
        } catch (Exception ee){
            System.err.println(ee);                
            return arr;
        }
    }
 
    public static void main(String[] args) {
        WordDocumentLoc myReader = new WordDocumentLoc();
        String fileFolder = args[0];
        int countNewWd=0;//count new word
        int allwd=0;
        Hashtable<String, Integer> hashT = new Hashtable<>();
        for(int i=1;i<415;i++){
            String filename = fileFolder+"/AustenOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
            allwd += sizeofWords;
            for(int j= 0;j<sizeofWords;j++){
                String singleWord = String.valueOf(words.get(j));
                if (hashT.containsKey(singleWord)) {
                    int countW = hashT.get(singleWord);
                    countW++;
                    hashT.put(singleWord,countW);
                } else {
                    hashT.put(singleWord,1);
                    countNewWd++;
                }       
            }   
        }
        for(int i=1;i<566;i++){
            String filename = fileFolder+"/GibonOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
            allwd += sizeofWords;
            for(int j= 0;j<sizeofWords;j++){
                String singleWord = String.valueOf(words.get(j));
                if (hashT.containsKey(singleWord)) {
                    int countW = hashT.get(singleWord);
                    countW++;
                    hashT.put(singleWord,countW);
                } else {
                    hashT.put(singleWord,1);
                    countNewWd++;
                }       
            }
        }
        for(int i=1;i<394;i++){
            String filename = fileFolder+"/ScottOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
            allwd += sizeofWords;
            for(int j= 0;j<sizeofWords;j++){
                String singleWord = String.valueOf(words.get(j));
                if (hashT.containsKey(singleWord)) {
                    int countW = hashT.get(singleWord);
                    countW++;
                    hashT.put(singleWord,countW);
                } else {
                    hashT.put(singleWord,1);
                    countNewWd++;
                }       
            }
        }
        //System.out.println("total number of number of key words in hashtable: "+countNewWd);
        //total number of number of key words in hashtable: 69568
        //Task3
        int range = 560000;
        int[] taskThreeOne = new int[range];
        for(int i =0;i<range;i++){
            taskThreeOne[i]=0;
        }
        for (Map.Entry<String, Integer> entry : hashT.entrySet()){
            String key =entry.getKey(); 
            int wordHash = key.hashCode();
            int index = Math.abs(wordHash);
            index = index% range;
            int currentVal = taskThreeOne[index];
            taskThreeOne[index]=currentVal+1;
        }
        int loadFatcor = 0;
        int numOfCollision =0;
        for(int i =0;i<range;i++){
            if(taskThreeOne[i]==0){
                loadFatcor++;
            }
            if(taskThreeOne[i]>1){
                numOfCollision += (taskThreeOne[i]-1);
            }
        }
        //System.out.println("N: "+range+" # collision: "+numOfCollision+" load factor: "+loadFatcor);
        
        //TASK 5
        int range2 = 560000;
        int[] taskFive = new int[range2];
        for(int i =0;i<range2;i++){
            taskFive[i]=0;
        }
        for (Map.Entry<String, Integer> entry : hashT.entrySet()){
            String key2 =entry.getKey(); 
            int wordHash2 = horner(key2);
            int index2 = Math.abs(wordHash2);
            index2 = index2% range2;
            int currentVal2 = taskFive[index2];
            taskFive[index2]=currentVal2+1;
        }
        int loadFatcor2 = 0;
        int numOfCollision2 =0;
        for(int i =0;i<range2;i++){
            if(taskFive[i]==0){
                loadFatcor2++;
            }
            if(taskFive[i]>1){
                numOfCollision2 += (taskFive[i]-1);
            }
        }
        System.out.println("N: "+range2+" # collision: "+numOfCollision2+" load factor: "+loadFatcor2);

        /*
        for (Map.Entry<String, Integer> e : hashT.entrySet()){
                System.out.println(e.getKey() + " " + e.getValue());//test of the program runs correctly
        }
        //TASK 1-2 test based on GibonOne_95.txx
        
        String filename = fileFolder+"/GibonOne_95.txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
            allwd += sizeofWords;
            for(int j= 0;j<sizeofWords;j++){
                String singleWord = String.valueOf(words.get(j));
                if (hashT.containsKey(singleWord)) {
                    int countW = hashT.get(singleWord);
                    countW++;
                    hashT.put(singleWord,countW);
                } else {
                    hashT.put(singleWord,1);
                    countNewWd++;
                }       
            }
        System.out.println("The concordance has "+countNewWd+"unique words");
        System.out.println("Query the Concordance!");
        Scanner myObj = new Scanner(System.in);
        while(true){
            System.out.println("Word to ask about (hit return to quit):");
            String inputword = myObj.nextLine();
            if(inputword==""){
                break;
            }
            if(hashT.get(inputword)==null){
                System.out.println(inputword+" is not in the concordance");
            }else{
            System.out.println(inputword+" apperas "+hashT.get(inputword)+" times");
            }
        }
        
        //Syst em.out.println(hashT.get("chapter"));//teseter
        
        String b = "Bryn Mawr";//hashCode() & Horner’s method example 
        String k = "k-Cass";
        String h = "Haverford";
        String s = "Swathmore";
        //task2
        System.out.println(b+"'s compute the hash codes is "+b.hashCode());
        System.out.println(k+"'s compute the hash codes is "+k.hashCode());
        System.out.println(h+"'s compute the hash codes is "+h.hashCode());
        System.out.println(s+"'s compute the hash codes is "+s.hashCode());
        //task4
        System.out.println(b+"'s compute the hash codes is "+horner(b));
        System.out.println(k+"'s compute the hash codes is "+horner(k));
        System.out.println(h+"'s compute the hash codes is "+horner(h));
        System.out.println(s+"'s compute the hash codes is "+horner(s));
        */
    }
}