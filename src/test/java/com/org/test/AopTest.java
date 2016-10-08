package com.org.test;

import org.junit.Test;

import com.org.aop.CGlibProxyFactory;
import com.org.aop.JDKProxyFactory;
import com.org.service.PersonService;
import com.org.service.impl.PersonServiceBean;

public class AopTest {
	@Test
	public void proxyTest() {
		JDKProxyFactory factory = new JDKProxyFactory();
		PersonService service = (PersonService) factory
				.createProxyIntance(new PersonServiceBean("xxx"));
		service.save("888");
	}

	@Test
	public void proxyTest2() {
		CGlibProxyFactory factory = new CGlibProxyFactory();
		PersonServiceBean service = (PersonServiceBean) factory
				.createProxyIntance(new PersonServiceBean("xxx"));
		service.save("999");
	}

}
