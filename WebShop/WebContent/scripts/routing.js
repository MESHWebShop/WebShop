/**
 * Routing script
 */

(function() {

	var routingApplication = angular
			.module("routingApplication", [ "ngRoute" ]);

	routingApplication.config(function($routeProvider) {

		$routeProvider.when("/", {
		//Main view
			templateUrl : "main.htm",
			controller : "homeController" })
		//View all products
		.when("/products", {
			templateUrl : "products.htm",
			controller : "productsController" })
		//View one product
		.when("/product/:id", {
			templateUrl : "product.htm",
			controller : "productController" })
		//View contact page
		.when("/contact", {
			templateUrl : "contact.htm",
			controller : "contactController" })
		//View about page
		.when("/about", {
			templateUrl : "about.htm",
			controller : "aboutController" })
		//View shoppingcart page
		.when("/shoppingCart/:cartid", {
			templateUrl : "shoppingCart.htm",
			controller : "shoppingCartController" })
		//Other
		.otherwise({
			redirectTo : "/"
		})
	});

}());
