package com.spring.annotation;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestAnnotation {

	@Test
	public void test() {
		Class clazz=FormService.class;
		Method[] methods=clazz.getDeclaredMethods();
		for(Method method:methods){
			AnnotationTest annotation= method.getAnnotation(AnnotationTest.class);
			if(annotation.value()){
				System.out.println(method.getName()+"()需要测试");
			}else{
				System.out.println(method.getName()+"()不需要测试");
			}
		}
	}

}
