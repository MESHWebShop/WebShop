/**
 *  Controller app for the Shopping Cart pages
 */
(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("shoppingCartController", function($scope) {
		$scope.title = "ShoppingCart";
		$scope.description = "This is the ShoppingCart page";
	});
}());