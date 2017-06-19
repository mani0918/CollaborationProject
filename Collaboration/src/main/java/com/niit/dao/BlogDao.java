package com.niit.dao;

import java.util.List;

import com.niit.domain.Blog;

public interface BlogDao {

	public boolean insertBlog(Blog blog);
	
	public List<Blog> list();
	
	public boolean deleteBlog(int id);
	
	public Blog getBlogById(int id);
	
	
}
