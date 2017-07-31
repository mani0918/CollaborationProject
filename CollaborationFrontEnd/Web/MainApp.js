var myApp = angular.module("myApp",["ngRoute","ngCookies"]);

myApp.directive('checkImage', function($http) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            attrs.$observe('ngSrc', function(ngSrc) {
                $http.get(ngSrc).success(function(){
                }).error(function(){
                    element.attr('src', 'Resources/ProfilePics/default.png'); // set default image
                });
            });
        }
    };
});



myApp.run(function($http,$rootScope,$cookieStore){
	$rootScope.loggedInUser=$cookieStore.get('loggedInUser') || {};
	if($rootScope.loggedInUser){
		$http.defaults.headers.common['Authorization'] = 'Basic'
			+ $rootScope.loggedInUser;
	}
	var loginUser=$rootScope.loggedInUser;
	console.log(loginUser);
	
				if (localStorage.getItem('gBlog') != "undefined")
					$rootScope.gBlog = JSON.parse(localStorage.getItem('gBlog')) || {};
				if (localStorage.getItem('blogComments') != "undefined")
					$rootScope.blogComments = JSON.parse(localStorage.getItem('blogComments')) || {};
				if (localStorage.getItem('gForum') != "undefined")
					$rootScope.gForum = JSON.parse(localStorage.getItem('gForum')) || {};
				if (localStorage.getItem('forumComments') != "undefined")
					$rootScope.forumComments = JSON.parse(localStorage.getItem('forumComments')) || {};
				if (localStorage.getItem('appliedJobsByMe') != "undefined")
						$rootScope.appliedJobsByMe = JSON.parse(localStorage.getItem('appliedJobsByMe')) || {};
						
				if (localStorage.getItem('getMyFriendRequests') != "undefined")
					$rootScope.getMyFriendRequests = JSON.parse(localStorage.getItem('getMyFriendRequests')) || {};
				if (localStorage.getItem('getMyFriends') != "undefined")
					$rootScope.getMyFriends = JSON.parse(localStorage.getItem('getMyFriends')) || {};
				if (localStorage.getItem('getFriendRequestsSentByMe') != "undefined")
					$rootScope.getFriendRequestsSentByMe = JSON.parse(localStorage.getItem('getFriendRequestsSentByMe')) || {};
						
				if (localStorage.getItem('friends') != "undefined")
					$rootScope.friends = JSON.parse(localStorage.getItem('friends')) || {};
		
		
})