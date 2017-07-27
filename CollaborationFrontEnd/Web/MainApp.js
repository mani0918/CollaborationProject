var myApp = angular.module("myApp",["ngRoute","ngCookies"]);
myApp.run(function($http,$rootScope,$cookieStore){
	$rootScope.loggedInUser=$cookieStore.get('loggedInUser') || {};
	if($rootScope.loggedInUser){
		$http.defaults.headers.common['Authorization'] = 'Basic'
			+ $rootScope.loggedInUser;
	}
	var loginUser=$rootScope.loggedInUser;
	console.log(loginUser);
})