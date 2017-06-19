package com.niit.dao;

import java.util.List;

import com.niit.domain.ForumComment;


public interface ForumCommentDao {

public boolean insertForumComment(ForumComment forumComment);
	
	public List<ForumComment> list();
	
	public boolean deleteForumComment(int id);
	
	public ForumComment getForumCommentById(int id);
	
}
