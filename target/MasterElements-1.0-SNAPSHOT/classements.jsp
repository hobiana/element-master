<%-- 
    Document   : classements
    Created on : 24 janv. 2017, 23:52:57
    Author     : ITU
--%>

<%@page import="Utilitaires.Format"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Format.header(out, "Accueil");
    Format.templateNav(out,request, response);
%>


    <div class="container-fluid " id="main">
        <div class="row">
            <div class="col-md-6  offset-md-3">
                <h3 class="bg-faded" >Classements en lignes</h3>
                <table class="table">
                    <thead class="thead-default">
                      <tr>
                        <th>#</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Temps</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>11:00:00</td>
                      </tr>
                      <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>13:00:00</td>
                      </tr>
                      <tr>
                        <th scope="row">3</th>
                        <td>Larry</td>
                        <td>the Bird</td>
                        <td>14:00:00</td>
                      </tr>
                    </tbody>
                </table>
                <button class="btn btn-primary pull-right">Retour</button>
            </div>
        </div>
</div>

<%
    Format.footer(out);
%>
