myApp.controller("BlogController", function($scope, $http) {

	$scope.blog = {
		blogId : 105,
		blogName : "",
		blogContent : ""
	};
	$http.get('http://localhost:2020/Collaboration/getBlogs')
			.then(function(response) {
				$scope.blogdata = response.data;
				console.log(response.data);
			});
	$scope.saveBlogPost = function() {

		$http.post('http://localhost:2020/Collaboration/insertBlog',$scope.blog)
			.then(function(response) {
			$scope.message = "successfully added the blog"
		});
	}

});