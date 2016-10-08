package com.spring.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class Invoke {

	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method=InvokeMain.class.getMethod("main", String[].class);
		//jdk 1.5中使用可变类型参数来接受参数的，但为了兼容jdk1.4中使用数组接受可变的参数
		//直接传new String[]{"1","2","3"}，编译器实际上将数组拆分成一个个元素传了进去
		method.invoke(null, (Object)new String[]{"1","2","3"});
	}

}
