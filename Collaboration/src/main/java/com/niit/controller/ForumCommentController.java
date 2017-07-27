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

import com.niit.dao.ForumCommentDao;
import com.niit.domain.ForumComment;

@RestController
public class ForumCommentController {

	@Autowired
	ForumCommentDao forumCommentDao;

	@Autowired
	ForumComment forumComment;

	@RequestMapping(value ="/getForumComments", method = RequestMethod.GET)
	public ResponseEntity<List<ForumComment>> list() {

		List<ForumComment> listforumComments = forumCommentDao.list();

		return new ResponseEntity<List<ForumComment>>(listforumComments, HttpStatus.OK);

	}

	// insert the forumComment
	@RequestMapping(value = "/insertForumComment", method = RequestMethod.POST)
	public ResponseEntity<String> addForumComment(@RequestBody ForumComment forumComment) {
		forumComment.setForumId(201);
		forumComment.setForumCommentDate(new Date());
		forumComment.setUsername("mani");
		forumComment.setUserId("102");
		forumCommentDao.save(forumComment);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	// delete the forumComment
	@RequestMapping(value = "/deleteForumComment/{forumCommentId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteForumComment(@PathVariable("forumCommentId") int forumCommentId) {

		forumCommentDao.delete(forumCommentId);

		return new ResponseEntity<String>("ForumComment Deleted Successfully", HttpStatus.OK);
	}

	// update the forumComment

	@RequestMapping(value = "/updateForumComment/{forumCommentId}", method = RequestMethod.PUT)
	public ResponseEntity<ForumComment> updateForumComment(@PathVariable("forumCommentId") int forumCommentId,
			@RequestBody ForumComment forumComment) {
		ForumComment curr_forumComment = forumCommentDao.getForumCommentById(forumCommentId);
		curr_forumComment.setForumComment(forumComment.getForumComment());
		forumCommentDao.update(curr_forumComment);
		return new ResponseEntity<ForumComment>(curr_forumComment, HttpStatus.OK);

	}

}
