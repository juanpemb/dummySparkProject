//para hacer uso de $resource debemos colocarlo al crear el modulo
var app = angular.module("app", ["ngResource"]);

//con dataResource inyectamos la factoría
app.controller("appController", function ($scope, $http, dataResource) {
    $http.get('/home-assistant').success(function (data) {
        $scope.datos = data;
    });
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