/**
 *  Controller app for the Shopping Cart pages
 */
(function() {
	var controllerApplication = angular.module("routingApplication");

	controllerApplication.controller("shoppingCartController", function($scope, $http, $routeParams) {
		$scope.title = "ShoppingCart";
		
		var cartId = $routeParams.cartid;
		
		$scope.deleteProduct = function(productId) {
			//alert("Product ID "+productId);
			//$scope.shoppingCart.splice(index, 1);
			$http.get("RemoveProductFromCart?cartId=1&productId=" + productId).then(onCartComplete, onError);

		};
		
		$scope.updateProductCountInCart = function(productId){
			alert("Product ID: "+productId +" Antal: ")
		}

		var onCartComplete = function(response) {
			$scope.products = response.data;
		}

		var onError = function(reason) {
			$scope.name = "Could not fetch data! " + reason.status;
		}

		var tmp = "GetAllProductsInCart?cartId=" + cartId;
		//alert(tmp);
		$http.get(tmp).then(onCartComplete, onError);
	});
}());