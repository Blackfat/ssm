package com.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1.Retention:
 * SOURCE:注解仅保留在源码中
 * CLASS:注解保留在字节码中，但运行期不能获取注解信息
 * RUNTIME:运行期可以获取注解信息
 * 2.注解成员规范：
 * 注解成员以无入参无抛出异常额方式声明
 * 可以声明一个默认值
 * 注解的类型包括原始类型及其封装类
 * 如果注解只有一个成员，成员名称必须为value()
 * @author wangfeiyang
 *
 */
@Retention(RetentionPolicy.RUNTIME)// 注解的保留权限
@Target(ElementType.METHOD)   // 注解的使用范围
public @interface AnnotationTest {
	boolean value() default true;// 定义注解成员

}
