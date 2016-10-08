package com.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//获取方法参数
	    System.out.println(invocation.getThis().getClass().getName());
	    Object[] args=invocation.getArguments();
	    String clientName=(String)args[0];
	    System.out.println("How are you "+clientName);
	    // 通过反射机制调用目标方法
	    Object obj=invocation.proceed();
	    System.out.println("enjoy yourself");
		return obj;
	}

}
