package com.niit.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class ForumComment extends BaseDomain {
	@Id
	private int forumCommentId;
	private int forumId;
	private String forumComment;
	private Date forumCommentDate;
	private String userId;
	private String username;

	public int getForumCommentId() {
		return forumCommentId;
	}

	public void setForumCommentId(int forumCommentId) {
		this.forumCommentId = forumCommentId;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumComment() {
		return forumComment;
	}

	public void setForumComment(String forumComment) {
		this.forumComment = forumComment;
	}

	public Date getForumCommentDate() {
		return forumCommentDate;
	}

	public void setForumCommentDate(Date forumCommentDate) {
		this.forumCommentDate = forumCommentDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
