myApp.controller("FriendController", function($scope, $http, FriendService, $rootScope, $cookieStore, $location, $route){
	console.log("Starting of Friend Controller");
	
	this.user = {	
			userId 		: '',
			firstname 	: '',
			lastname 	: '',
			password 	: '',
			emailId 		: '',
			role 		: '',
			status 		: '',		
			isOnline 	: '',
			remarks 	: '',
			errorMessage: '',
			errorCode	: ''		
		};
	
	this.friend = {
	        
	        tableId: '',
	        userId: '',
	        friendId: '',
	        status: '',
	        isOnline: '',
	        errorCode: '',
	        errorMessage: ''
	       
	}
	
	this.getMyFriendRequests=[];
	this.getMyFriends=[];
	this.friends = [];
	this.users=[];
	//Get All Users
	
	this.getMyFriendRequests = function(){
		console.log("Starting of getMyFriendRequests() in FriendController");
		
		FriendService.getMyFriendRequests()
		.then(
				function(response){
					if(response.errorCode!='404'){
						this.getMyFriendRequests = response;
						$rootScope.getMyFriendRequests = response;
						localStorage.setItem('getMyFriendRequests', JSON.stringify(this.getMyFriendRequests));
					} else {
						alert(response.errorMessage);
						console.error(response.errorMessage);
						localStorage.removeItem('getMyFriendRequests');
					}
					console.log(response);
				}
		);
	};
	
	this.getMyFriendRequests();
	
	
	this.getAllUsers=function(){
		console.log("starting og getAllUsers function");
		FriendService.getAllUsers()
				.then(
				function(serviceData){
					this.users=serviceData;
					$rootScope.users=serviceData;
					console.log($rootScope.users);
				}		
				);
	};
	this.getAllUsers();
	
	
	this.getFriendRequestsSentByMe = function(){
		console.log("Starting of getFriendRequestsSentByMe() in FriendController");
		
		FriendService.getFriendRequestsSentByMe()
		.then(
				function(response){
					if(response.errorCode!='404'){
						this.getFriendRequestsSentByMe = response;
						$rootScope.getFriendRequestsSentByMe = response;
						localStorage.setItem('getFriendRequestsSentByMe', JSON.stringify(this.getFriendRequestsSentByMe));
					} else {
						alert(response.errorMessage);
						console.error(response.errorMessage);
						localStorage.removeItem('getFriendRequestsSentByMe');
					}
					console.log(response);
				}
		);
	};
	
	this.getFriendRequestsSentByMe();
	
	this.getMyFriends = function(){
		console.log("Starting of getMyFriends() in FriendController");
		
		FriendService.getMyFriends()
		.then(
				function(response){
					console.log(response);
					if(response.errorCode!='404'){
						this.getMyFriends = response;
						$rootScope.getMyFriends = response;
						localStorage.setItem('getMyFriends', JSON.stringify(this.getMyFriends));
					} else {
						alert(response.errorMessage);
						console.error(response.errorMessage);
						localStorage.removeItem('getMyFriends');
					}
					console.log(response);
				}
		);
	};
	
	this.getMyFriends();
	
	

	
	this.addFriend = function(friendId){
		console.log("Starting of addFriend() in FriendController");
		
		FriendService.addFriend(friendId)
		.then(
				function(response){
					this.friend = response;
					$rootScope.friend = response;
					if(this.friend.errorCode == '404'){
						console.error(this.friend.errorMessage);
						alert(this.friend.errorMessage);
					} else {
						console.log(this.friend.errorMessage);
						alert(this.friend.errorMessage);
						
					}
				}
		);
	};
	
	this.acceptFriend = function(friendId){
		console.log("Starting of acceptFriend() in FriendController");
		
		FriendService.acceptFriend(friendId)
		.then(
				function(response){
					this.friend = response;
					$rootScope.friend = response;
					if(this.friend.errorCode == '404'){
						console.error(this.friend.errorMessage);
						$rootScope.errorMessage = this.friend.errorMessage;
					} else {
						console.log(this.friend.errorMessage);
						alert(this.friend.errorMessage);
						$route.reload();
					}
				}
		);
	};
	this.rejectFriend = function(friendId){
		console.log("Starting of rejectFriend() in FriendController");
		
		FriendService.rejectFriend(friendId)
		.then(
				function(response){
					this.friend = response;
					$rootScope.friend = response;
					if(this.friend.errorCode == '404'){
						console.error(this.friend.errorMessage);
						$rootScope.errorMessage = this.friend.errorMessage;
					} else {
						console.log(this.friend.errorMessage);
						alert(this.friend.errorMessage);
						$route.reload();
					}
				}
		);
	};
	this.unfriend = function(friendId){
		console.log("Starting of unfriend() in FriendController");
		
		FriendService.unfriend(friendId)
		.then(
				function(response){
					this.friend = response;
					$rootScope.friend = response;
					console.log(this.friend.errorMessage);
					alert(this.friend.errorMessage);
					if($route.reload())
						console.log("page refresh");
				}
		);
	};
	
});