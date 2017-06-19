package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDao;
import com.niit.domain.Blog;

@RestController
public class BlogController {

	@Autowired
	BlogDao blogDao;

	@Autowired
	Blog blog;

	@RequestMapping(value = "/getBlogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> list() {

		List<Blog> listblogs = blogDao.list();

		return new ResponseEntity<List<Blog>>(listblogs, HttpStatus.OK);

	}

	// insert the blog
	@RequestMapping(value = "/insertBlog", method = RequestMethod.POST)
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
		blog.setCreateDate(new Date());
		blog.setLikes(0);
		blog.setStatus("NA");
		blog.setUserId(102);
		blogDao.insertBlog(blog);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	// delete the blog
	@RequestMapping(value = "/deleteBlog/{blogId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {

		blogDao.deleteBlog(blogId);

		return new ResponseEntity<String>("Blog Deleted Successfully", HttpStatus.OK);
	}

	// update the blog
	
	@RequestMapping(value = "/updateBlog/{blogId}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("blogId") int blogId, @RequestBody Blog blog) {
		Blog curr_blog = blogDao.getBlogById(blogId);
		curr_blog.setBlogContent(blog.getBlogContent());
		blogDao.insertBlog(curr_blog);
		return new ResponseEntity<Blog>(curr_blog, HttpStatus.OK);

	}

}
