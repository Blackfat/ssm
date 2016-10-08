package com.spring.aop.advisor;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class GreetingComposablePointcut {
	public Pointcut getIntersectionPointcut(){
		ComposablePointcut cp=new ComposablePointcut();
		Pointcut pt1=new ControlFlowPointcut(WaiterDelegate.class,"service"
				);
		MethodMatcher mm=new NameMatchMethodPointcut();
		((NameMatchMethodPointcut) mm).addMethodName("greetTo");
		return cp.intersection(pt1).intersection(mm);
	}

}
