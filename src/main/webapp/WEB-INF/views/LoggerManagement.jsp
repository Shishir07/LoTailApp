<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
  	
  	  <div class="view-container">
      	<div ng-view class="view-frame"></div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/listView.html' />"></script>
	  <script src="<c:url value='/static/detailView.html' />"></script>
      <script src="<c:url value='/static/js/service/logs_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/logs_controller.js' />"></script>
	  <script src="<c:url value='/static/js/service/log_detail_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/log_detail_controller.js' />"></script>
  </body>
</html>