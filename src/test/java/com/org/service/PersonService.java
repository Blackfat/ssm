package com.org.service;

public interface PersonService {
	public abstract void getPersonName(Integer personid);

	public abstract void save(String name);

	public abstract void update(String name, Integer personid);

}