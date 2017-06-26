'use strict';

angular.module('myApp').factory('LogDetailService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/CoverfoxLogListing-1.0.0/logDetail/';

    var factory = {
        fetchlogDetail: fetchlogDetail
    };

    return factory;

    function fetchlogDetail(name) {
        var deferred = $q.defer();
        var appendedName = name.replace(".", "_");
		REST_SERVICE_URI = REST_SERVICE_URI+appendedName;
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Log Details');
                deferred.reject(errResponse);
            }
        );
		REST_SERVICE_URI = 'http://localhost:8080/CoverfoxLogListing-1.0.0/logDetail/';
        return deferred.promise;
    }


}]);
