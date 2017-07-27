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

import com.niit.dao.FriendDao;
import com.niit.domain.Friend;

@Repository("friendDao")
@Transactional
public class FriendDaoImpl implements FriendDao {

	private static Logger log = LoggerFactory.getLogger(FriendDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public FriendDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Friend> getMyFriends(String userId) {
		List<Friend> list1 = getCurrentSession()
				.createQuery("select friendId from Friend where userId = '" + userId + "' and status = 'A'").list();
		log.debug("Me as userId " + list1);
		List<Friend> list2 = getCurrentSession()
				.createQuery(" select userId from Friend where friendId = '" + userId + "' and status = 'A'").list();
		log.debug("Me as friendId " + list2);
		list1.addAll(list2);
		return list1;
	}

	public Friend get(String userId, String friendId) 
	{
		return (Friend) getCurrentSession().createCriteria(Friend.class).add(Restrictions.eq("userId", userId))
				.add(Restrictions.eq("friendId", friendId)).uniqueResult();
	}

	public boolean save(Friend friend) {
		try {
			log.debug("Previous max Id = " + getMaxId());
			friend.setTableId(getMaxId() + 1);
			log.debug("Generated Id = " + getMaxId() + 1);
			getCurrentSession().save(friend);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception arised while saving");
			return false;
		}
		return true;
	}

	public boolean update(Friend friend) {
		try {
			log.debug("Starting of Update method");
			getCurrentSession().update(friend);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception arised while updating");
			return false;
		}
		log.debug("Ending of Update method");
		return true;
	}

	public boolean delete(String userId, String friendId) {
		log.debug("Starting of delete friend  method");
		Friend friend = get(userId, friendId);
		if (friend == null) {
			log.debug("No such friend exist");
			return false;
		}
		try {
			getCurrentSession().delete(friend);
		} catch (Exception e) {
			log.debug("Exception arised");
			e.printStackTrace();
			return false;
		}
		log.debug("Starting of delete friend method");
		return true;

	}

	public List<Friend> getNewFriendRequests(String friendId) {
		// here friendId is me since I was not the one who sent friend request
		// and the person who sends friend request will be saved as userId
		return getCurrentSession().createQuery("select userId from Friend where friendId = ? and status = 'N'")
				.setString(0, friendId).list();
	}

	public void setOnline(String friendId) {
		getCurrentSession().createQuery("UPDATE Friend SET isOnline = 'Y' WHERE friendId = ?").setString(0, friendId)
				.executeUpdate();
	}

	public void setOffline(String friendId) {
		getCurrentSession().createQuery("UPDATE Friend SET isOnline = 'N' WHERE friendId = ?").setString(0, friendId)
				.executeUpdate();
	}

	public List<Friend> getFriendRequestsSentByMe(String userId) {
		return getCurrentSession().createQuery("select friendId from Friend where userId = ?")
				.setString(0, userId).list();
	}

	private int getMaxId() {
		log.debug("Starting of method getMaxId");
		int maxId;
		try {
			maxId = (Integer) getCurrentSession().createQuery("select max(id) from Friend").uniqueResult();
			log.debug("Got max Id : " + maxId);
		} catch (Exception e) {
			log.debug("Exception arised! There may not be any rows in table. Returning 0 as starting value");
			e.printStackTrace();
			return 0;
		}
		log.debug("Returning max Id");
		return maxId;

	}

}
