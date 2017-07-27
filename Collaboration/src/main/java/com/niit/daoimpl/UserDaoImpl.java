package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.UserDao;
import com.niit.domain.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);


	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(User user) {
		log.debug("---> Starting of User Save method");

		try {
			getCurrentSession().save(user);
		} catch (Exception e) {
			log.debug("---> Exception arised in User Save method");
			e.printStackTrace();
			return false;
		}

		log.debug("---> Ending of User Save method");
		return true;
	}

	public boolean update(User user) {
		log.debug("---> Starting of User update method");

		try {
			getCurrentSession().update(user);
		} catch (Exception e) {
			log.debug("---> Exception arised in User update method");
			e.printStackTrace();
			return false;
		}

		log.debug("---> Ending of User update method");
		return true;
	}

	public boolean delete(String userId) {
		log.debug("---> Starting of User delete method");

		try {
			getCurrentSession().delete(getUserById(userId));
		} catch (Exception e) {
			log.debug("---> Exception arised in User delete method");
			e.printStackTrace();
			return false;
		}

		log.debug("---> Ending of User delete method");
		return true;
	}

	public User getUserById(String userId) {
		log.debug("---> Starting of getUserById method");
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userId", userId)).uniqueResult();
	}

	public List<User> list() {
		log.debug("---> Starting of list method in User");
		return getCurrentSession().createCriteria(User.class).list();
	}

	public User validate(String userId, String password) {
		log.debug("---> Starting of method User Validate");
		return (User) getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userId", userId))
				.add(Restrictions.eq("password", password))
				.uniqueResult();
		

	}

	
	public void setOnline(String userId) {
		log.debug("Starting of the metnod setOnline");
		String hql =" UPDATE User	SET isOnline = 'Y' where userId='" +  userId + "'" ;
		  log.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the metnod setOnline");
		
	}
	

	public void setOffLine(String userId) {
		log.debug("Starting of the metnod setOffLine");
		String hql =" UPDATE User	SET isOnline = 'N' where userId='" +  userId + "'" ;
		  log.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the metnod setOffLine");
		
	}


}
