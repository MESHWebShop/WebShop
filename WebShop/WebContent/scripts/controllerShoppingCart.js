/**
 *  Controller app for the Shopping Cart pages
 */
(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("shoppingCartController", function($scope, $http, $routeParams) {
		$scope.title = "ShoppingCart";
		
		var productId = $routeParams.id
		//alert("productName : " + productId);

		$scope.product = {
			id : 0,
			name : "",
			description : "",
			price : "",
		};

		var onProductComplete = function(response) {
			// alert ("Yay");
			$scope.product = response.data;
		}

		var onError = function(reason) {
			$scope.name = "Could not fetch data! " + reason.status;
		}

		var tmp = "GetProduct?name=" + productId;
		$http.get(tmp).then(onProductComplete, onError);
	});
}());