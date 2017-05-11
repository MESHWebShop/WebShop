/**
 * Controller app for the products pages
 */
(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("productsController", function($scope, $http) {
		$scope.title = "Products";
		$scope.description = "This is the Products page";
		
		var products = [];
		$scope.products = products;
		
		var onProductsComplete = function(response) {
			$scope.products = response.data;
		}
		
		var onError = function(reason) {
			$scope.name = "Could not fetch data! " + reason.status;
		}
		
		$http.get("GetProducts").then(onProductsComplete, onError);
	});
}());