<%-- 
    Document   : inscription
    Created on : 9 févr. 2017, 00:53:06
    Author     : ITU
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="fonction.Fonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    
    String nom = request.getParameter("nom")+" "+request.getParameter("prenoms");
    String pseudo = request.getParameter("pseudo");
    String mail = request.getParameter("mail");
    String mdp = request.getParameter("pwd"); 

    
    Fonction f = new Fonction();
    String token = f.inscription_authentification(nom,pseudo,mail, mdp);
    
    if(token != null)
    {
        request.getSession().setAttribute("utilisateur", token);
        response.sendRedirect("libre.jsp");
    }
    
    

%>
