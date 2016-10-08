package com.spring.aop.advisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import com.spring.aop.Waiter;
/**
 * DefaultPointcutAdvisor是最常用的切面类型，可以通过任意的Pointcut和Advice组成。
 * DynamicMethodMatcherPointcut可以创建动态的切点
 * Spring的动态切点检查机制：在创建代理是对目标类的每个连接点使用静态切点检查，如果仅通过静态切点检查就可以知道连接点是
 * 不匹配的，则在运行时就不再动态检查了，如果静态切点检查匹配，在运行时才进行动态 切点检查。
 * @author wangfeiyang
 *
 */
public class GreetDynamicPointCut extends DynamicMethodMatcherPointcut {
    private static List<String> list=new ArrayList<String>();
    static{
    	list.add("james");
    }
	@Override
	public boolean matches(Method method, Class<?> targetClass, Object[] args)
	{   
		System.out.println("动态检查");
		return list.contains((String)args[0]);
	}
	
	public ClassFilter getClassFilter(){
		return new ClassFilter(){
			@Override
			public boolean matches(Class<?> clazz) {
				System.out.println(clazz.getName()+"静态检查");
				return Waiter.class.isAssignableFrom(clazz);
			}};
	}
	public boolean matches(Method method, Class<?> targetClass) {
		System.out.println(method.getName()+"静态检查");
		// 切点方法匹配规则，方法名为greetTo,仅能通过方法名定义切点
		return "greetTo".equals(method.getName());
	}
	

}
