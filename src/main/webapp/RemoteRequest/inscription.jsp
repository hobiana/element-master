<%-- 
    Document   : inscription
    Created on : 10 févr. 2017, 12:51:03
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
    JSONObject json = new JSONObject();
    try
    {
        String token = f.inscription_authentification(nom,pseudo,mail, mdp);
        
        json.put("success", true);
        json.put("token", token);
        
    }
    catch(Exception e)
    {
        json.put("success", false);
        json.put("error-code", e.getMessage());
    }
    out.print(json);
    
    

%>
