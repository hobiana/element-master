/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 var demo = angular.module('app', ['iso.directives', 'hljs','ngAnimate','ngSanitize','timer']);

 
demo.controller('defisCtrl', function($scope,$http) {
    
    
    $scope.$on('timer-tick', function (event, data) {
        if ($scope.timerRunning === true && data.millis >= $scope.deadlineInMilli) {
            $scope.$apply($scope.turnRed);
        }
    });
    $scope.idtoken = "";
        
      
      $scope.xList = [];
      $scope.xElement = "";
      $scope.historiques =[];
      $scope.motherElements = [];
      
      $scope.loadDefisMode = function(idtoken){
          $http.get("https://elementmaster.herokuapp.com/JSONRequest/getDefisJSON.jsp?idtoken="+idtoken).then(function(response) {
                $scope.xElement = response.data[0];
                $scope.idtoken = idtoken;
                $http.get("https://elementmaster.herokuapp.com/JSONRequest/getElementsBasesJSON.jsp").then(function(response) {
                    $scope.xList = response.data;
					$scope.$broadcast('timer-clear');
					$scope.$broadcast('timer-start');
                });
            });
      };
      
      $scope.getElement = function()
      {
          return $scope.xElement;
      };
      
      $scope.addElement = function(index)
      {
          if($scope.motherElements.length >= 3){
              $scope.motherElements = [];
              $scope.motherElements.push($scope.xList[index]);
          }
          else{
              $scope.motherElements.push($scope.xList[index]);
          }
          
      };
      
      $scope.removeMotherElement = function(index)
      {
            $scope.motherElements.splice(index,1);
      };
      //fonction mavesatra ka mila ovaina
      $scope.contains = function(idelement)
      {
          for(var i = 0 ; i < $scope.xList.length ; i++)
          {
              if($scope.xList[i].idelement===idelement)return i;
          }
          return -1;
      };
      
      
      //Fusionner AJAX
      $scope.fusionnerElements = function()
      {
          var idElements = "{";
          var nomElements = "";
          var nomfilleElements = "";
          var listFiltered = $scope.motherElements.slice(0);
          listFiltered.sort(function (a, b) {
            return a.idelement - b.idelement;
          });
          
          for(var i = 0; i < $scope.motherElements.length ; i++)
          {
              idElements += listFiltered[i].idelement;
              nomElements += " <b class='mere'>"+$scope.motherElements[i].nom+"</b>";
              if(i+1 < $scope.motherElements.length){
                  nomElements += ",";
                  idElements += ",";
              }
          }
          idElements += "}";
          
          var url = "&idelements="+idElements;
          $scope.childrenElements = [];
          $http.get("https://elementmaster.herokuapp.com/JSONRequest/getElementsFusion.jsp?idtoken="+$scope.idtoken+url).then(function(response)
          {
              var currdate = new Date();
                var time = "["+ $scope.humanizeTime(currdate.getHours()) + ":" + $scope.humanizeTime(currdate.getMinutes()) +"]";
                $scope.childrenElements = response.data;
                for(var i = 0; i < $scope.childrenElements.length ; i++)
                {
                    if($scope.childrenElements[i].idelement===$scope.xElement.idelement)
                    {
                        $scope.$broadcast('timer-stop');
                        alert("Bravo ! Vous avez trouvé l'élément ! Votre temps est "+$scope.getScore());
                        $scope.monterNiveau();
                    }
                    
                    if($scope.contains($scope.childrenElements[i].idelement)===-1)
                    {
                        $scope.historiques.push({"time":time,"histo":"Nouvel élément trouvé <b class='histo'>"+$scope.childrenElements[i].nom+"</b>."});
                        $scope.xList.push($scope.childrenElements[i]);
                    }
                  
                  
                    nomfilleElements += " <b class='fille'>"+$scope.childrenElements[i].nom+"</b>";
                    if(i+1 < $scope.childrenElements.length){
                        nomfilleElements += ",";
                    }
                    else nomfilleElements += ".";
                }
                if($scope.childrenElements.length!==0)
                {
                    var histo = "La fusion des éléments"+nomElements+" a donné"+nomfilleElements;
                    $scope.historiques.push({"time" : time,"histo" : histo});
                }
                
          });
      };
      
      //Fissionner AJAX
      $scope.fissionnerElements = function()
      {
          var idElement = "";
          var nomElement = "";
          var nomfilleElements = "";
          if($scope.motherElements.length > 1)
          {
              alert("Pour fissioner il ne faut qu'un seul éléments.");
          }
          else
          {
              idElement = $scope.motherElements[0].idelement;
              nomElement = " <b class='fille'>"+$scope.motherElements[0].nom+"</b>";
          
            var url = "?idelement="+idElement;
            $scope.childrenElements = [];
            $http.get("https://elementmaster.herokuapp.com/JSONRequest/getElementsFission.jsp"+url).then(function(response)
            {
                 var currdate = new Date();
                var time = "["+ $scope.humanizeTime(currdate.getHours()) + ":" + $scope.humanizeTime(currdate.getMinutes()) +"]";
                $scope.childrenElements = response.data;
                for(var i = 0; i < $scope.childrenElements.length ; i++)
                {
                    if($scope.childrenElements[i].idelement===$scope.xElement.idelement)
                    {
                        $scope.$broadcast('timer-stop');
                        alert("Bravo ! Vous avez trouvé l'élément ! Votre temps est "+$scope.getScore());
                        $scope.monterNiveau();
                    }
                    
                    if($scope.contains($scope.childrenElements[i].idelement)===-1)
                    {
                        $scope.historiques.push({"time":time,"histo":"Nouveau élément trouvé <b class='histo'>"+$scope.childrenElements[i].nom+"</b>."});
                        $scope.xList.push($scope.childrenElements[i]);
                    }
                    
                    nomfilleElements += " <b class='mere'>"+$scope.childrenElements[i].nom+"</b>";
                    if(i+1 < $scope.childrenElements.length){
                        nomfilleElements += ",";
                    }
                    else nomfilleElements += ".";
                }
               
                if($scope.childrenElements.length!==0)
                {
                    var histo = "La fission de l'élément"+nomElement+" a donné"+nomfilleElements;
                    $scope.historiques.push({"time" : time,"histo" : histo});
                }
                
            });
          }
            
      };
      $scope.humanizeTime = function(time)
      {
        return time < 10 ? '0' + time : time;
      };
      
      
      $scope.getScore = function()
      {
          var times = document.getElementById("timer");
          return times.innerHTML;
      };
      $scope.monterNiveau = function()
      {
          $http.get("https://elementmaster.herokuapp.com/RemoteRequest/upgradeLevel.jsp?idtoken="+$scope.idtoken+"&time="+$scope.getScore()+"&level="+$scope.xElement.level).then(function() {
               $scope.loadDefisMode($scope.idtoken);
               $scope.$broadcast('timer-clear');
               $scope.$broadcast('timer-start');
            });
      };
});
//

    
    