package com.niit.testcases;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.niit.dao.BlogCommentDao;
import com.niit.domain.BlogComment;

public class BlogCommentDaoTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static BlogCommentDao blogCommentDao;
	@Autowired
	static BlogComment blogComment;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		blogCommentDao = (BlogCommentDao) context.getBean("blogCommentDao");

		// get the user from context
		blogComment = (BlogComment) context.getBean("blogComment");
}

	@Test
	public void CraeteBlogCommentTestCase()
	{
		blogComment.setBlogId(100);
		blogComment.setBlogCommentId(10);
		blogComment.setBlogComment("what is this?");
		blogComment.setBlogCommentDate(new Date());
		blogComment.setUserId(1);
		blogComment.setUsername("mani");
		boolean flag = blogCommentDao.insertBlogComment(blogComment);
		assertEquals("CraeteBlogCommentTestCase", true, flag);
		
	}
}
