package com.niit.dao;

import java.util.List;

import com.niit.domain.BlogComment;

public interface BlogCommentDao {

	public boolean save(BlogComment blogComment);

	public boolean update(BlogComment blogComment);

	public boolean delete(int id);

	public BlogComment getBlogCommentById(int id);

	public List<BlogComment> getAllCommentsByBlogId(int blogId);

	public List<BlogComment> list();

	public int getMaxBlogCommentId();

}
