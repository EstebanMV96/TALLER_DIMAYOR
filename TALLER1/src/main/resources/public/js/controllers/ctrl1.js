var appController1 = function($scope, $log, $http) {

    $scope.entradaNombre = "sebastian";

    $scope.latitud;
    $scope.longitude;
    $scope.message;

    $scope.getInfo = function() {

        $scope.equipos = [];
        $http({
                url: '/equipos',
                method: 'get'
            })
            .then(function(res) {

                $log.info(res);
                if (res.status == 200) {
                    $scope.equipos = res.data;
                    $scope.length = res.data.length;
                    // $scope.latitud = res.data.iss_position.latitude;
                    // $scope.longitude = res.data.iss_position.longitude;
                    // $scope.message = res.data.message;
                    // $scope.timestamp = res.data.timestamp;
                }
            });

    }

}

myApp.controller("myctrl1", appController1);