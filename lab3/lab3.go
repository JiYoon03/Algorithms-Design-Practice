/*
* Write a function  Quicksort, Hybrid Quicksort,Library sort() in Go
@ @author JiYoon Kang
* created Feb 6, 2023
*/
package main

import (
	"fmt"
	"math/rand"
	"sort"
	"time"
)
func initialize(A []int, n int) []int {
	for i:=0;i<n;i++{
		A= append(A,i+1);
	}
	return A
}
func fisherYates(A []int,n int) []int{//task1 fisher yates 
	rand.Seed(time.Now().UnixNano())
	for i:=0;i<n-2;i++{
		j:=i+rand.Intn(n-i)
		x := A[i]
		A[i]=A[j]
		A[j]=x
	}
	return A
}
func swap(A []int,i int,j int) {//task2 inside function swap
    t := A[i]
    A[i] = A[j]
    A[j] = t
}
func quicksort(A []int, left int, right int){//task2 quicksort
	if(left>=right){
		return 
	}
	rand.Seed(time.Now().UnixNano())
	swapNum := left+ rand.Intn(right-left)//range is right to left

	swap(A,left,swapNum)
	var m int =left;
	for i :=left+1;i<=right;i++{
		if(A[i]<A[left]){
			m=m+1;
			swap(A,m,i)
		}
	}
	swap(A,left,m)
	quicksort(A,left,m-1)
	quicksort(A,m+1,right)
}
func insertionSort(A []int, n int){
	for i :=1;i<n;i++{
		key := A[i]
		j :=i-1
		for j>=0 && A[j]>key{
			A[j + 1] =A[j]
			j=j-1
		}
		A[j+1] = key;
	}
}
func hybridQuicksort(A []int, left int, right int, cutoff int){
	if(right-left<cutoff){
		return
	}
	swapNum := left+ rand.Intn(right-left)
	swap(A,left,swapNum)
	m := left
	for i:=left+1;i<=right;i++{
		if(A[i]<A[left]){
			m+=1
			swap(A,m,i)	
		}
	}
	swap(A,left,m)
	quicksort(A,left,m-1)
	quicksort(A,m+1,right)
}
func printarray(A []int, n int){
	for i:=0;i<n;i++{
		fmt.Println(A[i])
	}
}
func main(){
	var arr1 []int
	var arr2 []int
	var arr3 []int
	var arr4 []int
	var arr5 []int
	fmt.Println("task#1:  Fisher-Yates Shuffle")
	arr1 = initialize(arr1,20)
	arr1 =fisherYates(arr1,20)
	printarray(arr1,20)
	fmt.Println("task#2:  Bentley's Quicksort Algorithm")
	arr2 = initialize(arr2,20)
	arr2 =fisherYates(arr2,20)
	quicksort(arr2,0,19)
	printarray(arr2,20)
	fmt.Println("task#3: Insertion Sort")
	arr3 = initialize(arr3,20)
	arr3 =fisherYates(arr3,20)
	insertionSort(arr3,20)
	printarray(arr3,20)
	fmt.Println("task#4: Hybrid Quicksort")
	arr4 = initialize(arr4,100)
	arr4 =fisherYates(arr4,20)
	hybridQuicksort(arr4,0,99,25)
	printarray(arr4,100)
	fmt.Println("task#5: library sort()")
	arr5 = initialize(arr5,100)
	arr5 =fisherYates(arr5,20)
	sort.Ints(arr5)
	printarray(arr5,100)
}