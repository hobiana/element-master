<%-- 
    Document   : upgradeLevel
    Created on : 8 févr. 2017, 18:15:20
    Author     : ITU
--%>

<%@page import="fonction.JSONFonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    String idToken = request.getParameter("idtoken");
    String temps = request.getParameter("time");
    String level = request.getParameter("level");
    JSONFonction.upgradeLevel(idToken,temps,level);
%>
