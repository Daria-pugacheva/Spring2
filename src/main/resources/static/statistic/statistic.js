angular.module('market-front').controller('statisticController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.showStatistic = function () {
        $http.get(contextPath + 'api/v1/statistic')
            .then(function (response) {
                $scope.timeInfo = response.data;
            });
    };

    $scope.showStatistic();

});
