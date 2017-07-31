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
		},
		
		createForum:function(forum){
			console.log(forum);
			console.log("calling createForum");
			return $http.post(BASE_URL+'/insertForum',forum)
						.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error("error while creating the forum");
							return $q.reject(errResponse);
						}
						
						);
		},
		
		updateForum: function(id){
        	console.log("calling updateForum")
                return $http.put(BASE_URL+'/updateForum/'+id)  //2
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while updating forum');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        
        getForum: function(forumId)
    	{
    		return $http.get(BASE_URL + "/getForum/"+forumId)
    		.then(function(response)
    		{
    			console.log(response.status)
    			console.log(response.data.errorCode)
    			if(response.data.errorCode !=404 )
    				$rootScope.gForum = response.data
    				localStorage.setItem('gForum', JSON.stringify(response.data));
    			console.log($rootScope.gForum)
    			return response.data;
    		}, function(errResponse)
    		{
    			console.log(errResponse.status)
    			return errResponse.data;
    		});
    	},
    	
    	getComments: function(forumId)
    	{
    		return $http.get(BASE_URL + "/getAllCommentsByForumId/"+forumId)
    		.then(
    		function(response)
    		{
    			console.log(response.status)
    			console.log(response.data)
    			$rootScope.forumComments = response.data
    			return response.data;
    		});
    	},
    	
    	addComments: function(forumId, forumComment)
    	{
    		console.log("Adding Comments")
    		return $http.post(BASE_URL + "/insertForumComment", forumComment)
    		.then(
    		function(response)
    		{
    			console.log(response.status)
    			console.log("Added Forum")
    			return response.data;
    		});
    	}
    	
	};
});