package com.niit.testcases;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDao;
import com.niit.domain.Blog;

public class BlogDaoTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static BlogDao blogDao;
	@Autowired
	static Blog blog;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		blogDao = (BlogDao) context.getBean("blogDao");

		// get the user from context
		blog = (Blog) context.getBean("blog");
}
	@Test
	public void createBlogTestCase() {
		blog.setBlogId(102);
		blog.setBlogName("Notebook");
		blog.setCreateDate(new Date());
		boolean flag = blogDao.save(blog);

		assertEquals("createBlogTestCase", true, flag);
	}

	

}
