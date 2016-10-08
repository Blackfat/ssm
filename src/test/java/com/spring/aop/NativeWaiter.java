package com.spring.aop;

public class NativeWaiter implements Waiter{

	@Override
	public void greetTo(String name) {
		System.out.println("I'm greet to:"+name);
		
	}

	@Override
	public void serveTo(String name) {
		System.out.println("I'm serve to:"+name);
		
	}

}
