/*
* Write a function Lab7:Simple Graph Algorithms
* created Mar 12, 2023
*/
import java.io.File;
import java.io.BufferedReader;    
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


public class lab7 {
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
    public static ArrayList recursiveMaze(Map<String, ArrayList<String>> mapOfUS,ArrayList path,ArrayList pathtrack, String toState){
        ArrayList currentpath = path;
        String lastPath = path.get(path.size()-1).toString();//bring the last state 
        ArrayList lastStatElem = mapOfUS.get(lastPath);
        if(lastPath.equals(toState)){
            return currentpath;
        }else if(mapOfUS.get(toState).isEmpty()){
            return null;
        }else if(pathtrack.size()>0){
            for(int num=0;num<lastStatElem.size();num++){
                String elementStat = lastStatElem.get(num).toString();
                char firstletter = elementStat.charAt(0);
                if(firstletter == 'W'||firstletter == 'O'||firstletter == 'M'||firstletter == 'A'||firstletter == 'N'){
                    int checker =0;
                    for(int i = 0; i < currentpath.size(); i++) { //if the state is already in the list of way to state
                        String storeSta = currentpath.get(i).toString();
                        if(storeSta.equals(elementStat)){
                            checker+=1;
                        }
                    }
                    //System.out.println(checker);
                    if(checker ==0&&!elementStat.equals(currentpath.get(0))){
                        pathtrack.add(elementStat);
                        currentpath.add(elementStat);
                        System.out.println(elementStat);
                        System.out.println(pathtrack);
                        if(pathtrack.size()>2){
                            String findNO = pathtrack.get(num).toString();
                            String findNo2 = pathtrack.get(num-2).toString();
                            if(findNO.equals(findNo2)){
                              pathtrack.clear();
                            }
                        }
                        ArrayList recur = recursiveMaze(mapOfUS,currentpath,pathtrack,toState);
                        String newLastAddS = recur.get(recur.size()-1).toString();
                            if(recur.size()>1&& !newLastAddS.equals(lastPath)){
                                return recur;
                            }else{
                                recur.remove(recur.size()-1);
                            }
                    }
                    else{
                        currentpath.remove(currentpath.size()-1);
                    }
                }    
            }
        }return null;
    }

    public static void main(String[] args) {
        lab7 Reader = new lab7();
        String filename = "USStates.csv";
        ArrayList words = Reader.readFile(filename);
        Map<String, ArrayList<String>> mapOfUS = new HashMap<>();
        for(int i = 1; i < words.size(); i++) {   
            String listS = words.get(i).toString(); 
            //System.out.println(words.get(i)); SUCCESS TO PRINT TOTAL ELEMENTS IN CSV FILE
            String[] listofStates = listS.split("[,]");
            mapOfUS.put(listofStates[0],new ArrayList<String>());
            for(int n = 1; n < listofStates.length; n++){
                mapOfUS.get(listofStates[0]).add(listofStates[n]);
            }
            //System.out.println(mapOfUS.get(listofStates[0])); //SUCCESS TO PRINT Bordering States
        } 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Task1) Enter a state: ");
        String userState = scanner.nextLine();
        ArrayList printlist = mapOfUS.get(userState);
        if(printlist.size() == 0){
            System.out.println(userState+" has no neighbors.");
        }else{
            System.out.print(userState+" has the following neighbors:");
           for(int count = 0; count < printlist.size(); count++){ 
                System.out.print(printlist.get(count));
                if(count<printlist.size()-1){
                    System.out.print(", ");
                }else{
                    System.out.println();
                }
            }
        }  
        //System.out.println(mapOfUS.get(userState));
        System.out.print("Task2) From : ");
        String fromS = scanner.nextLine();
        ArrayList startPath = new ArrayList<>();
        ArrayList trackPath = new ArrayList<>();
        startPath.add(fromS);
        System.out.print("To : ");
        String toS = scanner.nextLine();

        ArrayList finalListS = recursiveMaze(mapOfUS,startPath,trackPath, toS);
        if(finalListS==null||mapOfUS.get(fromS)==null||mapOfUS.get(toS)==null){
            System.out.println("There is no way to get from "+fromS+" to "+toS+". ");
        }else{
            System.out.print("Yes. To get from "+fromS+" to "+toS+", march as follows:");
           System.out.println(finalListS);
        }
    }
}