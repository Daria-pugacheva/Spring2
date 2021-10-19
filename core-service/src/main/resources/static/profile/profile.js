angular.module('market-front').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market-core/';

    $scope.loadOrders = function () {
        //вот не уверена, что именно так можно добавить хэдер с именем пользователя, чтобы бэк получил его в RequestHeaders
        $http.defaults.headers.common = 'username: ' + $localStorage.webMarketUser.username;
        $http ({
            url: 'http://localhost:8190/market-order/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    };

    $scope.loadMyProfile = function () {
        $http ({
            url: contextPath + 'api/v1/users/me',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        });
    };

    $scope.loadMyProfile();

    $scope.loadOrders();


});