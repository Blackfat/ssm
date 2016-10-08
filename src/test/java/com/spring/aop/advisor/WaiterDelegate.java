package com.spring.aop.advisor;

import com.spring.aop.Waiter;
/**
 * 流程切点表示某个方法直接我间接调用其他方法
 * @author wangfeiyang
 *
 */
public class WaiterDelegate {
	private Waiter waiter;

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	
	public void service(String name){
		waiter.greetTo(name);
		waiter.serveTo(name);
	}
	

}
