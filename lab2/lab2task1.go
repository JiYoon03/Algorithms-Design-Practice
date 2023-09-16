/*
* Write a function  Linear Search: Performance Comparison task1
@ @author JiYoon Kang
* created Jan 28, 2023
*/
package main

import (
	"fmt"
	)

func linearSearch(A [10]int, n int, x int) int {
	var result int 
	result= -1
	for i:=0;i<n;i++{
		if(A[i]==x){
			result = i
		}
	}
	return result
}

func betterLinearSearch(A [10]int, n int, x int) int {
	for i:=0;i<n;i++ {
		if(A[i]==x){
			return i
		}
	}
	return -1
}

func SentinelLinearSearch(A [10]int, n int, x int) int {
	var last = A[n-1]
	A[n-1]=x
	i := 0
	for A[i]!=x {
		i++
	}
	A[n-1] = last
	if i < n-1 || A[n-1] == x {
		return i
	}else{
		return -1
	}
}

func RecursiveLinearSearch(A [10]int, n int,i int, x int)int{
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
    var array [10]int
    for b :=0;b<10;b++ {
        array[b]=b;
    }
	fmt.Println("TASK#1")
    elementx := 7
    result1 := linearSearch(array,10,elementx)
    fmt.Println("Searching array of size: N (10)\n Algorithm: linearSearch\nLooking for element: x (",elementx,") \nResult: ",result1)
    result2 := betterLinearSearch(array,10,elementx)
    fmt.Println("Algorithm: betterLinearSearch\nLooking for element: x (",elementx,")\nResult: ",result2)
    result3 := SentinelLinearSearch(array,10,elementx)
    fmt.Println("Algorithm: SentinelLinearSearch\nLooking for element: x (",elementx,")\nResult: ",result3)
    result4 := RecursiveLinearSearch(array,10,0,elementx)
    fmt.Println("Algorithm: RecursiveLinearSearch\nLooking for element: x (",elementx,")\nResult: ",result4)
    fmt.Println("===============================================")

}