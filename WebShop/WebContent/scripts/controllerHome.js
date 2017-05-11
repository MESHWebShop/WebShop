/**
 * Controller app for the home page
 */
(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("homeController", function($scope) {
		$scope.title = "Home";
		$scope.description = "This is the Home page for MESH Webshop";
	});
}());