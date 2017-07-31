myApp.service("JobService", function($http,$q){
	console.log("calling JobService");
	var BASE_URL='http://localhost:2020/Collaboration';
	return{
		
		getAllJobs : function(){
			
			console.log("calling getAllJobs method");
			return $http.get(BASE_URL + '/getAllJobs')
					.then(
							function(response){
								console.log("response from getAllJobs");
								return response.data;
							},null
					);
		},
		
		createJob : function(job){
			console.log("calling createJob");
			return $http.post(BASE_URL + '/postAJob' ,job)
						.then(
								function(response){
									console.log("response from createJob");
									return response.data;
								},
								function(errResponse){
									console.error("error while posting the job");
									return $q.reject(errResponse);
								}
						);
						
		},
		

		
		getAllJobApplications : function(){
			console.log("Starting of getAllJobApplications in JobService");
			
			return $http.get(BASE_URL+"/getAllJobApplications")
			.then(
					function(response) {
						return response.data;
					},
					function(errResponse){
						console.log("Error while getting all job applications");
					}
				);
		},
		
		closeJob : function(id){
			console.log("calling closeJob");
			return $http.put(BASE_URL+"/updateJobStatus/"+id)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error("Error while closing the job");
					}
			);
		},
		
		
		applyJob: function(id){
			console.log("calling applyJob");
			return $http.post(BASE_URL+"/applyForJob/"+id)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error("error while applying for the job");
					}
			);
		},
	
		
		jobAppliedByMe: function(){
			console.log("calling getAppliedJobsMyMe");
			return $http.get(BASE_URL+"/getMyAppliedJobs")
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error("error while getting the list of jobs ");
					}
			);
			
		}
	};
	
	
		
});