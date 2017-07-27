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
	this.jobs=[];
	
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
});