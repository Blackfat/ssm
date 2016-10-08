package com.spring.annotation;

public class FormService {
	@AnnotationTest(true)
	public void deleteForm(int formId){
		System.out.println("删除论坛模块："+formId);
	};
	
	@AnnotationTest(false)
	public void deleteTopic(int postId){
		System.out.println("删除论坛主题："+postId);
	};
 
}
