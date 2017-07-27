package com.niit.testcases;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ForumCommentDao;
import com.niit.domain.ForumComment;

public class ForumCommentDaoTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static ForumCommentDao forumCommentDao;
	@Autowired
	static ForumComment forumComment;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		forumCommentDao = (ForumCommentDao) context.getBean("forumCommentDao");

		// get the user from context
		forumComment = (ForumComment) context.getBean("forumComment");
}

	@Test
	public void createForumCommentTestCase(){
		
		forumComment.setForumCommentId(300);
		forumComment.setForumComment("nice");
		forumComment.setForumCommentDate(new Date());
		forumComment.setForumId(200);
		forumComment.setUserId("100");
		forumComment.setUsername("mani");
		
		boolean flag =forumCommentDao.save(forumComment);
		assertEquals("createForumCommentTestCase", true, flag);
	}

}
