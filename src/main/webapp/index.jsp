<%-- 
    Document   : index
    Created on : 24 janv. 2017, 21:39:54
    Author     : ITU
--%>

<%@page import="Utilitaires.Format"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Format.checkConnexion(request, response);
    Format.header(out, "Accueil");
    Format.templateNav(out,request,response);
%>

<body ng-app="app" ng-controller="indexCtrl">
    <div class="container-fluid " id="main">
    <div class="col-md-10 offset-md-1">
        <div class="row">
            <div class="col-md-6  bg-faded">

            </div>
            <div class="col-md-6">
                <form id="form" class="form-horizontal" action="inscription.jsp">
                    <h3 class="text-sm-center bg-faded" >Inscription</h3>
                    <span id="spanMessage" ></span>
                    <div class="form-group">
                        <label for="nom">Nom</label>
                        <input type="text" ng-model="forms.nom" name="nom" class="form-control" id="inputNom" placeholder="Nom">
                    </div>
                    <div class="form-group">
                        <label for="prenom">Prénoms</label>
                        <input type="text" ng-model="forms.prenoms" name="prenoms" class="form-control" id="inputPrenom" placeholder="Prénom">
                    </div>
                    <div ng-class="class">
                        <label for="pseudo">Pseudo</label>
                        <input type="text" ng-model="forms.pseudo" ng-change="checkPseudo()" name="pseudo" class="form-control" id="inputPseudo" placeholder="Pseudonyme">
                    </div>
                    <div class="form-group">
                        <label for="adresse">Adresse</label>
                        <input type="email" ng-model="forms.mail" name="mail" class="form-control" id="inputAdresse" placeholder="Adresse">
                    </div>
                    <div class="form-group">
                        <label for="adresse">Mot de passe</label>
                        <input type="password" ng-model="forms.pwd" name="pwd" class="form-control" id="inputPassword" placeholder="Mot de passe">
                    </div>
                    <button type="submit" class="btn btn-primary pull-sm-right" ng-disabled="disable">Enregistrer</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/MasterElements/js/appIndex.js"></script>
<%
    Format.footer(out);
%>
