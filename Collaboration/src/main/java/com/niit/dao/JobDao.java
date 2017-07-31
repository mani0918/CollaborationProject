package com.niit.dao;

import java.util.List;

import com.niit.domain.Job;
import com.niit.domain.JobApplied;

public interface JobDao {
	
	
	// post a new job by admin
	public boolean save(Job job);

	// update job by admin
	public boolean update(Job job);

	// List all jobs for admin
	public List<Job> list();

	//getthejobdetails by jobId
	public Job getJobById(String jobId);
	
	// Admin gets jobs by status
	// Students/Alumini gets jobs that are 'V'
	public List<Job> list(String status);

	
	public List<JobApplied> listAllJobsApplied();
	// List all open jobs
	public List<Job> listAllOpenJobs();

	// Apply for Job
	public boolean save(JobApplied jobApplied);

	// Admin can Accept/Reject/CallForInterview
	public boolean update(JobApplied jobApplied);

	// Get all applied jobs by userId
	public List<JobApplied> listAllJobsAppliedByMe(String userId);

	// Get job application
	public JobApplied getJobApplication(String userId, String jobId);
	
	
	// get job application
	public JobApplied getJobApplication(int jobAppliedId);

	
	// public int getMaxJobId();
	public int getMaxJobApplicationId();

	/*
	 * //Admin also required to get all jobs by status //Student/alumini- should
	 * get vacant jobs //RestController send status="V"
	 * 
	 * public List<Job> list(char status);
	 * 
	 * //Apply for the job-> create a new record in jobapplication table public
	 * boolean save(JobApplication jobApplication); //Admin can
	 * reject/select/call for interview public boolean update(JobApplication
	 * jobApplication);
	 * 
	 * //get all the jobs applied by me public List<JobApplication> list(String
	 * userId);
	 * 
	 */

}
