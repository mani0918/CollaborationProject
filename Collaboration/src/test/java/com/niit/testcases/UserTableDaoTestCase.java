package com.niit.testcases;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserTableDao;

import com.niit.domain.UserTable;

public class UserTableDaoTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	@Autowired
	static UserTableDao userTableDao;
	@Autowired
	static UserTable userTable;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		userTableDao = (UserTableDao) context.getBean("userTableDao");

		// get the user from context
		userTable = (UserTable) context.getBean("userTable");
	}

@Test
	public void createUserTableTestCase() {

		userTable.setUserId(104);
		userTable.setFirstname("Rakesh");
		userTable.setLastname("Goud");
		userTable.setEmailId("rakesh@gmail.com");
		userTable.setPassword("rakesh");
		userTable.setRole("ROLE_USER");
		userTable.setStatus("offline");
		userTable.setIsOnline("False");
		boolean flag = userTableDao.insertUserTable(userTable);
		assertEquals("createUserTableTestCase", true, flag);

	}

	@Test
	public void deleteUserTableTestcase() {

		boolean flag = userTableDao.deleteUserTable(104);
		assertEquals("deleteUserTableTestcase", true, flag);
	}

}
