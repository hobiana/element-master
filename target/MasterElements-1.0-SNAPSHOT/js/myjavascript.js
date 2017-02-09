/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// external js: isotope.pkgd.js

$('.grid').isotope({
  layoutMode: 'fitRows',
  itemSelector: '.grid-item'
});


function selectFormPersonnel()
{
    var personnel = document.getElementsByName('selectedPersonnel')[0].value;
    var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
		} 
        else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//////////
	xhttp.onreadystatechange=function() {
		if (xhttp.readyState==4 && xhttp.status==200) {
                        
                        var span = document.getElementById('formSelected');
                        //var num = document.getElementById('num').value;
                        //alert(xhttp.responseText);
                        //var cat = JSON.parse(xhttp.responseText);
                        //alert(cat[num].nomCategorie);
			span.innerHTML = xhttp.responseText;
		}
	  };
	xhttp.open("POST", "http://localhost:8080/HospitalProjectWeb/templates/FormPersonnel.jsp", true);
	
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send('personnel='+personnel);
}

function checkSpecialite()
{
    var specialite = document.getElementById('inputNewSpecialite').value;
    var personnel = document.getElementsByName('selectedPersonnel')[0].value;
    var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
		} 
        else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//////////
	xhttp.onreadystatechange=function() {
		if (xhttp.readyState==4 && xhttp.status==200) {
                        
                        var span = document.getElementById('checkSpecialite');
                        //var num = document.getElementById('num').value;
                        //alert(xhttp.responseText);
                        //var cat = JSON.parse(xhttp.responseText);
                        //alert(cat[num].nomCategorie);
			span.innerHTML = xhttp.responseText;
		}
	  };
	xhttp.open("GET", "http://localhost:8080/HospitalProjectWeb/templates/checkSpecialite.jsp?spe="+specialite+"&pers="+personnel, true);
        xhttp.send();
    
}

function insertSpecialite()
{
    var specialite = document.getElementById('inputNewSpecialite').value;
    var personnel = document.getElementsByName('selectedPersonnel')[0].value;
    
    var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
		} 
        else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//////////
	xhttp.onreadystatechange=function() {
		if (xhttp.readyState==4 && xhttp.status==200) {
                        
                        var span = document.getElementById('checkSpecialite');
                        var inputSpecialite = document.getElementById('inputSpecialite');
                        //var num = document.getElementById('num').value;
                        //alert(xhttp.responseText);
                        //var cat = JSON.parse(xhttp.responseText);
                        //alert(cat[num].nomCategorie);
			span.innerHTML = xhttp.responseText;
                        inputSpecialite.innerHTML += "<option value='"+specialite+"'>"+specialite+"</option>";
		}
	  };
	xhttp.open("GET", "http://localhost:8080/HospitalProjectWeb/templates/insertSpecialite.jsp?spe="+specialite+"&pers="+personnel, true);
        xhttp.send();
}
function updatePersonne(id)
{
    var nom = document.getElementById('inputNom').value;
    var prenom = document.getElementById('inputPrenom').value;
    var sexe = document.getElementsByName('optionsSexe')[0].value;
    var adresse = document.getElementById('inputAdresse').value;
    var personnel = document.getElementsByName('poste')[0].value;
    var specialite = document.getElementById('inputSpecialite');
    
    var specialites = "";
    for(var i=0;i<specialite.options.length;i++)
    {
        if(specialite.options[i].selected)
        {
            specialites += specialite.options[i].value+';';
        }
    }
    //alert(specialites);
    
    var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
		} 
        else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//////////
	xhttp.onreadystatechange=function() {
		if (xhttp.readyState==4 && xhttp.status==200) {
                        
                        // var span = document.getElementById('formUpdate');
			//span.innerHTML = xhttp.responseText;
                        if(confirm("Succès. Vous voulez revenir ?"))
                        {
                            location.replace("http://localhost:8080/HospitalProjectWeb/Personnel");
                        }
                        
		}
	  };
	xhttp.open("POST", "http://localhost:8080/HospitalProjectWeb/templates/updatePersonnel.jsp", true);
	
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send('nom='+nom+'&prenom='+prenom+'&adresse='+adresse+'&sexe='+sexe+'&pers='+personnel+'&spe='+specialites+'&idpersonne='+id);

}
function insertPersonne()
{
    var nom = document.getElementById('inputNom').value;
    var prenom = document.getElementById('inputPrenom').value;
    var sexe = document.getElementsByName('optionsSexe')[0].value;
    var adresse = document.getElementById('inputAdresse').value;
    var personnel = document.getElementsByName('selectedPersonnel')[0].value;
    var specialite = document.getElementById('inputSpecialite');
    
    
    var specialites = "";
    for(var i=0;i<specialite.options.length;i++)
    {
        if(specialite.options[i].selected)
        {
            specialites += specialite.options[i].value+';';
        }
    }
    alert(specialites);
    
    var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
		} 
        else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//////////
	xhttp.onreadystatechange=function() {
		if (xhttp.readyState==4 && xhttp.status==200) {
                        
                        var span = document.getElementById('formInsert');
                        //var num = document.getElementById('num').value;
                        //alert(xhttp.responseText);
                        //var cat = JSON.parse(xhttp.responseText);
                        //alert(cat[num].nomCategorie);
                        nom = "";
                        prenom = "";
                        adresse = "";
                        sexe = "";
			span.innerHTML = xhttp.responseText;
                        
		}
	  };
	xhttp.open("POST", "http://localhost:8080/HospitalProjectWeb/templates/insertPersonnel.jsp", true);
	
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send('nom='+nom+'&prenom='+prenom+'&adresse='+adresse+'&sexe='+sexe+'&pers='+personnel+'&spe='+specialites);
}

