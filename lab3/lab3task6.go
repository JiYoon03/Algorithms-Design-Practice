/*
* Write a function  Quicksort, Hybrid Quicksort,Library sort() in Go
* For task 6 the Performance Measurement
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

func main(){
	num := 50000000
	var arr1 []int
	var arr2 []int
	var arr3 []int
	
	fmt.Println("Task#6: Performance Measurement")
	arr1 = initialize(arr1,num)
	arr1 =fisherYates(arr1,num)
	arr2 = initialize(arr2,num)
	arr2 =fisherYates(arr2,num)
	arr3 = initialize(arr3,num)
	arr3 =fisherYates(arr3,num)
	startB1 := time.Now()
	quicksort(arr1,0,num-1)
	timeElapsedB1 := time.Since(startB1)
	fmt.Println("time Bentley's Quicksort Algorithm: ",timeElapsedB1)
	startB2 := time.Now()
	hybridQuicksort(arr2,0,num-1,100)
	timeElapsedB2 := time.Since(startB2)
	fmt.Println("time Hybrid Quicksort: ",timeElapsedB2)
	startB3 := time.Now()
	sort.Ints(arr3)
	timeElapsedB3 := time.Since(startB3)
	fmt.Println("time library sort(): ",timeElapsedB3)
}