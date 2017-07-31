myApp.controller('BlogController', function($scope, $http, BlogService, $rootScope, $route, $cookieStore, $location) {
	$scope.message="test";
	console.log("BlogController-----------");

	this.blog = {

		blogId : '',
		blogName : '',
		blogContent : '',
		userId : '',
		createDate : '',
		status : '',
		likes : '',
		remarks : '',
		errorCode : '',
		errorMessage : ''

	};
	this.postNewBlog=false;
	this.blogComment = {
			blogCommentId : '',
			blogId : '',
			blogComment : '',
			blogCommentDate : '',
			userId : '',
			username : '',
			errorCode : '',
			errorMessage : ''
	}
	
	$rootScope.isUserViewComments=true;
	this.blogs = [];
	this.blogComments=[];
	this.getAllBlogs = function() {
		console.log("-------starting of getAllBlogs function in blogcontroller");
		BlogService.getAllBlogs().then(
				function(blogServiceData) {
					$rootScope.blogs=blogServiceData;
					console.log($rootScope.blogs);
					/*$cookieStore.put('blogServiceData',this.blogs);
					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.loggedInUser;*/
		});
	};
	
	this.getAllBlogs();
	
	this.accept = function(id){
		console.log("-------accept");
		BlogService.accept(id)
			.then(
					function(blogServiceData){
						this.blog=blogServiceData;
					
						$scope.reloadRoute();
						alert(this.blog.errorMessage)
					},
					function(errResponse){
						console.error("error while accepting the blog");
					}
			);
		
	};
	
	this.reject=function(id){
		console.log("--------reject");
		
		BlogService.reject(id)
			.then(
			function(blogServiceData){
				this.blog=blogServiceData;
				/*alert(this.blog.errorMessage)*/
					$scope.reloadRoute();
				alert("suceessfully rejected")
			},null		
			);
			
	};
	
	/*this.getRejectedBlogs=function(){
		console.log("------getRejectedBlogs");
		BlogService.getRejectedBlogs
				.then(
						function(blogServiceData){
							$rootScope.blogs=blogServiceData;
						}
				);
	};*/
	
	
	$scope.reloadRoute = function() {
		$route.reload();
			};
			
			
	this.getBlogsByStatus=function(){
		console.log("-------getBlogsByStatus");
		if($location.path() == "/getApprovedBlogs"){
		/*	this.blog.status="A";*/
			return "A";
		}
		else if($location.path()=="/getRejectedBlogs"){
			return "R";
		}
		else if($location.path()=="/getNewBlogs"){
			return "N";
		}
		else{
			return "";
		}
	};	
	
	/*this.getAllBlogComments=function(id){
		console.log("starting of getAllBlogComments");
		BlogService.getAllBlogComments(id)
					.then(
							function(blogServiceData){
								$rootScope.isUserViewComments=false;
								$rootScope.blogComments=blogServiceData;
								$scope.reloadRoute();
								
							}
					);
	};*/
	
	this.createBlog=function(blog){
		console.log(blog);
		console.log("starting of createBlog");
		BlogService.createBlog(blog)
					.then(
					function(blogServiceData){
						this.blog = blogServiceData;
						if(this.blog.errorCode=="404"){
							alert(this.blog.errorMessage)
							this.blog="";
						}
						else{
							console.log("you have successfully created the blog")
							console.log(this.blog.errorMessage);
							alert(this.blog.errorMessage)
							$route.reload();
						}
					}		
					);
	};
	
	this.updateBlog = function(id) {
		console.log("updateUser...")
		BlogService.updateBlog(id).then(
				this.getAllBlogs, null);
	};

	this.update = function() {
		{
			console.log('Update the blog details');
			self.updateUser(id);
		}
		this.reset();
	};
	this.reset = function() {
		this.blog = {
				blogId : '',
				blogName : '',
				blogContent : '',
				userId : '',
				createDate : '',
				status : '',
				likes : '',
				remarks : '',
				errorCode : '',
				errorMessage : ''
		};
		$scope.myForm.$setPristine(); // reset Form
	};	
	
	$scope.getBlog = getSelectedBlog
	function getSelectedBlog(blogId)
	{
		console.log("Entering Get blog "+blogId)
		BlogService.getBlog(blogId)
		.then
		(
				function(response)
				{
					console.log("Get Blog Success "+response.status)
					console.log(response)
					$scope.blog=response;
					
					
				}
		)
		BlogService.getComments(blogId)
		.then
		(
				function(response)
				{
					console.log("Get comments for "+blogId)
					console.log(response)
					$rootScope.blogComments = response;
    				localStorage.setItem('blogComments', JSON.stringify(response));
					
				}
		);
		$location.path("/viewBlog")
	}
	
	this.addComment = addComment
	function addComment(blogId)
	{
		$scope.blogComment.blogId = blogId;
		BlogService.addComments(blogId, $scope.blogComment)
		.then
		(
			function(response)
			{
				console.log("Add Blog Comment "+response.status)
				$scope.reloadRoute();
				$location.path('/viewBlog');
				
			}
		);
	}
	
	this.userClickedPostABlog = function(){
		console.log(this.postNewBlog);
		if(this.postNewBlog == true){
			this.postNewBlog = false;
			console.log(this.postNewBlog+"in if");
		} else{
			this.postNewBlog = true;
			console.log(this.postNewBlog+"in else");
		}
		console.log(this.postNewBlog);
	}
});