package com.niit.testcases;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDao;
import com.niit.dao.ForumDao;
import com.niit.domain.Blog;
import com.niit.domain.Forum;

public class ForumDaoTestCase {

	
	@Autowired
	static AnnotationConfigApplicationContext context;
	@Autowired
	static ForumDao forumDao;
	@Autowired
	static Forum forum;
	
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		forumDao = (ForumDao) context.getBean("forumDao");

		// get the user from context
		forum = (Forum) context.getBean("forum");
}
	@Test
	public void createForumTestCase() {
	
		forum.setForumId(200);
		forum.setForumName("Mani");
		forum.setStatus("Approved");
		forum.setUserId("100");
		forum.setCreateDate(new Date());
		
		boolean flag= forumDao.save(forum);
		assertEquals("createForumTestCase", true, flag);
	}

}
