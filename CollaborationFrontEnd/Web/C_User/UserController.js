myApp.controller("UserController", function($scope, UserService, $http, $route,$location, $rootScope, $cookieStore) {

	console.log("-------UserController")
	this.user = {
		userId : '',
		firstname : '',
		lastname : '',
		password : '',
		emailId : '',
		role : '',
		status : '',
		isOnline : '',
		remarks : '',
		errorCode : '',
		errorMessage : ''
	};
	this.isUserLoggedIn = false;
	this.loggedInUser = {
		userId : '',
		firstname : '',
		lastname : '',
		password : '',
		emailId : '',
		role : '',
		status : '',
		isOnline : '',
		remarks : '',
		errorCode : '',
		errorMessage : ''
	};
	$rootScope.message="";
	this.users = [];
	this.validate = function(user) {
		console.log("----validate method");
		UserService.validate(user).then(function(serviceData) {
			this.user = serviceData;
			console.log("user.errorCode: " + this.user.errorCode)
			if (this.user.errorCode == "404") {
				alert(this.user.errorMessage)

				this.user.userId = "";
				this.user.password = "";

			} else {
				console.log("valid credentials");
				$rootScope.isUserLoggedIn = true;
				$rootScope.loggedInUser = this.user;

				$cookieStore.put('loggedInUser', this.user);
				$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.loggedInUser;
				$location.path('/');

			}
		}, function(errResponse) {
			console.error("Error while validating user");
		});

	};
	this.logout = function() {
		console.log("-----userController logout function");

		$rootScope.isUserLoggedIn = false
		$rootScope.loggedInUser = {}
		$cookieStore.remove('loggedInUser');
		UserService.logout()
		$location.path('/');

	};
	this.createUser = function(user) {
		console.log("calling createUser in iuserController");
		UserService.createUser(user).then(function(serviceData) {
			this.user = serviceData
			if (this.user.errorCode == "404") {
				alert(this.user.errorMessage)

				this.user.userId = "";
				this.user.password = "";

			}
			else{
				console.log("Thank you for registering in my website");
				console.log(this.user.errorMessage);
				$rootScope.message=this.user.errorMessage;
				$location.path('/Login');
			}

		});
	};
	
	this.myProfile = function() {
		console.log("myProfile...")
		UserService
				.myProfile()
				.then(
						function(serviceData) {
							this.user = serviceData;
							$location
									.path("/myProfile")
						},
						function(errResponse) {
							console
									.error('Error while fetch profile.');
						});
	};
	
	this.getAllUsers=function(){
		console.log("starting og getAllUsers function");
		UserService.getAllUsers()
				.then(
				function(serviceData){
					$rootScope.users=serviceData;
					console.log($rootScope.users);
				}		
				);
	};
	this.getAllUsers();
	
	this.getUsersByStatus=function(){
		console.log("starting of getUsersByStatus");
		if($location.path()=="/getApprovedUsers"){
			return "A";
		}
		else if($location.path()=="/getRejectedUsers"){
			return "R";
		}
		else if($location.path()=="/getNewUsers"){
			return "N";
			
		}
		else{
			return "";
		}
	};
	
	$scope.reloadRoute = function() {
		$route.reload();
			};
			
	this.acceptUser=function(id){
		console.log("starting of acceptUser");
		UserService.acceptUser(id)
					.then(
							function(serviceData){
								this.user=serviceData;
								$scope.reloadRoute();
								alert(this.user.errorMessage);
							},
							function(errResponse){
								console.error("error while approving the user");
							}
					);
		
	};
	
	this.rejectUser=function(id){
		console.log("starting of rejectUser");
		UserService.rejectUser(id)
				.then(
						function(serviceData){
							this.user=serviceData;
							$scope.reloadRoute();
							alert(this.user.errorMessage)
						},
						function(errResponse){
							console.error("error while rejecting the user");
						}
				);
	};
});