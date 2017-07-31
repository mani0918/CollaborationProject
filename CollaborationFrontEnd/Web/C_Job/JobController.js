myApp.controller("JobController", function($scope, JobService, $rootScope, $http,$route,$location){
	
	console.log("------JobController");
	this.job={
			
		jobId : '',
		jobProfile : '',
		jobDescription :'',
		qualification : '',
		status : '',
		postDate : '',
		errorCode : '',
		errorMessage : ''
};
	this.jobApplication = {
			errorMessage: '',
	        errorCode: '',
	        jobAppliedId: '',
	        userId: '',
	        jobId: '',
	        remarks: '',
	        status: '',
	        dateApplied: ''
	}
	
	this.jobApplications = [];
	this.jobs=[];
	this.appliedJobsByMe=[];
	this.getAllJobs=function(){
		console.log("starting of getAllJobs");
		JobService.getAllJobs()
					.then(
							function(jobServiceData)
							{
								$rootScope.jobs=jobServiceData;
							}
					);
	};
	this.getAllJobs();
	
	this.getJobsByStatus=function(){
		console.log("-----------getJobsByStatus");
		if($location.path()=="/getVacantJobs"){
			return "V";
		}
		else if($location.path()=="/getClosedJobs"){
			console.log("return c")
			return "C";
		}
		else{
			return "";
		}
	};
	
	this.createJob=function(job){
		console.log("starting of createJob");
		JobService.createJob(job)
					.then(
							function(jobServiceData)
							{
								this.job=jobServiceData;
								if(this.job.errorCode=="404"){
									alert(this.job.errorMessage)
									this.job="";
								}
								else{
									console.log("successfully posted the job");
								      console.log(this.job.errorMessage);
								      $location.path("/getAllJobs");
								}
							}
					);
	};
	
	this.getAllJobApplications = function(){
		console.log("Starting of getAllJobApplications in JobController");
		
		JobService.getAllJobApplications()
		.then(
				function(jobServiceData) {
					this.jobApplications = jobServiceData;
					$rootScope.jobApplications = jobServiceData;
					localStorage.setItem('jobApplications', JSON.stringify(this.jobApplications));
					console.log(this.jobApplications);
				}
		);
	};
	this.getAllJobApplications();
	
	this.closeJob=function(id){
		console.log("starting of closeJob");
		JobService.closeJob(id)
		.then(
				function(jobServiceData){
					this.job=jobServiceData;
					console.log(this.job);
					$route.reload();
				}
		);
	};
	$scope.reloadRoute = function() {
		$route.reload();
			};
			
	this.applyJob=function(id){
		console.log("starting of applyJob");
		JobService.applyJob(id)
		.then(
				function(jobServiceData){
					this.jobApplication = jobServiceData;
					$rootScope.jobApplications = jobServiceData;
					if(this.jobApplication.errorCode == '404'){
						alert(this.jobApplication.errorMessage);
						console.log(this.jobApplication.errorMessage);
					} else {
						alert(this.jobApplication.errorMessage);
						console.log(this.jobApplication.errorMessage);
						$route.reload();
					}
				}
		);
	};
	
	this.jobAppliedByMe=function(){
		console.log("startin g of getAppliedJobsByMe");
		JobService.jobAppliedByMe()
		.then(
				function(jobServiceData){
					this.appliedJobsByMe=jobServiceData;
					$rootScope.appliedJobsByMe=jobServiceData;
					console.log($rootScope.appliedJobsByMe);
					$route.reload();
					localStorage.setItem('appliedJobsByMe', JSON.stringify(jobServiceData));
				}
		);
	};
	/*this.jobAppliedByMe();*/
});