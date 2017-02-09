<%-- 
    Document   : checkPseudo
    Created on : 9 févr. 2017, 01:11:26
    Author     : ITU
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="fonction.Fonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    String pseudo = request.getParameter("pseudo");
    
    JSONObject json = new JSONObject();
    Fonction f= new Fonction();
    try
    {
        f.checkPseudo(pseudo);
        json.put("valide", true);
        
    }
    catch(Exception e)
    {
        json.put("valide", false);
        json.put("error-code", e.getMessage());
    }
    out.print(json);
    
%>
