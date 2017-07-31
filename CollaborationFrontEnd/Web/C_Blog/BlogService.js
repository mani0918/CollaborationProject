myApp.service('BlogService',function($http,$q,$rootScope){
	console.log("BlogService-----");
	var BASE_URL='http://localhost:2020/Collaboration'
		return{
		
		getAllBlogs:function(){
			console.log("getAllBlogs function in userservice");
			return $http.get(BASE_URL+'/getAllBlogs')
				.then(
						function(response){
							console.log("getAllBlogs response")
							return response.data;
							
						},
						null
				);
		},
		
		accept:function(id){
			console.log("calling accept-----");
			return $http.put(BASE_URL+'/approveBlog/'+id)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error("error while approving the blog");
						}
						
					);
			
		},
		
		reject: function(id){
			console.log("reject method-------");
			return $http.put(BASE_URL+'/rejectBlog/'+id)
					.then(
					function(response){
						return response.data;
					},
					null
					);
		},
		
		/*getAllBlogComments:function(id){
			console.log("calling getAllBlogComments");
			return $http.get(BASE_URL+'/getAllCommentsByBlogId/'+id)
					.then(
							function(response){
								
								return response.data;
							},null
					);
					
		},*/
		
		createBlog:function(blog){
			console.log(blog);
			console.log("calling createBlog");
			return $http.post(BASE_URL+'/insertBlog',blog)
						.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error("error while creating the blog");
							return $q.reject(errResponse);
						}
						
						);
		},
		
		updateBlog: function(id){
        	console.log("calling updateBlog ")
                return $http.put(BASE_URL+'/updateBlog/'+id)  //2
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while updating user');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        
        getBlog: function(blogId)
    	{
    		return $http.get(BASE_URL + "/getBlog/"+blogId)
    		.then(function(response)
    		{
    			console.log(response.status)
    			console.log(response.data.errorCode)
    			if(response.data.errorCode !=404 )
    				$rootScope.gBlog = response.data
    				localStorage.setItem('gBlog', JSON.stringify(response.data));
    			console.log($rootScope.gBlog)
    			return response.data;
    		}, function(errResponse)
    		{
    			console.log(errResponse.status)
    			return errResponse.data;
    		});
    	},
    	
    	getComments: function(blogId)
    	{
    		return $http.get(BASE_URL + "/getAllCommentsByBlogId/"+blogId)
    		.then(
    		function(response)
    		{
    			console.log(response.status)
    			console.log(response.data)
    			$rootScope.blogComments = response.data
    			return response.data;
    		});
    	},
    	
    	addComments: function(blogId, blogComment)
    	{
    		console.log("Adding Comments")
    		return $http.post(BASE_URL + "/insertBlogComment", blogComment)
    		.then(
    		function(response)
    		{
    			console.log(response.status)
    			console.log("Added Blog")
    			return response.data;
    		});
    	}
    	
		
	};
});