package com.framework.test.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.framework.persistent.jpa.dao.UserDao;
import com.framework.persistent.jpa.domain.User;
import com.framework.persistent.mybatis.dao.UserMapper;


/**
 * 
 * @RunWith 注释标签是 Junit 提供的，用来说明此测试类的运行者，这里用了 SpringJUnit4ClassRunner，这个类是一个针对
 *          Junit 运行环境的自定义扩展，用来标准化在 Spring 环境中 Junit4.5 的测试用例，例如支持的注释标签的标准化
 * @ContextConfiguration 注释标签是 Spring test context 提供的，用来指定 Spring 配置信息的来源，支持指定
 *                       XML 文件位置或者 Spring 配置类名
 * @Transactional 注释标签是表明此测试类的事务启用，这样所有的测试方案都会自动的
 *                rollback，即您不用自己清除自己所做的任何对数据库的变更了
 * @Autowired 体现了我们的测试类也是在 Spring 的容器中管理的，他可以获取容器的 bean 的注入，您不用自己手工获取要测试的 bean
 *            实例了
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback=false)
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insertUser(){
		User user=new User();
		user.setLoginName("admin");
		user.setPassword("admin");
		user.setRegistDate(new Date());
		userDao.save(user);
	}
	
	
	@Test
	public void selectUser(){
		User user=userMapper.findUserByLoginName("admin");
		System.out.println(user.getRegistDate());
	}
	
}
