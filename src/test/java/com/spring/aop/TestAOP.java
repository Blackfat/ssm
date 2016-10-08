package com.spring.aop;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.advisor.WaiterDelegate;

public class TestAOP {

	@Test
	public void testBeforeAdvice() {
		Waiter target=new NativeWaiter();
		BeforeAdvice advice=new GreetingBeforeAdvice();
		//代理工厂
		ProxyFactory proxy=new ProxyFactory();
		//设置代理对象
		proxy.setTarget(target);
		// 指定接口进行代理
		proxy.setInterfaces(target.getClass().getInterfaces());
		// 启用优化，是用cglib创建代理
		proxy.setOptimize(true);
		//设置增强
		proxy.addAdvice(advice);
		// 产生代理对象
		Waiter waiter=(Waiter)proxy.getProxy();
		waiter.greetTo("james");
		waiter.serveTo("king");
		
	}
    @Test
	public void testAfterAdvice(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		Waiter waiter=(Waiter) ctx.getBean("waiter");
		waiter.greetTo("james");
	}
    
    @Test
    public void testAdvisor(){
    	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-aop.xml");
    	Waiter waiter=(NativeWaiter) ctx.getBean("waiterBean");
    	Seller seller=(Seller) ctx.getBean("sellerBean");
    	waiter.greetTo("james");
    	seller.greetTo("king");
    }
    
    @Test
    public void testDynamicAdvisor(){
    	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-aop.xml");
    	Waiter waiter=(NativeWaiter) ctx.getBean("waiter2");
    	waiter.greetTo("james");
    }
    
    @Test
    public void testFlowAdvisor(){
    	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-aop.xml");
    	Waiter waiter=(NativeWaiter) ctx.getBean("waiter3");
    	WaiterDelegate wd=new WaiterDelegate();
    	wd.setWaiter(waiter);
    	waiter.serveTo("james");
    	wd.service("james");
    }
    @Test
    public void testComposableAdvisor(){
    	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-aop.xml");
    	Waiter waiter=(NativeWaiter) ctx.getBean("waiter4");
    	WaiterDelegate wd=new WaiterDelegate();
    	wd.setWaiter(waiter);
    	waiter.serveTo("james");
    	wd.service("james");
    }
    
    @Test
    public void proxy(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-proxy.xml");
        Seller seller = (Seller)ctx.getBean("sellerTarget1");
        System.out.println(seller.getClass().getName());
        seller.greetTo("james");
        
    }
}
