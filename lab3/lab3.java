/*
* Write a function  Quicksort, Hybrid Quicksort,Library sort() in Java
@ @author JiYoon Kang
* created Feb 6, 2023
*/
import java.util.Random;
import java.util.Arrays;
import java.lang.*;


public class lab3 {
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
		int[] arr1 = new int[20];
		int[] arr2= new int[20];
		int[] arr3= new int[20];
		int[] arr4= new int[100];
		int[] arr5= new int[100];
		System.out.println("task#1:  Fisher-Yates Shuffle");
		System.out.println("Initialize an integer array of N elements so that A[i] = i.");
		arr1 = initialize(arr1,20);
		arr1 =fisherYates(arr1,20);
		printarray(arr1,20);

		System.out.println("task#2:  Bentley's Quicksort Algorithm");
		System.out.println("Initialize an integer array of N elements so that A[i] = i.");
		arr2 = initialize(arr2,20);
		arr2 =fisherYates(arr2,20);
		quicksort(arr2,0,19);
		printarray(arr2,20);

		System.out.println("task#3: Insertion Sort");
		System.out.println("Initialize an integer array of N elements so that A[i] = i.");
		arr3 = initialize(arr3,20);
		arr3 =fisherYates(arr3,20);
		insertionSort(arr3,20);
		printarray(arr3,20);

		System.out.println("task#4: Hybrid Quicksort");
		System.out.println("Initialize an integer array of N elements so that A[i] = i.");
		arr4 = initialize(arr4,100);
		arr4 =fisherYates(arr4,20);
		hybridQuicksort(arr4,0,99,25);
		printarray(arr4,100);
		System.out.println("task#5: library sort()");
		System.out.println("Initialize an integer array of N elements so that A[i] = i.");
		arr5 = initialize(arr5,100);
		arr5 =fisherYates(arr5,20);
		Arrays.sort(arr5);
		printarray(arr5,100);
		
	}
}