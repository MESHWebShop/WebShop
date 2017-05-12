/**
 * Controller app for the product pages
 */
(function() {
	

	var controllerApplication = angular.module("routingApplication");
	
	//Controller for Products
	controllerApplication.controller("productsController", function($scope, $http) {
		$scope.title = "Products";
		$scope.description = "This is the Products page";
		
//		var product = {
//				id : 0,
//				name : "",
//				description : "",
//				price : "",
//			};
//			$scope.product = product;
		
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
	controllerApplication.controller("productController", function($scope, $http, $routeParams) {
		$scope.title = "Product page";

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