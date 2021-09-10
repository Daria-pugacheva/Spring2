angular.module('market-front').controller('registrationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.register = function () {
        $http.post(contextPath + '/register', $scope.new_user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.new_user.username, token: response.data.token};
                    alert("Вы успешно зарегистрированы с правами пользователя")

                    $scope.new_user.username = null;
                    $scope.new_user.password = null;
                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

});