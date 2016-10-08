package com.framework.util;

import java.util.HashMap;
import java.util.Map;

public class SimpleCache
{
	private static final Map<String, Object> container = new HashMap<String, Object>();
	
	public synchronized static void put(String key, Object value)
	{
		if(key == null || value == null)
		{
			throw new NullPointerException("name == null || value == null");
		}
		container.put(key, value);
	}
	
	public synchronized static Object get(String key)
	{
		return container.get(key);
	}
	
	public synchronized static void remove(String key)
	{
		container.remove(key);
	}
	
	public synchronized static void clear()
	{
		container.clear();
	}
}
