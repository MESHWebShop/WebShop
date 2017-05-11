/**
 *  Controller app for the Contact pages
 */
(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("contactController", function($scope) {
		$scope.title = "Home";
		$scope.description = "This is the Home page for MESH Webshop";
	});
}());