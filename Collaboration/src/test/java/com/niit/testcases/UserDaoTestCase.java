package com.niit.testcases;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDao;

import com.niit.domain.User;

public class UserDaoTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	@Autowired
	static UserDao userDao;
	@Autowired
	static User user;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		userDao = (UserDao) context.getBean("userDao");

		// get the user from context
		user = (User) context.getBean("user");
	}

@Test
	public void createUserTestCase() {

		user.setUserId("104");
		user.setFirstname("Rakesh");
		user.setLastname("Goud");
		user.setEmailId("rakesh@gmail.com");
		user.setPassword("rakesh");
		user.setRole("ROLE_USER");
		user.setStatus("offline");
		user.setIsOnline("False");
		boolean flag = userDao.save(user);
		assertEquals("createUserTestCase", true, flag);

	}

	
}
