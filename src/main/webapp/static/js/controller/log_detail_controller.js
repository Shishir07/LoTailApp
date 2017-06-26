'use strict';

angular.module('myApp').controller('LogDetailController', ['$scope','$routeParams','LogDetailService', function($scope,$routeParams,LogDetailService) {
    var self = this;
	var name = $routeParams.logName;
	self.name=name;
	self.logs=[];
	var wsURL ='ws://localhost:8080/CoverfoxLogListing-1.0.0/';
    var ws = new WebSocket(wsURL+'handleServerSocket');
    ws.onopen = function(){
    	var appendedName = name.replace(".", "_");
		ws.send(appendedName);
	};
	ws.onmessage = function (evt){
		if(evt.data!=null && evt.data!=undefined && evt.data!=''){
			var received_msg = evt.data;    
			var myObject = eval('(' + received_msg + ')');  
			$scope.ctrl.logs	= 	myObject;
			$scope.$apply();
		}
	};
	
	ws.onclose = function(){
		console.log("Valuation WebSocket connection closed...");
		ws.close();
	};
    
    fetchlogDetail(name);

    function fetchlogDetail(name){
        LogDetailService.fetchlogDetail(name)
            .then(
            function(d) {
                $scope.ctrl.logs = d;
            },
            function(errResponse){
                console.error('Error while fetching Log details');
            }
        );
    }


}]);
