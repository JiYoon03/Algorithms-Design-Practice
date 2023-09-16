/*
* Write a function isOdd with java for question 7
@ @author JiYoon Kang
* created Jan 23, 2023
*/

public class Lab1q7 {
   static void isOdd(int n) {
        if(n%2==1){
            System.out.println(n+" true");
        }else{
            System.out.println(n+" false");
        }
      }
     public static void main(String[] args) {
        System.out.println("Java run isOdd fuction");
        for(int i=0;i<6;i++){
            Lab1q7.isOdd(i);
	    }
    }
}