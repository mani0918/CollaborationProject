myApp.controller('ForumController',function($http,$rootScope,$route,ForumService,$cookieStore,$scope,$location){
	console.log("--------ForumController");
	this.forum={
			
			forumId : '',
			forumName : '',
			forumContent : '',
			userId : '',
			status :'',
			remarks : '',
			createDate : '',
			errorCode : '',
			errorMessage : ''
			
	};
	this.postNewForum=false;
	this.forumComment = {
			forumCommentId : '',
			forumId : '',
			forumComment : '',
			forumCommentDate : '',
			userId : '',
			username : '',
			errorCode : '',
			errorMessage : ''
	}
	this.forums=[];
	this.forumComments=[];
	this.getAllForums=function(){
		console.log("-----------starting of getAllForums");
		ForumService.getAllForums()
			.then(
					function(forumServiceData){
						$rootScope.forums=forumServiceData;
						console.log($rootScope.forums);
					}
			);
	};
	this.getAllForums();
	
	this.acceptForum=function(id){
		console.log("---------starting of acceptforum");
		ForumService.acceptForum(id)
					.then(
						function(forumServiceData){
							this.forum=forumServiceData;
								$scope.reloadRoute();
							alert(this.forum.errorMessage)
						},
						function(errResponse){
							console.error("error while rejecting the forum");
						}	
					);
	};
	
	this.rejectForum=function(id){
		console.log("--------starting of rejectForum");
		ForumService.rejectForum(id)
					.then(
							function(forumServiceData){
								this.forum=forumServiceData;
								/*alert(this.blog.errorMessage)*/
									$scope.reloadRoute();
								alert("suceessfully rejected")
							},null		
							);
	};
	
	$scope.reloadRoute = function() {
		$route.reload();
			};
	
	this.getForumsByStatus=function(){
		console.log("-----------getForumsByStatus");
		if($location.path()=="/getApprovedForums"){
			return "A";
		}
		else if($location.path()=="/getRejectedForums"){
			return "R";
		}
		else if($location.path()=="/getNewForums"){
			return "N";
			
		}
		else{
			return "";
		}
	};
	
	this.createForum=function(forum){
		console.log(forum);
		console.log("starting of createForum");
		ForumService.createForum(forum)
					.then(
					function(forumServiceData)
					{
						this.forum = forumServiceData;
						if(this.forum.errorCode=="404"){
							alert(this.forum.errorMessage)
							this.forum="";
						}
						else{
							console.log("you have successfully created the forum")
							console.log(this.forum.errorMessage);
							alert(this.forum.errorMessage)
							$route.reload();
						}
					}		
					);
	};
	
	this.updateForum = function(id) {
		console.log("updateUser...")
		ForumService.updateForum(id).then(
				this.getAllForums, null);
	};

	this.update = function() {
		{
			console.log('Update the forum details');
			self.updateUser(id);
		}
		this.reset();
	};
	this.reset = function() {
		this.forum = {
				forumId : '',
				forumName : '',
				forumContent : '',
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
	
	$scope.getForum = getSelectedForum
	function getSelectedForum(forumId)
	{
		console.log("Entering Get Forum "+forumId)
		ForumService.getForum(forumId)
		.then
		(
				function(response)
				{
					console.log("Get Forum Success "+response.status)
					console.log(response)
					$scope.forum=response;
					
					
				}
		)
		ForumService.getComments(forumId)
		.then
		(
				function(response)
				{
					console.log("Get comments for "+forumId)
					console.log(response)
					$rootScope.forumComments = response;
    				localStorage.setItem('forumComments', JSON.stringify(response));
					
				}
		);
		$location.path("/viewForum")
	}
	
	this.addComment = addComment
	function addComment(forumId)
	{
		$scope.forumComment.forumId = forumId;
		ForumService.addComments(forumId, $scope.forumComment)
		.then
		(
			function(response)
			{
				console.log("Add Forum Comment "+response.status)
				$scope.reloadRoute();
				$location.path('/viewForum');
				
			}
		);
	}
	
	this.userClickedPostAForum = function(){
		console.log(this.postNewForum);
		if(this.postNewForum == true){
			this.postNewForum = false;
			console.log(this.postNewForum+"in if");
		} else{
			this.postNewForum = true;
			console.log(this.postNewForum+"in else");
		}
		console.log(this.postNewForum);
	}
	
});