package com.niit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogCommentDao;
import com.niit.domain.Blog;
import com.niit.domain.BlogComment;

@RestController
public class BlogCommentController {

	@Autowired
	BlogCommentDao blogCommentDao;

	@Autowired
	BlogComment blogComment;

	@RequestMapping(value = "/getBlogComments", method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> list() {

		List<BlogComment> listblogComments = blogCommentDao.list();

		return new ResponseEntity<List<BlogComment>>(listblogComments, HttpStatus.OK);
	}

	// insert the blogComment
	@RequestMapping(value = "/insertBlogComment", method = RequestMethod.POST)
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment) {

		blogComment.setBlogCommentDate(new Date());
		blogComment.setBlogId(102);
		blogComment.setUserId(102);
		blogComment.setUsername("mani");
		blogCommentDao.insertBlogComment(blogComment);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	// delete blogcomment

	@RequestMapping(value = "/deleteBlogComment/{blogCommentId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBlogComment(@PathVariable("blogCommentId") int blogCommentId) {
		blogCommentDao.deleteBlogComment(blogCommentId);
		return new ResponseEntity<String>("deleted Successfully", HttpStatus.OK);

	}

	// update blogComment

	@RequestMapping(value = "/updateBlog/{blogCommentId}", method = RequestMethod.PUT)
	public ResponseEntity<BlogComment> updateBlog(@PathVariable("blogCommentId") int blogCommentId, @RequestBody BlogComment blogComment) {
		BlogComment curr_blogcomment = blogCommentDao.getBlogCommentById(blogCommentId);
		curr_blogcomment.setBlogComment(blogComment.getBlogComment());
		blogCommentDao.insertBlogComment(curr_blogcomment);
		return new ResponseEntity<BlogComment>(curr_blogcomment, HttpStatus.OK);

	}
}
