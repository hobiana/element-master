<%-- 
    Document   : menu
    Created on : 24 janv. 2017, 22:59:18
    Author     : ITU
--%>

<%@page import="Utilitaires.Format"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Format.header(out, "Accueil");
    Format.templateNav(out,request, response);
%>


<div class="container-fluid " id="main">
    <div class="card-deck">
        <div class="card" style="width: 20rem;">
        <img class="card-img-top" src="https://elementmaster.herokuapp.com/images/e.jpg" alt="Card image cap">
        <div class="card-block">
            <h4 class="card-title">Mode : Libre</h4>
            <p class="card-text">Trouvez tous les éléments possibles dans un temps illimité.</p>
            <a href="#" class="btn btn-primary">Jouer !</a>
        </div>
    </div><div class="card" style="width: 20rem;">
        <img class="card-img-top" src="https://elementmaster.herokuapp.com/images/e.jpg" alt="Card image cap">
        <div class="card-block">
            <h4 class="card-title">Mode : Défis</h4>
            <p class="card-text">Défier les records de vos amis en les battant avec ce mode de jeu.</p>
            <a href="#" class="btn btn-primary">Jouer !</a>
        </div>
    </div><div class="card" style="width: 20rem;">
        <img class="card-img-top" src="https://elementmaster.herokuapp.com/images/e.jpg" alt="Card image cap">
        <div class="card-block">
            <h4 class="card-title">Classement</h4>
            <p class="card-text">Regarder vos classements et même ceux de vos amis.</p>
            <a href="#" class="btn btn-primary">Voir !</a>
        </div>
    </div>
        </div>
</div>
</div>

<%
    Format.footer(out);
%>
