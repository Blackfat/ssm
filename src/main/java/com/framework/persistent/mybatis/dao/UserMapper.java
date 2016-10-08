package com.framework.persistent.mybatis.dao;

import org.springframework.stereotype.Repository;

import com.framework.persistent.jpa.domain.User;


@Repository
public interface UserMapper {
	
	public User findUserByLoginName(String name);

}
