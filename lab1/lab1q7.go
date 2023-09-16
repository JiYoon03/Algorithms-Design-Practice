/*
* Write a function isOdd with Go for question 7
@ @author JiYoon Kang
* created Jan 23, 2023
*/
package main

import "fmt"

func isOdd(x int){
	if(x%2==1){
		fmt.Printf("%d true\n",x)
	}else{
		fmt.Println(x,"false")
	}
}
func main(){
	fmt.Println("Go run isOdd fuction")
	for i:=0;i<6;i++{
		isOdd(i)
	}
	
}