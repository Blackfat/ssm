package com.spring.aop.aspectJ;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.NativeWaiter;
import com.spring.aop.Seller;
import com.spring.aop.SmartSeller;
import com.spring.aop.Waiter;

public class TestAspectJ {

	@Test
	public void test() {
		Waiter waiter=new NativeWaiter();
		AspectJProxyFactory pf=new AspectJProxyFactory();
		// 设置目标对象
		pf.setTarget(waiter);
		// 设置切面类
		pf.addAspect(PreGreetingAspect.class);
		Waiter proxy=pf.getProxy();
		proxy.greetTo("james");
		proxy.serveTo("james");
	}
	
   @Test
	public void testAspect(){
	   ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-aspectJ.xml");
	   Waiter waiter=(Waiter)ctx.getBean("waiter");
	   waiter.greetTo("james");
	   waiter.serveTo("james");
		
	}
   
   @Test
   public void testSchema(){
	   ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-schema.xml");
	   Waiter waiter=(Waiter)ctx.getBean("waiter");
	   waiter.greetTo("james");
	   waiter.serveTo("james");
	   SmartSeller seller=(SmartSeller) ctx.getBean("seller");
	   seller.count();
	   Seller  sellerproxy=(Seller) ctx.getBean("target");
	   sellerproxy.greetTo("james");
	   
   }
}