var app = angular.module('myApp', []);

    app.controller('namesCtrl', function($scope,$http) {
        $http.get("http://localhost:8080/HospitalProjectWeb/templates/getPersonneJSON.jsp").then(function(response) {
            $scope.myData = response.data;
        });
        $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
      };
    });
    
    app.controller('paiementCtrl', function($scope,$http) {
        $scope.facture = null;
        $scope.paiementMode = null;
        $scope.montantpaie = null;
        $scope.facturefiltered = [];
        $scope.selected = [];
        $scope.montantSelected = [];
        $scope.page = 0;
        
        $scope.getAll = true;
        $scope.getFiltered = true;
        
        
        $http.get("http://localhost:8080/HospitalProjectWeb/templates/getFactureJSON.jsp").then(function(response) {
            $scope.factureList = response.data;
        });
        $http.get("http://localhost:8080/HospitalProjectWeb/templates/getModePaiementJSON.jsp").then(function(response) {
            $scope.paiementList = response.data;
        });
        $http.get("http://localhost:8080/HospitalProjectWeb/templates/getPatientJSON.jsp").then(function(response) {
            $scope.patientList = response.data;
        });
        
        
        $scope.getIndex = function()
        {
            for(i = 0; i < $scope.factureList.length; i++)
            {
                if($scope.factureList[i].idfacture == $scope.facture)
                    return i;
            }
            return null;
        };
        $scope.getIndexIdFacture = function(idfacture)
        {
            for(i = 0; i < $scope.factureList.length; i++)
            {
                if($scope.factureList[i].idfacture === idfacture)
                    return i;
            }
            return null;
        };
        
        $scope.totalFacture = function()
        {
            return $scope.factureList[$scope.getIndex()].montantview;
        };
        
        $scope.payeFacture = function()
        {
            return $scope.factureList[$scope.getIndex()].montantpaye;
        };
        
        $scope.resteFacture = function()
        {
            return $scope.factureList[$scope.getIndex()].reste;
        };
        
        $scope.setButtonPayer = function()
        {
            if($scope.resteFacture()===0)
            {
                return 'disabled';
            }
        };
        $scope.setButtonPayerList = function(x)
        {
            if($scope.factureList[$scope.getIndexIdFacture(x)].reste===0)
            {
                return 'disabled';
            }
        };
        
        
        
        //PAGINATION
        $scope.pagineNext = function()
        {
            if($scope.page+10 < $scope.facturefiltered.length)$scope.page += 10;
        };
        $scope.paginePrevious = function()
        {
            if($scope.page-10<0)$scope.page = 0;
            else $scope.page -= 10;
        };
        $scope.getNumberPage = function() {
            var num = parseInt($scope.facturefiltered.length / 10);
            return new Array(num+1);   
        };
        $scope.pagineTo = function(x)
        {
            if(x*10 < $scope.facturefiltered.length)$scope.page = x*10;
        };
        
        //NAV
        $scope.showAll = function()
        {
            $scope.getAll = true;
            $scope.page = 0;
        };
        $scope.showPaid = function()
        {
            $scope.getAll = false;
            $scope.getFiltered = true;
            $scope.page = 0;
        };
        $scope.showUnpaid = function()
        {
            $scope.getAll = false;
            $scope.getFiltered = false;
            $scope.page = 0;
        };
        
        //SELECTED
        $scope.showChecked = function(x)
        {
            var str = "";
            for(var i = 0; i< x.length ; i++)
            {
                str += x[i]+";";
            }
            alert(str);
        };
        $scope.toggleSelection = function toggleSelection(idFacture) {
            var idx = $scope.selected.indexOf(idFacture);
            // is currently selected
            if (idx > -1) {
              $scope.selected.splice(idx, 1);
            }
            // is newly selected
            else {
              $scope.selected.push(idFacture);
            }
          };
          $scope.selectAll = function(x) {

              if(!x)
              {
                    $scope.selected = [];
              }
              else
              {
                    $scope.selected = [];
                    for(var i = 0 ; i<$scope.facturefiltered.length ; i++)
                    {
                        if($scope.facturefiltered[i].reste !== 0)
                        $scope.selected.push($scope.facturefiltered[i].idfacture);
                    }
              }
          };
          
          
          
         $scope.payerMultiple= function()
        {
            var strMontant ="";
            var strIdFacture = "";
            var boolError = false;
            for(var i = 0; i< $scope.selected.length ; i++)
            {
                if(isNaN($scope.montantSelected[$scope.selected[i]]) || $scope.montantSelected[$scope.selected[i]] == " " || $scope.montantSelected[$scope.selected[i]] == ""){
                    alert("Le montant  pour le paiement de la facture "+$scope.factureList[$scope.getIndexIdFacture($scope.selected[i])].descriptionfacture+" est incorecte");
                    boolError = true;
                    break;
                }
                else if($scope.montantSelected[$scope.selected[i]]>$scope.factureList[$scope.getIndexIdFacture($scope.selected[i])].reste)
                {
                    alert("Le montant  pour le paiement de la facture "+$scope.factureList[$scope.getIndexIdFacture($scope.selected[i])].descriptionfacture+" dépasse le reste");
                    boolError = true;
                    break;
                }
                else{
                    strMontant += $scope.montantSelected[$scope.selected[i]];
                    strIdFacture += $scope.selected[i];
                    if(i+1< $scope.selected.length)
                    {
                        strMontant += ";";
                        strIdFacture += ";";
                    }
                }
            }
            if($scope.paiementMode === null)
            {
                alert("Séléctionez un mode de paiement.");
                boolError = true;
            }
            if(!boolError)
            {
                var url = ("http://localhost:8080/HospitalProjectWeb/templates/payerFacture.jsp?idfacture="+strIdFacture+"&idpaiement="+$scope.paiementMode+"&montantpaie="+strMontant);
                $http.get(url).then(function(response) {
                    $http.get("http://localhost:8080/HospitalProjectWeb/templates/getFactureJSON.jsp").then(function(response) {
                        $scope.factureList = response.data;
                        $scope.paiementMode = null;
                        $scope.montantSelected = [];
                        $scope.selected = [];
                        $scope.page = 0;
                        alert("Faits !")
                        
                    });

                });
            }
        };
        
        
        //input Property
        $scope.setInputMontant = function(x) {
            var idx = $scope.selected.indexOf(x);
            //alert(idx);
            if(isNaN($scope.montantSelected[x]) || $scope.montantSelected[x] == " " || $scope.montantSelected[x] == null || $scope.montantSelected[x] == "")
            {
                if(idx > -1)$scope.selected.splice(idx, 1);
            }
            else
            {
                if(idx === -1)$scope.selected.push(x);
            }
          };
        
        $scope.payerFacture = function()
        {
            if($scope.montantpaie<=$scope.resteFacture())
            {
                $http.get("http://localhost:8080/HospitalProjectWeb/templates/payerFacture.jsp?idfacture="+$scope.facture+"&idpaiement="+$scope.paiementMode+"&montantpaie="+$scope.montantpaie).then(function(response) {
                    $http.get("http://localhost:8080/HospitalProjectWeb/templates/getFactureJSON.jsp").then(function(response) {
                        $scope.facture = null;
                        $scope.paiementMode = null;
                        $scope.montantpaie = null;
                        $scope.factureList = response.data;
                        if(confirm('Paiement enregistré. Se rediriger vers la liste des factures ?')){
                            location.replace("http://localhost:8080/HospitalProjectWeb/facture.jsp");
                        };
                    });

                });
            }
            else alert('Le montant dépasse le reste.');
            
        };
        
        //PDF
        $scope.saveFacture = function(x)
        {
            html2canvas(document.getElementById('exportthis'), {
                onrendered: function (canvas) {
                    var data = canvas.toDataURL();
                    var docDefinition = {
                        content: [{
                            image: data,
                            width: 500
                        }]
                    };
                    pdfMake.createPdf(docDefinition).download(x+".pdf");
                }
            });
        };
        
        
    });
    
    app.controller('formController', function($scope,$http) {
      $scope.master = {};

      $scope.update = function(user) {
        $scope.master = angular.copy(user);
      };
      
      $scope.validate = function ()
      {
          $http({
              method : 'post',
              url : 'https://www.google.com/recaptcha/api/siteverify',
              data : "secret=6LfyixIUAAAAACUEAeDV9BrFJldJYR5JvlTnSZXi&response="+angular.element('#g-recaptcha-response').val(),
              headers:{'Content-Type':'application/x-www-form-urlencoded'}
          }).success(function(response) {
                    alert ("success");
                }).error(function(){ alert("error"); });
      };

      $scope.reset = function() {
        $scope.user = angular.copy($scope.master);
      };
      
      $scope.isPassValid = function(pswd)
      {
          var boolean = false;
            if ( pswd.length < 8 ) {
                boolean = true;
            }
            
            //validate letter
            if (! pswd.match(/[a-z]/) ) {
                boolean = true;
            }
            
            //validate capital letter
            if (! pswd.match(/[A-Z]/) ) {
                boolean = true;
            }
            
            //validate number
            if (! pswd.match(/\d/) ) {
                boolean = true;
            }
            
            //validate !@$&
            if (! pswd.match(/[-_.éè&,;:!?*#]/) ) {
                boolean = true;
            }
            
            return boolean;
      };

      $scope.reset();
    });
    
    
    app.controller('modifSpeCtrl', function($scope,$http) {
        $scope.poste = null;
        $http.get("http://localhost:8080/HospitalProjectWeb/templates/getPosteJSON.jsp").then(function(response) {
            $scope.posteList = response.data;
        });
        
        $scope.changePosteSelect = function(poste)
        {
            $scope.poste=poste;
            $http.get("http://localhost:8080/HospitalProjectWeb/templates/getSpecialiteJSON.jsp?idposte="+$scope.poste).then(function(response) {
                $scope.myData = response.data;
            });
        };
        
        $scope.updatePoste = function(newName)
        {
            $http.get("http://localhost:8080/HospitalProjectWeb/templates/updatePoste.jsp?idposte="+$scope.poste+"&poste="+newName).then(function(response) {
                $scope.poste=newName;
                $http.get("http://localhost:8080/HospitalProjectWeb/templates/getPosteJSON.jsp").then(function(response) {
                    $scope.posteList = response.data;
                });
            });
        };
        
        $scope.updateSpecialite = function(index,nomspecialite)
        {
            $http.get("http://localhost:8080/HospitalProjectWeb/templates/updateSpecialite.jsp?idspe="+$scope.myData[index].idspecialite+"&specialite="+nomspecialite).then(function(response) {
                //$scope.posteList = response.data;
                alert('Fait !');
            });
        };
        
        $scope.deleteSpecialite = function(index)
        {
            if(confirm('Cette action est irréversible. Vous voulez continuer ?'))
            {
                $http.get("http://localhost:8080/HospitalProjectWeb/templates/deleteSpecialite.jsp?idspe="+$scope.myData[index].idspecialite).then(function(response) {
                    //$scope.posteList = response.data;
                    $http.get("http://localhost:8080/HospitalProjectWeb/templates/getSpecialiteJSON.jsp?idposte="+$scope.poste).then(function(response) {
                        $scope.myData = response.data;
                    });
                });
            }
        };
        
    });
    
    app.controller('factureCtrl', function($scope,$http) {
        //SOME FAKE
        $scope.facture = [];
        $scope.patient = null;
        $scope.desFacture = null;
        $scope.dateFacture = null;
        
        $http.get("http://localhost:8080/HospitalProjectWeb/templates/getMouvementJSON.jsp").then(function(response) {
            $scope.mouvementList = response.data;
        });
        $http.get("http://localhost:8080/HospitalProjectWeb/templates/getPatientJSON.jsp").then(function(response) {
            $scope.patientList = response.data;
        });
        
        $scope.sauverFacture = function()
        {
            var str = JSON.stringify($scope.facture);
                $http.get("http://localhost:8080/HospitalProjectWeb/templates/sauverFacture.jsp?stringifiedFacture="+str+"&idpatient="+$scope.patient+"&desFacture="+$scope.desFacture+"&dateFacture="+$scope.dateFacture).then(function(response) {
                if(confirm('Facturation enregistré. Se rediriger vers la liste des factures ?')){
                        location.replace("http://localhost:8080/HospitalProjectWeb/facture.jsp");
                    };
            });
        };
        
        $scope.ajouterFacture = function(index)
        {
            var inside = false;
            for(i = 0; i < $scope.mouvementList.length; i++)
            {
                if($scope.mouvementList[i].idmouvementfille == index){
                    
                    
                    for(k = 0; k < $scope.facture.length; k++)
                    {
                        if($scope.mouvementList[i].idmouvementfille == $scope.facture[k].idmouvementfille)
                        {
                            $scope.facture[k].quantite += 1;
                            inside = true;
                            break;
                        }
                    }
                    
                    if(!inside)
                    {
                        $scope.facture.push({
                        idmouvementfille: $scope.mouvementList[i].idmouvementfille,
                        descriptionmouvementfille: $scope.mouvementList[i].descriptionmouvementfille,
                        quantite: 1,
                        prixunitaire:$scope.mouvementList[i].prixunitaire
                        });
                    }
                    break;
                    
                }
            }
             
        };
        $scope.totalFacture = function()
        {
            var total = 0;
            for(i = 0; i < $scope.facture.length; i++)
            {
                total += $scope.facture[i].prixunitaire*$scope.facture[i].quantite;
            }
            return total;
        };
        
    });
    
    app.filter('higherThan',function(){
    	return function(array, min) {
                if ( array == undefined ) return array;
                else if (!isNaN(min)){
                    var tableauPlat = [];
                    for(i = 0; i < array.length; i++)
                    {
                        if(array[i].prixplat > min){tableauPlat.push(array[i]);}
                    }
                    return tableauPlat;
                }
                else return array;
                
    	};
    });

    app.filter('lowerThan',function(){
    	return function(array, max) {
                if ( array == undefined ) return array;
                else if (!isNaN(max)){
                    if(max==0||max==null)return array;
                    var tableauPlat = [];
                    for(i = 0; i < array.length; i++)
                    {
                        if(array[i].prixplat < max){tableauPlat.push(array[i]);}
                    }
                    return tableauPlat;
                }
                else return array;
                
    	};
    });
    
    app.filter('unique', function() {
   return function(collection, keyname) {
      var output = [], 
          keys = [];

      angular.forEach(collection, function(item) {
          var key = item[keyname];
          if(keys.indexOf(key) === -1) {
              keys.push(key);
              output.push(item);
          }
      });

        return output;
     };
    });
   
   app.filter('paid', function() {
   return function(collection, keyname, value, reverse, all) {
      var output = [];
      if(all) output = collection;
      else
      {
          angular.forEach(collection, function(item) {
            var key = item[keyname];

            if(key === value && reverse) {
                output.push(item);
            }
            else if(key !== value && !reverse)
            {
               output.push(item);
            }
        });
      }
      

      return output;
        };
     });
     
     app.filter('filterSpe', function() {
   return function(collection, keyname) {
      var output = [], 
          keys = [];
      angular.forEach(collection, function(item) {
          var key = item[keyname];
          if(keys.indexOf(key) === -1) {
              keys.push(key);
              output.push(item);
          }
          else
          {
              if(output[keys.indexOf(key)]['nomspecialite'].indexOf(item['nomspecialite']) === -1)
                output[keys.indexOf(key)]['nomspecialite']+= ", "+item['nomspecialite'];
          }
      });

        return output;
     };
    });