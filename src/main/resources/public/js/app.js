//para hacer uso de $resource debemos colocarlo al crear el modulo
var app = angular.module("app", ["ngResource"]);

//con dataResource inyectamos la factoría
app.controller("appController", function ($scope, $http, dataResource) {
    $http.get('/home-assistant').success(function (data) {
        $scope.datos = data;
    });

    $scope.SendData = function () {
               // use $.param jQuery function to serialize data from JSON
                var data = {
                    name: $scope.name,
                    cp: $scope.cp
                }

                var config = {
                    headers : {
                        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                    }
                }

                $http.post('/home-assistant', data, config)
                .success(function (data, status, headers, config) {
                    $scope.PostDataResponse = data;
                })
                .error(function (data, status, header, config) {
                    $scope.ResponseDetails = "Data: " + data +
                        "<hr />status: " + status +
                        "<hr />headers: " + header +
                        "<hr />config: " + config;
                });
            };
})

//de esta forma tan sencilla consumimos con $resource en AngularJS
app.factory("dataResource", function ($resource) {
    return $resource("js/data.json", //la url donde queremos consumir
        {}, //aquí podemos pasar variables que queramos pasar a la consulta
        //a la función get le decimos el método, y, si es un array lo que devuelve
        //ponemos isArray en true
        { get: { method: "GET", isArray: true }
    })
})