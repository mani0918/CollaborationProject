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

	.when("/Profile", {
		templateUrl : "C_User/Profile.html",
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
	.when("/viewBlog", {
		templateUrl : "C_Blog/viewBlog.html",
		controller : "BlogController"
	})
	
	.when("/Forum", {
		templateUrl : "C_Forum/Forum.html",
		controller : "ForumController"
	})
	.when("/viewForum", {
		templateUrl : "C_Forum/viewForum.html",
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
	.when("/Chat",{
		templateUrl : "C_Chat/Chat.html",
		controller : "ChatController"
	})
	.when("/Job",{
		templateUrl : "C_Job/Job.html",
		controller : "JobController"
	})
	.when("/getVacantJobs",{
		templateUrl : "C_Job/Admin_manageJob.html",
		controller : "JobController"
	})
	.when("/getClosedJobs",{
		templateUrl : "C_Job/Admin_manageJob.html",
		controller : "JobController"
	})
	.when("/getAppliedJobs",{
		templateUrl : "C_Job/Admin_manageAppliedJobs.html",
		controller : "JobController"
	})
	.when("/JobsApplied",{
		templateUrl : "C_Job/JobApplied.html",
		controller : "JobController"
	})
	.when("/requestsSent",{
		templateUrl : "C_Friend/RequestsSent.html",
		controller : "FriendController"
	})
	.when("/myFriends",{
		templateUrl : "C_Friend/MyFriends.html",
		controller : "FriendController"
	})
	.when("/allUsers",{
		templateUrl : "C_Friend/AllUsers.html",
		controller : "FriendController"
	})
	.when("/receivedFriendRequests",{
		templateUrl : "C_Friend/NewFriendsRequests.html",
		controller : "FriendController"
	})
	.otherwise({redirectTo:'/'})


});