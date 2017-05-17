/**
 * Routing script
 */

(function() {

	var routingApplication = angular
			.module("routingApplication", [ "ngRoute" ]);

	routingApplication.config(function($routeProvider) {

		$routeProvider.when("/", {
		//Main viev
			templateUrl : "main.htm",
			controller : "homeController" })
		//Viev all products
		.when("/products", {
			templateUrl : "products.htm",
			controller : "productsController" })
		//Viev one product
		.when("/product/:id", {
			templateUrl : "product.htm",
			controller : "productController" })
		//Viev contact page
		.when("/contact", {
			templateUrl : "contact.htm",
			controller : "contactController" })
		//Viev about page
		.when("/about", {
			templateUrl : "about.htm",
			controller : "aboutController" })
		//Viev shoppingcart page
		.when("/shoppingCart/:cartid", {
			templateUrl : "shoppingCart.htm",
			controller : "shoppingCartController" })
		//Other
		.otherwise({
			redirectTo : "/"
		})
	});

}());
