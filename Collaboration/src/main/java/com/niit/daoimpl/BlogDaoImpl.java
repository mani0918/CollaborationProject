package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.BlogDao;
import com.niit.domain.Blog;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public BlogDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean insertBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
		return true;
	}

	public List<Blog> list() {
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();

	}

	public boolean deleteBlog(int id) {
		try {
			sessionFactory.getCurrentSession().delete(getBlogById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Blog getBlogById(int id) {
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);
	}
}
