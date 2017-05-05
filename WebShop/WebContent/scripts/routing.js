/**
 * Routing script
 */

(function() {

	var routingApplication = angular.module("routingApplication", [ "ngRoute" ]);

	routingApplication.config(function($routeProvider) {
		
		$routeProvider
		.when("/", {templateUrl : "main.htm", controller: "homeController"})
		.when("/products", {templateUrl : "products.htm", controller: "productsController"})
		.when("/contact", {templateUrl : "contact.htm", controller: "contactController"})
		.when("/about", {templateUrl : "about.htm", controller: "aboutController"})
		.otherwise({redirectTo:"/"})
	});
	
	routingApplication.controller("homeController", function($scope){
		$scope.title = "Home";
		$scope.description = "This is the Home page for Mesh Webshop";
	});
	routingApplication.controller("productsController", function($scope){
		$scope.title = "Products";
		$scope.description = "This is the Products page";
	});
	routingApplication.controller("contactController", function($scope){
		$scope.title = "Contact";
		$scope.description = "This is the Contact page";
	});
	routingApplication.controller("Controller", function($scope){
		$scope.title = "About";
		$scope.description = "This is the About page";
	});
}());



