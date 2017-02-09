/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 var demo = angular.module('app',[]);

 

demo.controller('indexCtrl',function($scope,$http)
{
    $scope.forms = {};
    $scope.class = "form-group";
    $scope.disable = false;
    $scope.checkPseudo = function()
    {
        $http({
            method : "post",
            url:"https://elementmaster.herokuapp.com/RemoteRequest/checkPseudo.jsp",
            data : "pseudo="+$scope.forms.pseudo,
            headers:{'Content-Type':'application/x-www-form-urlencoded'}
        })
        .then(function(response){
            if(response.data.valide)
            {
                $scope.class = "form-group";
                $scope.disable = false;
            }
            else
            {
                $scope.class = "form-group has-warning";
                $scope.disable = true;
            }
        });
        
    };
});
    
    