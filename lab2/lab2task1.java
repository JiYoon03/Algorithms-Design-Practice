/*
* Write a function  Linear Search: Performance Comparison task1 Java
@ @author JiYoon Kang
* created Jan 28, 2023
*/
import java.util.Random;

public class lab2task1 {
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
        int[] array = new int[10];
        for(int b =0;b<10;b++){
            array[b]=b;
        }
        System.out.println("TASK#1");
        int elementx =7;
        int result1 = lab2task1.linearSearch(array,10,elementx);
        System.out.println("Searching array of size: N (10)\n Algorithm: linearSearch\nLooking for element: x ("+elementx+") \nResult: "+result1);
        int result2 = lab2task1.betterLinearSearch(array,10,elementx);
        System.out.println("Algorithm: betterLinearSearch\nLooking for element: x ("+elementx+")\nResult: "+result2);
        int result3 = lab2task1.SentinelLinearSearch(array,10,elementx);
        System.out.println("Algorithm: SentinelLinearSearch\nLooking for element: x ("+elementx+")\nResult: "+result3);
        int result4 = lab2task1.RecursiveLinearSearch(array,10,0,elementx);
        System.out.println("Algorithm: RecursiveLinearSearch\nLooking for element: x ("+elementx+")\nResult: "+result4);
        System.out.println("===============================================");

    }
    
}
