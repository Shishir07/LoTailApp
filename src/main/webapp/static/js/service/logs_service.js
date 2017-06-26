'use strict';

angular.module('myApp').factory('LogsService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/CoverfoxLogListing-1.0.0/logs/';

    var factory = {
        fetchAllLogs: fetchAllLogs
    };

    return factory;

    function fetchAllLogs() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Logs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


}]);
