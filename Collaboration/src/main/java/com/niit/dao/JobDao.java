package com.niit.dao;

import java.util.List;

import com.niit.domain.Job;

public interface JobDao {

	public boolean insertJob(Job job);

	public List<Job> list();

	public boolean deleteJob(int id);

	public Job getJobById(int id);

}
