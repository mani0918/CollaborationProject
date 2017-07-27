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
		
		getAllBlogComments:function(id){
			console.log("calling getAllBlogComments");
			return $http.get(BASE_URL+'/getAllCommentsByBlogId/'+id)
					.then(
							function(response){
								
								return response.data;
							},null
					);
					
		},
		
		createBlog:function(blog){
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
                return $http.put(BASE_URL+'/updateBlog/', id)  //2
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
		
	};
});