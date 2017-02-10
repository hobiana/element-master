<%-- 
    Document   : libre
    Created on : 25 janv. 2017, 00:17:19
    Author     : ITU
--%>



<%@page import="Utilitaires.Format"%>
<% Format.checkSession(request, response); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Format.header(out, "Accueil");
%>
    

<%
    Format.templateNav(out,request, response);
%>

<body ng-app="app" ng-controller="libreCtrl" ng-init="loadElementsDebloquer('<%=request.getSession().getAttribute("utilisateur")%>')">
<div class="container-fluid " id="main">
    <div class="row">
        <div class="col-md-8">
            <div class="row table-deck">
                <div class="card-deck text-sm-center" >
                    <div class="card elem" ng-repeat="x in motherElements track by $index">
                        <div class="exit"><a href="#" ng-click="removeMotherElement($index)"><i class="fa fa-close" aria-hidden="true"></i></a></div>
                        <img class="card-img" src="https://elementmaster.herokuapp.com/images/{{x.image}}" alt="{{x.nom}}">
                        <div class="card-block">
                          <h5 class="card-title">{{x.nom}}</h5>
                        </div>
                    </div>
                    
                </div>
            </div>
            <div class="row">
                <div class="text-sm-center">
                    <button class="btn btn-primary btn-sm" ng-click="fusionnerElements()">Fusionner</button>
                    <button class="btn btn-primary btn-sm" ng-click="fissionnerElements()">Fissionner</button>
                    <h2 class="text-sm-center"><i class="fa fa-chevron-down" aria-hidden="true"></i></h2>
                </div>
            </div>
            <div class="row table-deck">
                
                <div class="card-deck text-sm-center">
                    <div class="card elem" ng-repeat="x in childrenElements">
                        <img class="card-img" src="https://elementmaster.herokuapp.com/images/{{x.image}}" alt="{{x.nom}}">
                        <div class="card-block">
                          <h5 class="card-title">{{x.nom}}</h5>
                        </div>
                    </div>
                    
                </div>
            </div>
            
            <div class="row container-fluid">
                    
                    <div class="historique">
                        <b for="histo" class="histo">Historiques</b>
                        <div ng-repeat="x in historiques"><div ng-bind-html="x.time +' : '+ x.histo"></div></div>
                    </div>
            </div>
        </div>
        <div class="col-md-4" >
            <h3>Elements trouvés : {{xList.length}}/100</h3>
            <hr>
                <div class="form-group">
                  <input type="text" class="form-control"  ng-model="filtername" placeholder="Rechercher éléments" >
                </div>
                <div class="btn-group" opt-kind ok-key="sortBy">
                    <button type="button" class="btn btn-primary" ok-sel=".name">Nom</button>
                    <button type="button" class="btn btn-primary active" ok-sel="[number]" ok-type="integer">Numéro</button>
                </div>
            <div class="row">
                <ul isotope-container="isotope-container">
                    
                    <li ng-repeat="x in xList | filter : {nom:filtername}" isotope-item="isotope-item" class="kindling" ng-style="{'background-image':'url(' + '/images/'+ x.image + ')'}" ng-click="addElement($index)">
                        <div class="symbol">{{x.nom}}</div>
                        <div class="number" number="{{x.idelement}}">{{x.idelement}}</div>
                    </li>
                </ul>
            </div>
            
        </div>
    </div>

</div>
<script src="https://elementmaster.herokuapp.com/js/jquery.isotope.min.js"></script>
<script src="https://elementmaster.herokuapp.com/js/angular-animate.min.js"></script>
<script src="https://elementmaster.herokuapp.com/js/angular-sanitize.min.js"></script>
<script src="https://elementmaster.herokuapp.com/js/angular-isotope.min.js"></script>
<script src="https://elementmaster.herokuapp.com/js/app.js"></script>
<script src="https://elementmaster.herokuapp.com/js/angular-highlightjs.min.js"></script>
<%
    Format.footer(out);
%>
