angular.module('market-front').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8190/market-order/';

    $scope.loadCart = function () {
        $http({
            url: 'http://localhost:8191/market-cart/api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data;
        });
    };

    $scope.createOrder = function () {
        //вот не уверена, что именно так можно добавить хэдер с именем пользователя, чтобы бэк получил его в RequestHeaders
        $http.defaults.headers.common = 'username: ' + $localStorage.webMarketUser.username;
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            alert("Ваш заказ успешно сформирован");
            $location.path("/");
        });
    };

    $scope.loadCart();


});