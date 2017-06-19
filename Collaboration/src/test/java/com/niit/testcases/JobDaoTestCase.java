package com.niit.testcases;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.FriendDao;
import com.niit.dao.JobDao;
import com.niit.domain.Friend;
import com.niit.domain.Job;

public class JobDaoTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	@Autowired
	static JobDao jobDao;
	@Autowired
	static Job job;
	
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		// get the userDAO from context
		jobDao = (JobDao) context.getBean("jobDao");

		// get the user from context
		job = (Job) context.getBean("job");
}
	
	
	
	@Test
	public void CreateJobTestCase() {
		job.setJobId(600);
		job.setJobProfile("Software Engineer");
		job.setJobDescription("As a Java Developer");
		job.setPostDate(new Date());
		job.setQualification("BTech with 65% Aggregate");
		job.setStatus("Apply");
	}

}
