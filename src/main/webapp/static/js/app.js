'use strict';

var app = angular.module('myApp',["ngRoute"]);


app.config(function($routeProvider) {
	//$locationProvider.hashPrefix('!');

    $routeProvider.
      when('/view1', {
        templateUrl: 'static/listView.html'
      }).
      when('/view2/:logName', {
    	  templateUrl: 'static/detailView.html'
      }).
      otherwise('/view1');
	});