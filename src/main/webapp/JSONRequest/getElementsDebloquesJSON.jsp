<%-- 
    Document   : getElementsDebloques
    Created on : 1 févr. 2017, 09:03:13
    Author     : ITU
--%>

<%@page import="fonction.JSONFonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    String idToken = request.getParameter("idtoken");
    JSONFonction.getElementsDebloquesJSON(idToken, response.getWriter());
%>
