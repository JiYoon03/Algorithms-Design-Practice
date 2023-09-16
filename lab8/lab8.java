/*
* Write a function Lab8: Dijkstra’s Shortest Path Algorithm
* created Mar 16, 2023
*/
import java.util.*;
import java.io.File;
import java.util.Queue;
import java.io.BufferedReader;   
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
public class lab8{
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
//here
  public static int[] dijkstra(String start, Map<String, Map<String, String>>mapOfGT1){
       int vnum = mapOfGT1.size();
       int [] shortest = new int[vnum];
       String [] pred = new String[vnum];
       int checkerstart = Integer.parseInt(start);
       
       shortest[0]=0;
       pred[0]=null;
       int counter =0;
       while(counter<vnum){
        if(counter==checkerstart){
            shortest[counter]=0;
        }else{
            Queue<String> que = new PriorityQueue<>();
            Map<String, String> startlist = mapOfGT1.get(start);
            String stringcounter = String.valueOf(counter);
            for(int j =0;j<startlist.size();j++ ){
                String value = (String) startlist.keySet().toArray()[j];
                while(value.equals(stringcounter)){
                    que.add(value);
                    vnum--;
                    shortest[Integer.valueOf(value)]= Integer.valueOf(startlist.get(value));
                    System.out.println(counter+" "+j+" Value: " + value);


                }

            }
        }
        
       }

       

       
    return shortest;
  }

   public static void main(String[] args) {
       lab8 Reader = new lab8();
       String filename = "Lab08Data/tinyEWD.txt";
       ArrayList graphTiny = Reader.readFile(filename);
       Map<String, Map<String, String>> mapOfGT = new HashMap<>();
        //making the map!!
        String vnum = graphTiny.get(0).toString();
        int verticenum = Integer.parseInt(vnum);
        String edgenum = graphTiny.get(1).toString();
        int edgenumI = Integer.valueOf(edgenum);

       for(int i =2;i<graphTiny.size();i++){
           String listGT = graphTiny.get(i).toString();
           System.out.println(graphTiny.get(i));
           String [] listofGT = listGT.split(" ");
           String mainNode = String.valueOf(listofGT[0]);
           if(mapOfGT.containsKey(mainNode)){
               mapOfGT.get(listofGT[0]).put(listofGT[1],listofGT[2]);
           }else{
               mapOfGT.put(listofGT[0],new HashMap<>());
               if(listofGT.length>1){
                   mapOfGT.get(listofGT[0]).put(listofGT[1],listofGT[2]);
               }
           }
           System.out.println(mapOfGT.get(listofGT[0]));
        }
    System.out.println(mapOfGT.get("6").get("0"));//it prints 0.58
    //NOW GET THE source vertex & destination vertex
    Map<String, Queue<Integer>> mapOfshortest = new HashMap<>();
    for( String keys : mapOfGT.keySet() ){
        //Queue<Integer> elementQue = dijkstra()//여기 새롭게 만드ㅡ느느

    }




    Scanner scanner = new Scanner(System.in);
    System.out.print("Task1) Enter the source vertex: ");
    String sourceV = scanner.nextLine();
    Map<String, String> printlist = mapOfGT.get(sourceV);
    if(sourceV.equals("-1")){
        System.out.println("good bye");
    }else if(printlist.size() == 0){
        System.out.println(sourceV+" has no neighbors vertexs.");
    }else{
        System.out.println(sourceV+" has the following neighbors vertexs:");
        for( Map.Entry<String, String> entry : printlist.entrySet() ){
            System.out.println( entry.getKey() + " = " + entry.getValue() );
        } 
        System.out.print("Enter the destination vertex: "); 
        String destinV = scanner.nextLine();   
        System.out.println(destinV);
        for( String keys : printlist.keySet() ){
            //System.out.println( keys );
            if(keys.equals(destinV)){
                System.out.println("The shortest path has a cost "+printlist.get(keys)+" Here	it is: ");
                System.out.println(sourceV+"-> "+keys+" "+printlist.get(keys));
            }
            
        }
        String value = (String) printlist.values().toArray()[0];
        System.out.println("Value: " + value);
    }
    

}
}

