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

import com.niit.dao.ForumCommentDao;

import com.niit.domain.ForumComment;

@Repository("forumCommentDao")
@Transactional
public class ForumCommentDaoImpl implements ForumCommentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ForumCommentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static Logger log = LoggerFactory.getLogger(ForumCommentDaoImpl.class);

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(ForumComment forumComment) {
		log.debug("Starting of method insertForumComment");
		try {
			getCurrentSession().save(forumComment);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		log.debug("Ending of method insertForumComment");
		return true;
	}

	public boolean update(ForumComment forumComment) {
		log.debug("Starting of method insertForumComment");
		try {
			getCurrentSession().update(forumComment);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		log.debug("Ending of method insertForumComment");
		return true;
	}

	public boolean delete(int id) {
		log.debug("Starting of method deleteForumComment");
		try {
			getCurrentSession().delete(getForumCommentById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		log.debug("Ending of method deleteForumComment");
		return true;
	}

	public ForumComment getForumCommentById(int id) {
		log.debug("Starting of method getForumCommentById");
		log.debug("Ending of method getForumCommentById");
		return (ForumComment) getCurrentSession().get(ForumComment.class, id);
	}

	public List<ForumComment> getAllCommentsByForumId(int forumid) {
		log.debug("Starting of method getAllComments in Forum Comment");
		log.debug("Ending of method getAllComments in Forum Comment");
		return getCurrentSession().createCriteria(ForumComment.class).add(Restrictions.eq("forumId", forumid)).list();
	}

	public List<ForumComment> list() {
		log.debug("Starting of method list in Forum Comment");
		log.debug("Ending of method list in Forum Comment");
		return getCurrentSession().createCriteria(ForumComment.class).list();
	}

	public int getMaxForumCommentId() {
		return (Integer) getCurrentSession().createQuery("select max(forumCommentId) from ForumComment").uniqueResult();
	}

}
