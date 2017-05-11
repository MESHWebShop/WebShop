/**
 * Controller app for the product pages
 */
(function() {
	
	//Controller for Products
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
		
		$http.get("GetAllProducts").then(onProductsComplete, onError);
	});
	
	
	//Controller for product
	controllerApplication.controller("productController", function($scope, $http,
			$routeParams) {
		$scope.name = "";
		$scope.description = "";
		$scope.price = "";

		var productId = $routeParams.id;

		// alert("productId : " + productId);

		$scope.product = {
			id : 0,
			name : "",
			price : "",
		}

		var onproductComplete = function(response) {
			// alert ("Yay");
			$scope.product = response.data;
		}

		var onError = function(reason) {
			$scope.name = "Could not fetch data! " + reason.status;
		}

		var tmp = "GetProduct?id=" + productId;
		$http.get(tmp).then(onproductComplete, onError);
	});
}());