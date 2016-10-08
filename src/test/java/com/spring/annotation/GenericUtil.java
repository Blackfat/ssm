package com.spring.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GenericUtil{
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		List<String> list=new ArrayList<String>();
		Method method=GenericUtil.class.getMethod("getGeneric",ArrayList.class );
		//按照声明顺序返回 Type 对象的数组，这些对象描述了此 Method 对象所表示的方法的形参类型的。
		Type[] types=method.getGenericParameterTypes();
		ParameterizedType type=(ParameterizedType) types[0];
        System.out.println(type.getRawType());
        System.out.println(type.getActualTypeArguments()[0]);
	}
    public void getGeneric(ArrayList<String> list){
 
    }
   
}
