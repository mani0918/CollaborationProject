package com.niit.dao;

import java.util.List;

import com.niit.domain.Blog;

public interface BlogDao {

	public boolean save(Blog blog);

	public boolean update(Blog blog);

	public boolean delete(int id);

	public Blog getBlogById(int blogId);

	public List<Blog> list();

	public List<Blog> list(String status);

	public int getMaxBlogId();

}
