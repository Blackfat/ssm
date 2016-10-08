package com.spring.aop.aspectJ;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TestAspect {
	
	@Before("target(com.spring.aop.NativeWaiter) && args(name,num,..)")
	// 绑定连接点参数，首先args(name,num,..)根据增强方法的入参找到name和num对应的类型，以得到真实的切点表达式
	public void bindJointPointParams(int num,String name){
		
	}

}
