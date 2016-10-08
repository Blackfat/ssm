package com.spring.aop.aspectJ;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//定义了一个切面
@Aspect
public class PreGreetingAspect {
	// 定义增强类型和切点
	@Before(value="execution(* greetTo(..))")
	// 定义了增强
	public void beforeGreeting(){
		System.out.println("How are you");
	}

}
