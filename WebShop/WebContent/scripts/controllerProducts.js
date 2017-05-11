/**
 * Controller app for the products pages
 */
(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("productsController", function($scope) {
		$scope.title = "Products";
		$scope.description = "This is the Products page";
	});
}());