/*
* Write a function  Linear Search: Performance Comparison task2 Java
@ @author JiYoon Kang
* created Jan 28, 2023
*/
import java.util.Random;

public class lab2task2 {
    static int linearSearch(int [] A,int n ,int x ) {
        int result= -1;
        for(int i=0;i<n;i++){
            if(A[i]==x){
                result = i;
            }
        }
        return result;
    }
    
    static int  betterLinearSearch(int [] A,int n ,int x ) {
        for(int i=0;i<n;i++){
            if(A[i]==x){
                return i;
            }
        }
        return -1;
    }
    
    static int  SentinelLinearSearch(int [] A,int n ,int x ) {
        var last = A[n-1]; 
        A[n-1]=x;
        int i = 0;
        while(A[i]!=x){
            i++;
        }
        A[n-1] = last;
        if(i < n-1 || A[n-1] == x){
            return i;
        }else{
            return -1;
        }
    }
    
    static int RecursiveLinearSearch(int [] A,int n ,int i,int x ){
        if(i>=n){
          return -1;
        }else{
           if(A[i]==x){
              return i;
            }else{
               return RecursiveLinearSearch(A, n,i+1, x);
           }
       }
    }
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[1000];
        int elementx =7;
        int searchnum = rand.nextInt(100000000);
        for(int b =0;b<1000;b++){
            int randnum = rand.nextInt(50000000);
            array[b]=randnum;
        }
        System.out.println("TASK#2_Best Case: search for element");
        long sB1 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.linearSearch(array,1000,elementx);
	    }
        long fB1 = System.nanoTime();
        double tB1 = (fB1- sB1)/1000000000.0;// sec 1 = 1_000_000_000 nanosecond
        System.out.println("BEST linearSearch Runtime average:"+tB1/1000.0+"seconds");// divide 1000.0 which number of timed run
        
        long sB2 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.betterLinearSearch(array,1000,elementx);
	    }
        long fB2 = System.nanoTime();
        double tB2 = (fB2- sB2)/1000000000.0;
        System.out.println("BEST betterLinearSearch Runtime average:"+tB2/1000.0+"seconds");

        long sB3 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.SentinelLinearSearch(array,1000,elementx);
	    }
        long fB3 = System.nanoTime();
        double tB3 = (fB3- sB3)/1000000000.0;
        System.out.println("BEST SentinelLinearSearch Runtime average:"+tB3/1000.0+"seconds");

        long sB4 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.RecursiveLinearSearch(array,1000,0,elementx);
	    }
        long fB4 = System.nanoTime();
        double tB4 = (fB4- sB4)/1000000000.0;
        System.out.println("BEST RecursiveLinearSearch Runtime average:"+tB4/1000.0+"seconds");
        System.out.println("===============================================");

     
        System.out.println("TASK#2_Average Case: search for element");
        long sA1 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.linearSearch(array,1000,searchnum);
	    }
        long fA1 = System.nanoTime();
        double tA1 = (fA1- sA1)/1000000000.0;
        System.out.println("Average linearSearch Runtime average:"+tA1/1000.0+"seconds");
        
        long sA2 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.betterLinearSearch(array,1000,searchnum);
	    }
        long fA2 = System.nanoTime();
        double tA2 = (fA2- sA2)/1000000000.0;
        System.out.println("Average betterLinearSearch Runtime average:"+tA2/1000.0+"seconds");

        long sA3 = System.nanoTime();
        for (int a =0;a<1000;a++){
		   lab2task2.SentinelLinearSearch(array,1000,searchnum);
	    }
        long fA3 = System.nanoTime();
        double tA3 = (fA3- sA3)/1000000000.0;
        System.out.println("Average SentinelLinearSearch Runtime average:"+tA3/1000.0+"seconds");

        long sA4 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.RecursiveLinearSearch(array,1000,0,searchnum);
	    }
        long fA4 = System.nanoTime();
        double tA4 = (fA4- sA4)/1000000000.0;
        System.out.println("Average RecursiveLinearSearch Runtime average:"+tA4/1000.0+"seconds");
        System.out.println("===============================================");

        int worstelem = -10;
        System.out.println("TASK#2_Worst Case: search for element");
        


        long sW1 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.linearSearch(array,1000,worstelem);
	    }
        long fW1 = System.nanoTime();
        double tW1 = (fW1- sW1)/1000000000.0;
        System.out.println("Worst linearSearch Runtime average:"+tW1/1000.0+"seconds");
        
        long sW2 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.betterLinearSearch(array,1000,worstelem);
	    }
        long fW2 = System.nanoTime();
        double tW2 = (fW2- sW2)/1000000000.0;
        System.out.println("Worst betterLinearSearch Runtime average:"+tW2/1000.0+"seconds");

        long sW3 = System.nanoTime();
        for (int a =0;a<1000;a++){
		   lab2task2.SentinelLinearSearch(array,1000,worstelem);
	    }
        long fW3 = System.nanoTime();
        double tW3 = (fW3- sW3)/1000000000.0;
        System.out.println("Worst SentinelLinearSearch Runtime average:"+tW3/1000.0+"seconds");

        long sW4 = System.nanoTime();
        for (int a =0;a<1000;a++){
		    lab2task2.RecursiveLinearSearch(array,1000,0,worstelem);
	    }
        long fW4 = System.nanoTime();
        double tW4 = (fW4- sW4)/1000000000.0;
        System.out.println("Worst RecursiveLinearSearch Runtime average:"+tW4/1000.0+"seconds");
        System.out.println("===============================================");
    }
    
}
