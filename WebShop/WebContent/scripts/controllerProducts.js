/**
 * Controller app for the products and product pages
 */
(function() {

	var controllerApplication = angular.module("routingApplication");
	
/*---------------------------------------------------------------------------------------*/
/*----- Controller for Products ---------------------------------------------------------*/
	controllerApplication.controller("productsController", function($scope,
			$http) {
		$scope.title = "Products";

		// Add product to cart
		$scope.addProductToCart = function(id) {
			alert("Product:" + id);
			// $scope.product = response.data;
		}

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
	
/*---------------------------------------------------------------------------------------*/	
/*----- Controller for product ----------------------------------------------------------*/
	controllerApplication.controller("productController", function($scope,
			$http, $routeParams) {
		$scope.title = "Product page";

		var productId = $routeParams.id
		// alert("productName : " + productId);

		$scope.product = {
			id : 0,
			name : "",
			description : "",
			price : "",
		};

		var onProductComplete = function(response) {
			$scope.product = response.data;
		}

		var onError = function(reason) {
			$scope.name = "Could not fetch data! " + reason.status;
		}

		// Add product to cart		
		var onAddProductToCartComplete = function(response) {
			$scope.addedProduct = "Produkt lades till i kundvagnen.";
		}
		
		$scope.addProductToCart = function(id) {
			alert("Product:" + id);
			$http.get("AddProductToCart?productId=" + id).then(onAddProductToCartComplete, onError);
		};

		// Get product by name
		var tmp = "GetProduct?name=" + productId;
		$http.get(tmp).then(onProductComplete, onError);
	});

}());