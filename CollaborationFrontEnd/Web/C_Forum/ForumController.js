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
	this.forums=[];
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
	}
	
});