package com.spring.aop.aspectJ;

public class AdviceMethods {
	public void preGreeting(){
		System.out.println("how are you!");
	}
    
	public void afterSeller(int count){
		System.out.println("总计："+count);
	}
}
