package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.BlogDao;
import com.niit.domain.Blog;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {

	private static Logger log = LoggerFactory.getLogger(BlogDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public BlogDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(Blog blog) {
		log.debug("Start of method save Blog");
		try {
			
			getCurrentSession().save(blog);
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		log.debug("End of the  method save Blog");
		return true;

	}

	public boolean update(Blog blog) {
		log.debug("Start of the method update Blog");
		try {
			getCurrentSession().update(blog);
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		log.debug("End of the method update Blog");
		return true;

	}

	public boolean delete(int id) {
		log.debug("start of the  method delete Blog");
		try {
			getCurrentSession().delete(getBlogById(id));
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		log.debug("End  of the method delete Blog");
		return true;

	}

	public Blog getBlogById(int blogId) {
		log.debug("Start of the method getBlogById");
		return (Blog) sessionFactory.getCurrentSession().createCriteria(Blog.class)
				.add(Restrictions.eq("blogId", blogId)).uniqueResult();
	}

	public List<Blog> list() {
		log.debug("---> Starting of method list in Blog");
		return getCurrentSession().createCriteria(Blog.class).list();

	}

	public int getMaxBlogId() {
		return (Integer) getCurrentSession().createQuery("select max(blogId) from Blog").uniqueResult();
	}

	public List<Blog> list(String status) {
		log.debug("---> Starting of method list in Blog");
		return getCurrentSession().createCriteria(Blog.class).add(Restrictions.eq("status", status)).list();

	}

}
