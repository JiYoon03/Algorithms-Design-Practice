/*
* Write a function  Linear Search: Performance Comparison task2
@ @author JiYoon Kang
* created Jan 28, 2023
*/
package main

import (
	"fmt"
	"math/rand"
	"time"
)

func linearSearch(A []int, n int, x int) int {
	var result int 
	result= -1
	for i:=0;i<n;i++{
		if(A[i]==x){
			result = i
		}
	}
	return result
}

func betterLinearSearch(A []int, n int, x int) int {
	for i:=0;i<n;i++ {
		if(A[i]==x){
			return i
		}
	}
	return -1
}

func SentinelLinearSearch(A []int, n int, x int) int {
	var last = A[n-1]
	A[n-1]=x
	i := 0
	for A[i]!=x {
		i++
	}
	A[n-1] = last
	if i < n-1 || A[n-1] == x {
		return i
	}
	return -1
}

func RecursiveLinearSearch(A []int, n int,i int, x int)int{
	if i>=n {
		return -1
	}else{
		if(A[i]==x){
			return i
		}else{
			return RecursiveLinearSearch(A, n,i+1, x)
		}
	}
}


func main(){
    var array []int
    for b :=0;b<50000;b++ {//set A of integers of size 50,000,000
		x := rand.Intn(50000000)//A[i] = a random number between 0 and 50million
        array= append(array,x);
    }
	fmt.Println("TASK#2_Best Case: search for element======")
	 element2 := 7
	startB1 := time.Now()
	for a:=0;a<50000;a++{
		linearSearch(array,50000,element2)
	}
	timeElapsedB1 := time.Since(startB1)/50000.00
    fmt.Println("BEST linearSearch Runtime average:",timeElapsedB1)

	startB2 := time.Now()
	for a:=0;a<50000;a++{
		betterLinearSearch(array,50000,element2)
	}
	timeElapsedB2 := time.Since(startB2)/50000.00
    fmt.Println("BEST betterLinearSearch Runtime average:",timeElapsedB2)

	startB3 := time.Now()
	for a:=0;a<50000;a++{
		SentinelLinearSearch(array,50000,element2)
	}
	timeElapsedB3 := time.Since(startB3)/50000.00
    fmt.Println("BEST SentinelLinearSearch Runtime average:",timeElapsedB3)

	startB4 := time.Now()
	for a:=0;a<50000;a++{
		RecursiveLinearSearch(array,50000,0,element2)
	}
	timeElapsedB4 := time.Since(startB4)/50000.00
    fmt.Println("BEST RecursiveLinearSearch Runtime average:",timeElapsedB4)
    fmt.Println("===============================================")

	element3 := rand.Intn(80000000)

	fmt.Println("TASK#2_Average Case: search for element======")
	startA1 := time.Now()
	for a:=0;a<50000;a++{
		linearSearch(array,50000,element3)
	}
	timeElapsedA1 := time.Since(startA1)/50000.00
    fmt.Println("Average  linearSearch Runtime average:",timeElapsedA1)

	startA2 := time.Now()
	for a:=0;a<50000;a++{
		betterLinearSearch(array,50000,element3)
	}
	timeElapsedA2 := time.Since(startA2)/50000.00
    fmt.Println("Average  betterLinearSearch Runtime average:",timeElapsedA2)

	startA3 := time.Now()
	for a:=0;a<50000;a++{
		SentinelLinearSearch(array,50000,element3)
	}
	timeElapsedA3 := time.Since(startA3)/50000.00
    fmt.Println("Average  SentinelLinearSearch Runtime average:",timeElapsedA3)

	startA4 := time.Now()
	for a:=0;a<50000;a++{
		RecursiveLinearSearch(array,50000,0,element3)
	}
	timeElapsedA4 := time.Since(startA4)/50000.00
    fmt.Println("Average  RecursiveLinearSearch Runtime average:",timeElapsedA4)
    fmt.Println("===============================================")

	element4:= -10;

	fmt.Println("TASK#2_Worst Case: search for element======")
	startW1 := time.Now()
	for a:=0;a<50000;a++{
		linearSearch(array,50000,element4)
	}
	timeElapsedW1 := time.Since(startW1)/50000.00
    fmt.Println("Worst  linearSearch Runtime average:",timeElapsedW1)

	startW2 := time.Now()
	for a:=0;a<50000;a++{
		betterLinearSearch(array,50000,element4)
	}
	timeElapsedW2 := time.Since(startW2)/50000.00
    fmt.Println("Worst  betterLinearSearch Runtime average:",timeElapsedW2)

	startW3 := time.Now()
	for a:=0;a<50000;a++{
		SentinelLinearSearch(array,50000,element4)
	}
	timeElapsedW3 := time.Since(startW3)/50000.00
    fmt.Println("Worst  SentinelLinearSearch Runtime average:",timeElapsedW3)

	startW4 := time.Now()
	for a:=0;a<50000;a++{
		RecursiveLinearSearch(array,50000,0,element4)
	}
	timeElapsedW4 := time.Since(startW4)/50000.00
    fmt.Println("Worst  RecursiveLinearSearch Runtime average:",timeElapsedW4)
    fmt.Println("===============================================")
}
