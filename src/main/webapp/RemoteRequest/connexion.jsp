<%-- 
    Document   : connexion
    Created on : 3 févr. 2017, 13:34:50
    Author     : ITU
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="fonction.Fonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    String mail = request.getParameter("mail");
    String mdp = request.getParameter("pwd");
    JSONObject json = new JSONObject();
    Fonction f= new Fonction();
    try
    {
        String token = f.authentification(mail,mdp);
        request.getSession().setAttribute("utilisateur", token);
        
        json.put("success", true);
        json.put("token", request.getSession().getAttribute("utilisateur"));
        
    }
    catch(Exception e)
    {
        json.put("success", true);
        json.put("error-code", e.getMessage());
    }
    out.print(json);
    
%>
