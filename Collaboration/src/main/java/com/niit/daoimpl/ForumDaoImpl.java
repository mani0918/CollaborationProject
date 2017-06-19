package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.ForumDao;
import com.niit.domain.Forum;

@Repository("forumDao")
@Transactional
public class ForumDaoImpl implements ForumDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		
	}
	
	public boolean insertForum(Forum forum) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public List<Forum> list() {
		
		return sessionFactory.getCurrentSession().createQuery("from Forum").list();
		
	}

	
	public boolean deleteForum(int id) {
		try{
			sessionFactory.getCurrentSession().delete(getForumById(id));
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public Forum getForumById(int id) {
		
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, id);
		
	}

	
	
}
