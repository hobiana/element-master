<%-- 
    Document   : inscription
    Created on : 25 janv. 2017, 03:10:41
    Author     : ITU
--%>

<%@page import="Utilitaires.Format"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Format.header(out, "Accueil");
    Format.templateNav(out,request, response);
%>


    <div class="container-fluid " id="main">
    <div class="col-md-10 offset-md-1">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <h1 class="display-3">Bienvenue alchimiste!</h1>
                    <p class="lead">Vous pouvez desormais profiter de notre application.</p>
                    <p class="lead">
                      <a class="btn btn-primary btn-lg" href="#" role="button">Se connecter</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<%
    Format.footer(out);
%>
