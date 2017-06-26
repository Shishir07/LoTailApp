'use strict';

angular.module('myApp').controller('LogsController', ['$scope', 'LogsService', function($scope, LogsService) {
    var self = this;
    self.user={id:null,name:''};
    self.users=[];
    var wsURL ='ws://localhost:8080/CoverfoxLogListing-1.0.0/';
    var ws = new WebSocket(wsURL+'handleServerSocket');
    fetchAllLogs();

    function fetchAllLogs(){
        LogsService.fetchAllLogs()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Logs');
            }
        );
    }


}]);
