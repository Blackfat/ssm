package com.spring.aop.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] arg1, Object obj)
			throws Throwable {
		System.out.println(obj.getClass()+"."+method.getName());
		String clientName=(String)arg1[0];
		System.out.println("How are you!"+clientName);
		
	}

}
