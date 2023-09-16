/*
* Write a function Lab8: Dijkstra’s Shortest Path Algorithm
* created Mar 16, 2023
* READING THE FILE filename <- "Lab08Data/tinyEWD.txt" THIS
* READING THE FILE filename2<-  "Lab08Data/mediumEWD.txt"
*/
import java.util.PriorityQueue;
import java.util.*;
import java.io.File;
import java.util.Queue;
import java.util.Comparator;
import java.io.BufferedReader;   
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
 
class Element implements Comparable<Element> {
    int index;
    int distance;
 
    public Element(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
 
    @Override
    public int compareTo(Element o) {
       return this.distance - o.distance;
    }
}
 
public class DijkstraTest {
    static boolean[] visited;
    static int[] dist;
    static int[][] ad;
    static double[] result;
    //static int E = 9, V = 6;
    static int E;
    static int V;
    static int inf = 100000000;

 
    public static Map<Integer,ArrayList<Integer>> DijkstaMethod(int start) {
        PriorityQueue<Element> pq = new PriorityQueue<>();
        dist[start] = 0;
        Map<Integer,ArrayList<Integer>> hashtrack = new HashMap<>();
        pq.offer(new Element(start, dist[start]));
        for(int addH =0; addH<V;addH++){
            hashtrack.put(addH,new ArrayList<Integer>());
            if(addH==start){
            hashtrack.get(addH).add(addH);
            }
        }
 
        while(!pq.isEmpty()) {
            int cost = pq.peek().distance;
            int here = pq.peek().index;
            pq.poll();
 
            if (cost > dist[here]) {   // continue if cost(distance) is greater than the value currently in the array(shortest distance)
                continue;
            }
 
            //System.out.print("current place"+here + " ");
 
            for (int i = 0; i < V; ++i) {   // update
           
                if (ad[here][i] != 0 && dist[i] > (dist[here] + ad[here][i])) {
                    
                    dist[i] = dist[here] + ad[here][i];
                     //System.out.println("here: "+here+ " i: "+i+ "ad:"+ ad[here][i]);
                     ArrayList adding = hashtrack.get(here);
                     int addingsize =  Integer.valueOf(adding.size());
                     for(int adder =0; adder<adding.size();adder++){
                        int adderValue =  (int)adding.get(adder);
                        hashtrack.get(i).add(adderValue);
                        //System.out.println(hashtrack.get(i));
                     }
                    hashtrack.get(i).add(i);
                    pq.offer(new Element(i, dist[i]));
                }
            }
 
            //System.out.println();
 
            for (int i = 0; i <= V; ++i) {   
                //System.out.print(dist[i] + " ");// dist current result
                int pass = dist[i];
                double draftDis = Double.valueOf(pass);
                double realDis = draftDis/100000;//100000/ 100
                result[i]=realDis;
                if(i==V){
                    //System.out.println();
                }
            }
            
            }
        return hashtrack;
    }


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
        lab8 Reader = new lab8();
       String filename = "Lab08Data/tinyEWD.txt";
       String filename2 = "Lab08Data/mediumEWD.txt";
       String filename3 = "Lab08Data/hugeEWD.txt";
       ArrayList graphTiny = Reader.readFile(filename2);//filename / filename2 /  filename3
        String vnum = graphTiny.get(0).toString();
        V = Integer.parseInt(vnum);
       String edgenum = graphTiny.get(1).toString();
        E = Integer.valueOf(edgenum);
        result = new double[V+1];
        visited = new boolean[V + 1];
        dist = new int[V + 1];
        ad = new int[V + 1][V + 1];
 
        for (int i = 0; i < V; ++i) {
            dist[i] = inf;
        }

       for(int i =2;i<graphTiny.size();i++){
           String listGT = graphTiny.get(i).toString();
           
           //System.out.println(graphTiny.get(i));
           String [] listofGT = listGT.split("\\s+");
           String first = String.valueOf(listofGT[0]);
           int firstI = Integer.parseInt(first);
           //System.out.println(firstI);
           String second = String.valueOf(listofGT[1]);
           int secondI = Integer.parseInt(second);
           String dist = String.valueOf(listofGT[2]);
           //System.out.println(dist);
           double distI = Double.parseDouble(dist)*100000;//100000/ 100
           int passDistI = (int)distI;
           //System.out.println(passDistI);
           ad[firstI][secondI]= passDistI;
        } 

        //
        Scanner scanner = new Scanner(System.in);
    System.out.print("Task1) Enter the source vertex: ");
    String sourceV = scanner.nextLine();
    int sourceVInt =  Integer.parseInt(sourceV);
    if(sourceV.equals("-1")){
        System.out.println("good bye");
    }else{
        System.out.print("Enter the destination vertex: "); 
        String destinV = scanner.nextLine();   
        System.out.println(destinV);
        int destinVInt = Integer.parseInt(destinV);
        Map<Integer,ArrayList<Integer>> resultHashMap= DijkstaMethod(sourceVInt);
        System.out.println(resultHashMap.get(destinVInt));
        //System.out.println(resultHashMap);
        ArrayList resultHashMapAL = resultHashMap.get(destinVInt);
        //System.out.println(resultHashMapAL.size());
        for(int printcount = 0; printcount<resultHashMapAL.size()-1;printcount++){
            System.out.print(resultHashMapAL.get(printcount)+" --> "+resultHashMapAL.get(printcount+1)+"   :");
            System.out.println(ad[(int)resultHashMapAL.get(printcount)][(int)resultHashMapAL.get(printcount+1)]*0.00001);//0.00001/0.01
        }
        System.out.println("The shortest path has a cost:"+result[destinVInt]);
    }
    for (int i = 0; i < V; ++i) {
            dist[i] = inf;
        }
    System.out.println("TASK2) MOST EXPENSIVE");
    String fromV = scanner.nextLine();
    int fromVInt =  Integer.parseInt(fromV);
    //System.out.println(fromVInt);
    double expesivest = 0.0;
    int expenNode = 0;
    Map<Integer,ArrayList<Integer>> resultHashMap2= DijkstaMethod(fromVInt);
    int countrechableNode =0;
    for(int expen = 0; expen<V;expen++){
        System.out.println(" "+expen+" : "+result[expen]);
        if(result[expen]!=1000000.0 ){
            countrechableNode++;
        }
        if(result[expen]!=1000000.0 &&expesivest<result[expen]){//change it to comparsion 33
            expesivest= result[expen];
            expenNode = expen;
        }
    }
    System.out.println("FROM : "+ fromV+" CAN GET TO "+countrechableNode+" nodes\n MOST EXPENSIVE to get to is node "+expenNode+" with a cost of "+expesivest);

    }
}