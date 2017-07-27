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
		
		/*getAllAppliedJobs: function(){
			
		}*/
	
	};
	
	
		
});