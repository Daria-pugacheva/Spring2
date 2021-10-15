angular.module('market-front').controller('productController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.showProductInfo = function (){
        $http.get(contextPath + 'api/v1/products/'+ $routeParams.productId)
            .then(function successCallback (response){
                $scope.current_product=response.data;
            },function failureCallback (response){
                alert(response.data.messages);
                $location.path('/store');
            });
    }

    //Пробовала из store передавать не id, а продукт, но не получилось :(...
    // $scope.showProductInfo = function (){
    //     $scope.current_product = $routeParams.product;
    // }

    $scope.showProductfeedback = function (){
        $http.get(contextPath + 'api/v1/feedbacks/'+ $routeParams.productId)
            .then(function successCallback (response){
                $scope.current_feedbacks=response.data;
            },function failureCallback (response){
                alert(response.data.messages);
                $location.path('/store');
            });
    }

    $scope.saveFeedback = function (productId){
        $http.post(contextPath + 'api/v1/feedbacks/' + productId, $scope.feedback)
            .then(function successCallback (response){
                $scope.feedback=null;
                alert("Отзыв сохранен");
                $scope.showProductfeedback();
            },function failureCallback (response){
                alert(response.data.messages);
            });
    }


    $scope.showProductInfo();
    $scope.showProductfeedback();

});