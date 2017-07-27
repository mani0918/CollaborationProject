myApp.service("ForumService",function($http,$q,$rootScope){
	console.log("ForumService-------");
	var BASE_URL='http://localhost:2020/Collaboration';
	return{
		getAllForums:function(){
			console.log("calling getAllForums function from forumService");
			return $http.get(BASE_URL+'/getAllForums')
				.then(
				    function(response){
				    	console.log("response from getAllForums");
				    	return response.data;
				    },null		
				);
		},
		
		acceptForum:function(id){
			console.log("calling acceptForum function");
			return $http.put(BASE_URL+'/approveForum/'+id)
					.then(
					function(response){
						console.log("response from forumservice");
						return response.data;
					},
					function(errResponse){
						console.error("error while approving the forum");
					}
					);
		},
		
		rejectForum:function(id){
			console.log("calling rejectForum function");
			return $http.put(BASE_URL+'/rejectForum/'+id)
					.then(
					function(response){
						console.log("response from forumservice");
						return response.data;
						
					},null
					);
		}
	};
});