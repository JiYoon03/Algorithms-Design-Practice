/*
* Write a function  Quicksort, Hybrid Quicksort,Library sort() in Java
* For task 6 the Performance Measurement
@ @author JiYoon Kang
* created Feb 6, 2023
*/
import java.util.Random;
import java.util.Arrays;


public class lab3task6 {
	static int[] initialize(int[] A,int n){
		for (int i=0;i<n;i++){
			A[i]= i+1;
		}
		return A;
	}
	static int[] fisherYates(int[] A,int n){//task1 fisher yates 
		Random rand = new Random();
		for (int i=0;i<n-2;i++){
			int j=i+rand.nextInt(n-i);//random.nextInt(max - min) + min
			int x = A[i];
			A[i]=A[j];
			A[j]=x;
		}
		return A;
	}
	static void swap(int[] A,int i,int j) {//task2 inside function swap
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}
	static void quicksort(int[] A,int left, int right){//task2 quicksort
		if(left>=right){
			return;
		}
		Random rand = new Random();
		int swapNum = left+ rand.nextInt(right-left);//range is right to left
		swap(A,left,swapNum);
		int m =left;
		for (int i =left+1;i<=right;i++){
			if(A[i]<A[left]){
				++m;
				swap(A,m,i);
			}
		}
		swap(A,left,m);
		quicksort(A,left,m-1);
		quicksort(A,m+1,right);
	}
	static void insertionSort(int[] A, int n){
		for (int i =1;i<n;i++){
			int key = A[i];
			int j =i-1;
			while(j>=0 && A[j]>key){
				A[j + 1] =A[j];
				j=j-1;
			}
			A[j+1] = key;
		}
	}
	static void hybridQuicksort(int[] A,int left, int right, int cutoff){
		if(right-left<cutoff){
			return;
		}
		Random rand = new Random();
		int swapNum = left+ rand.nextInt(right-left);//range is right to left
		swap(A,left,swapNum);
		int m = left;
		for (int i=left+1;i<=right;i++){
			if(A[i]<A[left]){
				++m;
				swap(A,m,i);
			}
		}
		swap(A,left,m);
		quicksort(A,left,m-1);
		quicksort(A,m+1,right);
	}
	static void printarray(int[] A, int n){
		for (int i=0;i<n;i++){
			System.out.println(A[i]);
		}
	}
	public static void main(String[] args) {
        int num =50000000;
		int[] arr1 = new int[num];
		int[] arr2= new int[num];
		int[] arr3= new int[num];
        System.out.println("Task#6: Performance Measurement");
		arr1 = initialize(arr1,num);
		arr1 =fisherYates(arr1,num);
        arr2 = initialize(arr2,num);
		arr2 =fisherYates(arr2,num);
        arr3 = initialize(arr3,num);
		arr3 =fisherYates(arr3,num);
        long sB1 = System.nanoTime();
        quicksort(arr1,0,num-1);
        long fB1 = System.nanoTime();
        double tB1 = (fB1- sB1)/1000000000.0;// sec 1 = 1_000_000_000 nanosecond
		System.out.println("time Bentley's Quicksort Algorithm: "+tB1+"s");
		long sB2 = System.nanoTime();
        hybridQuicksort(arr2,0,num-1,100);
        long fB2 = System.nanoTime();
        double tB2 = (fB2- sB2)/1000000000.0;// sec 1 = 1_000_000_000 nanosecond
		System.out.println("time Hybrid Quicksort: "+tB2+"s");
        long sB3 = System.nanoTime();
        Arrays.sort(arr3);
        long fB3 = System.nanoTime();
        double tB3 = (fB3- sB3)/1000000000.0;// sec 1 = 1_000_000_000 nanosecond
		System.out.println("time library sort(): "+tB3+"s");
		
	}
}