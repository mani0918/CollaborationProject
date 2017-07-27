myApp.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "Template/Home.html"
	}).when("/Login", {
		templateUrl : "C_User/Login.html",
		controller : "UserController"
	})

	.when("/Register", {
		templateUrl : "C_User/Register.html",
		controller : "UserController"
	})

	.when("/myProfile", {
		templateUrl : "C_User/myProfile.html",
		controller : "UserController"
	})

	.when("/getAllUsers", {
		templateUrl : "C_User/Admin_manageUser.html",
		controller : "UserController"
	})
	
	.when("/getApprovedUsers", {
		templateUrl : "C_User/Admin_manageUser.html",
		controller : "UserController"
	})
	
	.when("/getRejectedUsers", {
		templateUrl : "C_User/Admin_manageUser.html",
		controller : "UserController"
	})
	.when("/getNewUsers", {
		templateUrl : "C_User/Admin_manageUser.html",
		controller : "UserController"
	})
	
	.when("/Admin", {
		templateUrl : "C_Admin/Admin.html"
	})
	.when("/Blog", {
		templateUrl : "C_Blog/Blog.html",
		controller : "BlogController"
	})
	.when("/getAllBlogs", {
		templateUrl : "C_Blog/Admin_manageBlog.html",
		controller : "BlogController"
	})
	.when("/getRejectedBlogs", {
		templateUrl : "C_Blog/Admin_manageBlog.html",
		controller : "BlogController"
	})
	.when("/getNewBlogs", {
		templateUrl : "C_Blog/Admin_manageBlog.html",
		controller : "BlogController"
	})
	.when("/getApprovedBlogs", {
		templateUrl : "C_Blog/Admin_manageBlog.html",
		controller : "BlogController"
	})
	.when("/Forum", {
		templateUrl : "Web/C_Forum/Forum.html",
		controller : "ForumController"
	})
	.when("/getAllForums", {
		templateUrl : "C_Forum/Admin_manageForum.html",
		controller : "ForumController"
	})
	.when("/getRejectedForums", {
		templateUrl : "C_Forum/Admin_manageForum.html",
		controller : "ForumController"
	})
	.when("/getNewForums", {
		templateUrl : "C_Forum/Admin_manageForum.html",
		controller : "ForumController"
	})
	.when("/getApprovedForums", {
		templateUrl : "C_Forum/Admin_manageForum.html",
		controller : "ForumController"
	})
	
	.when("/postJob",{
		templateUrl : "C_Job/Admin_postAJob.html",
		controller : "JobController"
	})
	
	.when("/getAllJobs",{
		templateUrl : "C_Job/Admin_manageJob.html",
		controller : "JobController"
	})
	
	.otherwise({redirectTo:'/'})


});