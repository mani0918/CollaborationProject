package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.JobDao;
import com.niit.domain.Job;


@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public boolean insertJob(Job job) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(job);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public List<Job> list() {
		return sessionFactory.getCurrentSession().createQuery("from Job").list();
		
	}

	
	public boolean deleteJob(int id) {
		try{
			sessionFactory.getCurrentSession().delete(getJobById(id));
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;	
	}

	
	public Job getJobById(int id) {
		return (Job) sessionFactory.getCurrentSession().get(Job.class, id);
	}

}
