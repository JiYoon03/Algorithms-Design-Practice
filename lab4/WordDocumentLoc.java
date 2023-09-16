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
import java.lang.Math;
import java.util.Random;

public class WordDocumentLoc {
    String documentId;
    int wordLoc;

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
    static WordDocumentLoc[] getInvertedIndexes(String word, Map<String, List<WordDocumentLoc>> mapOfFolder){
           List<WordDocumentLoc> name = mapOfFolder.get(word);
           WordDocumentLoc[] listOfWD = new WordDocumentLoc[name.size()];
           //System.out.println(word);
           for(int b = 0; b< name.size();b++){
                listOfWD[b]= new WordDocumentLoc();
                listOfWD[b].documentId= name.get(b).documentId;
                listOfWD[b].wordLoc=name.get(b).wordLoc;
                //test it prints correct values
                //System.out.println("{"+name.get(b).documentId+" "+name.get(b).wordLoc+"}");
           }
           return listOfWD;
    }
    static ArrayList<String>  listOfTwoContain(String word1,String word2, Map<String, List<WordDocumentLoc>> mapOfFolder){
        WordDocumentLoc[] word1List = getInvertedIndexes(word1,mapOfFolder);
        WordDocumentLoc[] word2List = getInvertedIndexes(word2,mapOfFolder);
        ArrayList<String> listOfTC = new ArrayList<>();
        for(int i =0; i<word1List.length;i++){
            for(int j =0;j<word2List.length;j++){
                if(word1List[i].documentId.equals(word2List[j].documentId)&&!listOfTC.contains(word2List[j].documentId)){
                    if(!listOfTC.contains(word1List[i])&&!listOfTC.contains(word2List[j])){
                        listOfTC.add(word1List[i].documentId);
                    }
                }
            }
        }
        for(int num =0; num<listOfTC.size();num++){
            System.out.printf(listOfTC.get(num)+" ");
        }
        System.out.println();
        return listOfTC;
        
    }

    static WordDocumentLoc[] betterListOfTwoContain(String word1,String word2, Map<String, List<WordDocumentLoc>> mapOfFolder){
        WordDocumentLoc[] word1List = getInvertedIndexes(word1,mapOfFolder);
        WordDocumentLoc[] word2List = getInvertedIndexes(word2,mapOfFolder);
        ArrayList<String> listOfTC = listOfTwoContain(word1,word2,mapOfFolder);

        WordDocumentLoc[] listOfBTC = new WordDocumentLoc[listOfTC.size()];        
        for(int num =0; num<listOfTC.size();num++){//go over the both contain documentID
            int[] w1 = new int[word1List.length];
            int[] w2 = new int[word2List.length];
            int countw1 =0;
            int countw2 =0;
            int idx1 = 0; // index in W1
            int idx2 = 0; // index in W2
            //System.out.println(listOfTC.get(num));
            for(int i =0; i<word1List.length;i++){
                if(word1List[i].documentId.equals(listOfTC.get(num))){
                    w1[countw1]=word1List[i].wordLoc;
                    countw1++;
                }
            }
            for(int j =0; j<word2List.length;j++){
                if(word2List[j].documentId.equals(listOfTC.get(num))){
                    w2[countw2]=word2List[j].wordLoc;
                    countw2++;
                }
            }
            int d = Math.abs(w1[idx1]-w2[idx2]);
            while(idx1<countw1 && idx2<countw2){
                if(Math.abs(w1[idx1]-w2[idx2])<d){
                    d = Math.abs(w1[idx1]-w2[idx2]);
                }
                if(w1[idx1]<w2[idx2]){
                    idx1++;
                }else{
                    idx2++;
                }
            }
            listOfBTC[num]= new WordDocumentLoc();
            listOfBTC[num].documentId = listOfTC.get(num);
            listOfBTC[num].wordLoc = d;
            //System.out.println("{"+listOfBTC[num].wordLoc+" "+listOfBTC[num].documentId+"}");//test it print the correct number of minimum distance and name of documentId
        }
        for(int num =0; num<listOfBTC.length;num++){
			WordDocumentLoc key = listOfBTC[num];
			int j =num-1;
			while(j>=0 && listOfBTC[j].wordLoc>key.wordLoc){
				listOfBTC[j + 1] =listOfBTC[j];
				j=j-1;
			}
		    listOfBTC[j+1] = key;
		}
        for(int num =0; num<listOfBTC.length;num++){
            int order = num +1;
            System.out.println("{"+order+"      "+listOfBTC[num].wordLoc+" "+listOfBTC[num].documentId+"}");//test it print the correct number of minimum distance and name of documentId
        }
        return listOfBTC;
        
    }

    public static void main(String[] args) {
        WordDocumentLoc myReader = new WordDocumentLoc();
        String fileFolder = args[0];
        int countwd=0;
        int allwd=0;
        Map<String, List<WordDocumentLoc>> mapOfFolder = new HashMap<>();//set of documents IDs, one document per file
        for(int i=1;i<415;i++){
            String filename = fileFolder+"/AustenOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
            allwd += sizeofWords;
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
                        countwd++;
                    }
               }
           }
        }
        for(int i=1;i<566;i++){
            String filename = fileFolder+"/GibonOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
            allwd += sizeofWords;
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
                        countwd++;
                    }
               }
           }
        }
        for(int i=1;i<394;i++){
            String filename = fileFolder+"/ScottOne_"+Integer.toString(i)+".txx";
            //System.out.println(filename);//test that it get correct name 
            ArrayList words = myReader.readFile(filename);
            int sizeofWords = words.size();
            allwd += sizeofWords;
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
                        countwd++;
                    }
               }
           }
        }/*
        System.out.println(countwd);
        System.out.println(allwd);
        //test that it get correct word list 
        //print without function getInvertedIndexes
        */
        List<WordDocumentLoc> name1 = mapOfFolder.get("bonnie");
        System.out.println("bonnie");
        for(int a = 0; a< name1.size();a++){
            System.out.println("{"+name1.get(a).documentId+" "+name1.get(a).wordLoc+"}");
        }
        System.out.println();
        List<WordDocumentLoc> name2 = mapOfFolder.get("caligula");
        System.out.println("caligula");
        for(int b = 0; b< name2.size();b++){
            System.out.println("{"+name2.get(b).documentId+" "+name2.get(b).wordLoc+"}");
        }
        System.out.println();
        //test bonnie and Caligula
        WordDocumentLoc[] bonnie = getInvertedIndexes("bonnie",mapOfFolder);
        WordDocumentLoc[] caligula = getInvertedIndexes("caligula",mapOfFolder);
        System.out.println(caligula[0].documentId);
        System.out.println(caligula[0].wordLoc);
        // find txx file that contain both words.
        ArrayList<String> containTwo= listOfTwoContain("elizabeth","emma",mapOfFolder);

        WordDocumentLoc[] containTwoBetter= betterListOfTwoContain("elizabeth","emma",mapOfFolder);

        WordDocumentLoc[] containTwoBetter1= betterListOfTwoContain("roy","clan",mapOfFolder); 
        WordDocumentLoc[] containTwoBetter2= betterListOfTwoContain("legend","legion",mapOfFolder);
    }
}  