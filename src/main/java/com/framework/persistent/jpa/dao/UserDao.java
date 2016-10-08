package com.framework.persistent.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.framework.persistent.jpa.domain.User;

public interface UserDao extends CrudRepository<User, Long> {

}
