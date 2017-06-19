package com.niit.testcases;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.FriendDao;
import com.niit.domain.Friend;



public class FriendDaoTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	@Autowired
	static FriendDao friendDao;
	@Autowired
	static Friend friend;
	
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		friendDao = (FriendDao) context.getBean("friendDao");

		// get the user from context
		friend = (Friend) context.getBean("friend");
}
	@Test
	public void createFriendTestCase() {
	
		friend.setFriendId(500);
		friend.setUserId(100);
		friend.setStatus("N");
		
		boolean flag= friendDao.insertFriend(friend);
		assertEquals("createFriendTestCase", true, flag);
	}

}
