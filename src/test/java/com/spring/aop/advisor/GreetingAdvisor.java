package com.spring.aop.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.spring.aop.Waiter;

/**
 * 静态方法匹配切面，默认情况下匹配所有的目标类
 * @author wangfeiyang
 *
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor{

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		// 切点方法匹配规则，方法名为greetTo,仅能通过方法名定义切点
		return "greetTo".equals(method.getName());
	}
	public ClassFilter getClassFilter(){
		return new ClassFilter(){
			@Override
			public boolean matches(Class<?> clazz) {
				// clazz是waiter类或者子类
				return Waiter.class.isAssignableFrom(clazz);
			}};
	}

}
