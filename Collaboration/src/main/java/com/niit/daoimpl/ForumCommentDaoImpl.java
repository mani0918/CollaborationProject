package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.ForumCommentDao;

import com.niit.domain.ForumComment;

@Repository("forumCommentDao")
@Transactional
public class ForumCommentDaoImpl implements ForumCommentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumCommentDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	public boolean insertForumComment(ForumComment forumComment) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public List<ForumComment> list() {
		return sessionFactory.getCurrentSession().createQuery("from ForumComment").list();
	}


	public boolean deleteForumComment(int id) {
		try{
			sessionFactory.getCurrentSession().delete(getForumCommentById(id));
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public ForumComment getForumCommentById(int id) {
		return (ForumComment) sessionFactory.getCurrentSession().get(ForumComment.class, id);
		
	}

	
}
