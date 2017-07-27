myApp.service("UserService", function($http, $q) {
	console.log("calling UserService")
	var BASE_URL = 'http://localhost:2020/Collaboration'
	return {
		validate : function(user) {

			console.log("calling validate method");
			return $http.post(BASE_URL + '/login', user).then(function(response) {
				return response.data;
			}, null);

		},

		logout : function() {
			console.log("-------start of logout function");
			return $http.get(BASE_URL + '/logout').then(function(response) {
				return response.data;

			}, null);
		},

		createUser : function(user) {
			console.log("calling createUser");
			return $http.post(BASE_URL + '/insertUser', user).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error("error while creating user");
				return $q.reject(errResponse);
			});
		},
		
		 myProfile: function() {
        	console.log("calling myProfile ")
                return $http.get(BASE_URL+'/myProfile')
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                               null
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
        
        acceptUser: function(id){
        	console.log("calling acceptUser");
        	return $http.get(BASE_URL+'/approve/'+id)
        			.then(
        					function(response){
        						return response.data;
        					},
        					function(errResponse){
        						console.error("error while approving the user");
        					}
        			);
        },
        
        rejectUser: function(id){
        	console.log("calling rejectUser");
        	return $http.put(BASE_URL+'/reject/'+id)
        			.then(
        					function(response){
        						console.log("response from rejectUser")
        						return response.data;
        					},null
        			
        			);
        }

	};
});