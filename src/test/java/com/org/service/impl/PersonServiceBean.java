package com.org.service.impl;

import com.org.service.PersonService;

public class PersonServiceBean implements PersonService {
	private String user = null;
	public String getUser() {
		return user;
	}

	public PersonServiceBean() {
	}

	public PersonServiceBean(String user) {
		this.user = user;
	}
	@Override
	public void getPersonName(Integer personid) {
		System.out.println("getPersonName()");
	}


	@Override
	public void save(String name) {
		System.out.println("save()");
	}


	@Override
	public void update(String name, Integer personid) {
		System.out.println("update()");
	}

}
