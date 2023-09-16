/*
* Write a function Inverted Indexes in Java
* created Feb 18, 2023
*/
import java.io.File;
import java.io.BufferedReader;    
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GGReader {
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
    
    public static void main(String[] args) {
        class WordDocumentLoc{
            String documentId;
            int wordLoc;
        }
        GGReader myReader = new GGReader();
        String fileFolder = args[0];
        Map<String, List<WordDocumentLoc>> mapOfFolder = new HashMap<>();//set of documents IDs, one document per file
        for(int i=1;i<415;i++){
            String filename = fileFolder+"/AustenOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
           if(sizeofWords<=3){//when arr size is smaller than 3, it does not contain list of the words~
               //do nothing
           }else{
               for(int j= 0;j<sizeofWords;j++){
                   WordDocumentLoc docuID = new WordDocumentLoc();//creat new WordDocumentLoc
                   docuID.documentId = "AustenOne_"+Integer.toString(i)+".txx";
                   //System.out.println(docuID.documentId);
                   docuID.wordLoc = j;
                   String singleWord = String.valueOf(words.get(j));
                   if (mapOfFolder.containsKey(singleWord)) {
                        mapOfFolder.get(singleWord).add(docuID);
                    } else {
                        mapOfFolder.put(singleWord, new ArrayList<WordDocumentLoc>());
                        mapOfFolder.get(singleWord).add(docuID);
                    }
               }
           }
        }
        for(int i=1;i<566;i++){
            String filename = fileFolder+"/GibonOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
           if(sizeofWords<=3){//when arr size is smaller than 3, it does not contain list of the words~
               //do nothing
           }else{
               for(int j= 0;j<sizeofWords;j++){
                   WordDocumentLoc docuID = new WordDocumentLoc();//creat new WordDocumentLoc
                   docuID.documentId = "GibonOne_"+Integer.toString(i)+".txx";
                   //System.out.println(docuID.documentId);
                   docuID.wordLoc = j;
                   String singleWord = String.valueOf(words.get(j));
                   if (mapOfFolder.containsKey(singleWord)) {
                        mapOfFolder.get(singleWord).add(docuID);
                    } else {
                        mapOfFolder.put(singleWord, new ArrayList<WordDocumentLoc>());
                        mapOfFolder.get(singleWord).add(docuID);
                    }
               }
           }
        }
        for(int i=1;i<394;i++){
            String filename = fileFolder+"/ScottOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
           if(sizeofWords<=3){//when arr size is smaller than 3, it does not contain list of the words~
               //do nothing
           }else{
               for(int j= 0;j<sizeofWords;j++){
                   WordDocumentLoc docuID = new WordDocumentLoc();//creat new WordDocumentLoc
                   docuID.documentId = "ScottOne_"+Integer.toString(i)+".txx";
                   //System.out.println(docuID.documentId);
                   docuID.wordLoc = j;
                   String singleWord = String.valueOf(words.get(j));
                   if (mapOfFolder.containsKey(singleWord)) {
                        mapOfFolder.get(singleWord).add(docuID);
                    } else {
                        mapOfFolder.put(singleWord, new ArrayList<WordDocumentLoc>());
                        mapOfFolder.get(singleWord).add(docuID);
                    }
               }
           }
        }
        //test that it get correct word list 
        List<WordDocumentLoc> name1 = mapOfFolder.get("bonnie");
        System.out.println("bonnie");
        for(int a = 0; a< name1.size();a++){
            System.out.println("{"+name1.get(a).documentId+" "+name1.get(a).wordLoc+"}");
        }
        System.out.println();
        List<WordDocumentLoc> name2 = mapOfFolder.get("Caligula");
        System.out.println("Caligula");
        for(int b = 0; b< name2.size();b++){
            System.out.println("{"+name2.get(b).documentId+" "+name2.get(b).wordLoc+"}");
        }
        System.out.println();
    }
}  
