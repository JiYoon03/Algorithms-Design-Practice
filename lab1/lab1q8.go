/*
* Write a function isOdd with Go for question 8
@ @author JiYoon Kang
* created Jan 23, 2023
*/
package main

import "fmt"

func isOdd(x int){
	if(x%2==-1){
		fmt.Printf("%d true\n",x)
	}
	if(x%2==1){
		fmt.Printf("%d true\n",x)
	}
	if(x%2==0){
		fmt.Println(x,"false")
	}
}
func main(){
	fmt.Println("Go run isOdd fuction")
	for i:=-5;i<6;i++{
		isOdd(i)
	}
	
}