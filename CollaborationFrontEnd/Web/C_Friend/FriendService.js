myApp.service("FriendService", function($http, $q) {
	console.log("Starting friend service");
	var BASE_URL = 'http://localhost:2020/Collaboration';
	
	return {
		
		addFriend : function(friendId){
			console.log("Starting of add friend method in FriendService")
			
			return $http.get(BASE_URL+"/addFriend/"+friendId)
			.then(
					function(response) {
						return response.data;
					}, function(errResponse){
						console.log("Error while sending friend request");
					}
			);
		},
		 getAllUsers: function(){
	        	console.log("calling getAllUsers");
	        	return $http.get(BASE_URL+'/getUsers')
	        			.then(
	        					function(response){
	        						return response.data;
	        					},
	        					null
	        			
	        			);
	        },
	        
		getMyFriendRequests : function(){
			console.log("Starting of getMyFriendRequests() in FriendService");
			
			return $http.get(BASE_URL+"/getMyFriendRequests")
			.then(
					function(response){
						return response.data;
					}, function(errResponse){
						console.log("Error while retreving getMyFriendRequests");
					}
			);
		},
		getFriendRequestsSentByMe : function(){
			console.log("Starting of getFriendRequestsSentByMe() in FriendService");
			
			return $http.get(BASE_URL+"/getRequestsSendByMe")
			.then(
					function(response){
						return response.data;
					}, function(errResponse){
						console.log("Error while retreving getFriendRequestsSentByMe");
					}
			);
		},
		getMyFriends : function(){
			console.log("Starting of getMyFriends() in FriendService");
			
			return $http.get(BASE_URL+"/myFriends")
			.then(
					function(response){
						return response.data;
					}, function(errResponse){
						console.log("Error while retreving myFriends");
					}
			);
		},
		acceptFriend : function(friendId){
			console.log("Starting of accept friend method in FriendService")
			
			return $http.put(BASE_URL+"/acceptFriend/"+friendId)
			.then(
					function(response) {
						return response.data;
					}, function(errResponse){
						console.log("Error while accepting friend request");
					}
			);
		},
		rejectFriend : function(friendId){
			console.log("Starting of reject friend method in FriendService")
			
			return $http.put(BASE_URL+"/rejectFriend/"+friendId)
			.then(
					function(response) {
						return response.data;
					}, function(errResponse){
						console.log("Error while rejecting friend request");
					}
			);
		},
		unfriend : function(friendId){
			console.log("Starting of unfriend method in FriendService")
			
			return $http.put(BASE_URL+"/unFriend/"+friendId)
			.then(
					function(response) {
						return response.data;
					}, function(errResponse){
						console.log("Error while unfriending request");
					}
			);
		}
	};
	
	console.log("Ending friend service");
});