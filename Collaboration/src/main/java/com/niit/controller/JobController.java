package com.niit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDao;
import com.niit.domain.Job;
import com.niit.domain.JobApplied;

@RestController
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	JobDao jobDao;
	@Autowired
	Job job;
	@Autowired
	JobApplied jobApplied;
	@Autowired
	HttpSession session;

	//  //
	// $http.get(base_url+"/getAllJobs/)
	/*@GetMapping("/getAllJobs")*/
	@RequestMapping(value = "/getAllJobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllOpendJobs() {
		logger.debug("Starting of the method getAllOpendJobs");
		// List<Job> jobs = jobDao.listAllOpenJobs();
		return new ResponseEntity<List<Job>>(jobDao.listAllOpenJobs(), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/getMyAppliedJobs", method = RequestMethod.GET)
	public ResponseEntity<List<JobApplied>> getMyAppliedJobs() {
		logger.debug("Starting of the method getMyAppliedJobs");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		List<JobApplied> jobs = new ArrayList<JobApplied>();

		if (loggedInUserId == null || loggedInUserId.isEmpty()) {
			job.setErrorCode("404");
			job.setErrorMessage("You have to login to see you applied jobs");
			jobs.add(jobApplied);

		} else {
			jobs = jobDao.listAllJobsAppliedByMe(loggedInUserId);
		}

		return new ResponseEntity<List<JobApplied>>(jobs, HttpStatus.OK);
	}

	

	@RequestMapping(value = "/getJobAppliedDetails/{userId}/{jobId}", method = RequestMethod.GET)

	public ResponseEntity<JobApplied> getJobAppliedDetails(@PathVariable("userId") String userId , @PathVariable("jobId") String jobId ) {
		logger.debug("Starting of the method getJobDetails of job id : " +jobId );
		JobApplied jobApplied = jobDao.getJobApplication(userId, jobId);

		if (jobApplied == null) {
			jobApplied = new JobApplied();
			jobApplied.setErrorCode("404");
			jobApplied.setErrorMessage("Job not applied available by this userId : " + userId);
		}

		return new ResponseEntity<JobApplied>(jobApplied, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/postAJob", method = RequestMethod.POST)
	public ResponseEntity<Job> postAJob(@RequestBody Job job) {
		logger.debug("Starting of the method postAJob");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (loggedInUserId != null) {

			if (loggedInUserRole.equals("ROLE_ADMIN")) {

				
				job.setStatus("V"); // 1. V-Vacant 2. F-Filled 3. P-Pending 4.

				if (jobDao.save(job)) {
					job.setErrorCode("200");
					job.setErrorMessage("Successfully poted the job");
					logger.debug("Successfully poted the job");

				} else {
					job.setErrorCode("404");
					job.setErrorMessage("Not able to post a job");
					logger.debug("Not able to post a job");
				}
			} else {
				job.setErrorCode("404");
				job.setErrorMessage("Not authorised to post ajob");
				logger.debug("Not able to post a job");
			}
		} else {
			job.setErrorCode("404");
			job.setErrorMessage("Login to post the job");
			logger.debug("Login as admin to post the job");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	@RequestMapping(value = "/applyForJob/{jobId}", method = RequestMethod.POST)
	public ResponseEntity<JobApplied> applyForJob(@PathVariable("jobId") String jobId) {
		logger.debug("Starting of the method applyForJob");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		if (loggedInUserId != null) {

			if (isUserAppliedForTheJob(loggedInUserId, jobId) == false) {
				jobApplied.setJobId(jobId);
				jobApplied.setUserId(loggedInUserId);
				jobApplied.setStatus("N"); // N-Newly Applied; C->Call For
											// Interview, S->Selected
				jobApplied.setDateApplied(new Date(System.currentTimeMillis()));

				logger.debug("Applied Date : " + jobApplied.getDateApplied());

				if (jobDao.save(jobApplied)) {
					jobApplied.setErrorCode("200");
					jobApplied.setErrorMessage(
							"You have successfully applied for the job :" + jobId + " Hr will getback to you soon.");
					logger.debug("Successfully applied for the job");
				} else {
					jobApplied.setErrorCode("404");
					jobApplied.setErrorMessage("failed to apply for the job");
					logger.debug("Not able to apply for the job");
				}
			} else // If the user already applied for the job
			{
				jobApplied.setErrorCode("404");
				jobApplied.setErrorMessage("You already applied for the job" + jobId);
				logger.debug("Not able to apply for the job");
			}

		} else {
			jobApplied.setErrorCode("404");
			jobApplied.setErrorMessage("You have to loggin to apply for a job");
		}

		// jobApplied = jobDAO.getJobApplied(jobId);

		return new ResponseEntity<JobApplied>(jobApplied, HttpStatus.OK);
	}

	private boolean isUserAppliedForTheJob(String userId, String jobId) {

		if (jobDao.getJobApplication(userId, jobId) == null) {
			return false;
		}

		return true;
	}
	
	@RequestMapping(value = "/updateJobStatus/{jobId}", method = RequestMethod.PUT)
		public ResponseEntity<Job> updateJobStatus(@PathVariable("jobId") String jobId) {
			logger.debug("Starting of the method updateJob");
			String loggedInUserId = (String) session.getAttribute("loggedInUserId");
			String loggedInUserRole=(String) session.getAttribute("loggedInUserRole");
			Job job=jobDao.getJobById(jobId);
		
				job.setStatus("C");
				if (jobDao.update(job)) {
					job.setErrorCode("200");
					job.setErrorMessage("Successfully updated the job");
					logger.debug("Successfully posted the updateJob");
				} else {
					job.setErrorCode("404");
					job.setErrorMessage("Not able to update the job");
					logger.debug("Not able to updatd a job");
					
				}
			
			

			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
	
	@RequestMapping(value = "/callForInterview/{userId}/{jobId}/{remarks}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplied> callForInterview(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @PathVariable("remarks") String remarks) {
		logger.debug("Starting of the method can CallForInterview");

		jobApplied = updateJobAppliedStatus(userId, jobId, "C", remarks);

		return new ResponseEntity<JobApplied>(jobApplied, HttpStatus.OK);
	}

	@RequestMapping(value = "/rejectJobApplied/{userId}/{jobId}/{remarks}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplied> rejectJobApplied(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @PathVariable("remarks") String remarks) {
		logger.debug("Starting of the method rejectJobApplied");
		jobApplied = updateJobAppliedStatus(userId, jobId, "R", remarks);

		return new ResponseEntity<JobApplied>(jobApplied, HttpStatus.OK);
	}
	

	private JobApplied updateJobAppliedStatus(String userId, String jobId, String status, String remarks) {
		logger.debug("Starting of the method updateJobAppliedStatus");

		if (isUserAppliedForTheJob(userId, jobId) == false) {
			jobApplied.setErrorCode("404");
			jobApplied.setErrorMessage(userId + " not applied for the job " + jobId);
			return jobApplied;
		}

		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");
		logger.debug("loggedInUserRole:" + loggedInUserRole);
		if (loggedInUserRole == null || loggedInUserRole.isEmpty()) {
			jobApplied.setErrorCode("404");
			jobApplied.setErrorMessage("You are not logged in");
			return jobApplied;
		}

		if (!loggedInUserRole.equalsIgnoreCase("ROLE_ADMIN")) {
			jobApplied.setErrorCode("404");
			jobApplied.setErrorMessage("You are not admin.  You can not do this Operation");
			return jobApplied;
		}
		jobApplied = jobDao.getJobApplication(userId, jobId);

		jobApplied.setStatus(status);
		jobApplied.setRemarks(remarks);
		if (jobDao.update(jobApplied)) {
			jobApplied.setErrorCode("200");
			jobApplied.setErrorMessage("Successfully updated the status as " + status);
			logger.debug("Successfully updated the status as " + status);
		} else {
			jobApplied.setErrorCode("404");
			jobApplied.setErrorMessage("Not able to update the status " + status);
			logger.debug("Not able to update the status" + status);
		}

		return jobApplied;
	}


	


	

	}

