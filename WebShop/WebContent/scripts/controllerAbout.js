/**
 *  Controller app for the About pages
 */

(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("aboutController", function($scope) {
		$scope.title = "About";
		$scope.description = "This is the Home page for MESH Webshop";
	});
}());