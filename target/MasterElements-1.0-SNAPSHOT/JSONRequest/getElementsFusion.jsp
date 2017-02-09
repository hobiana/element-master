<%-- 
    Document   : getElementsFusion
    Created on : 1 févr. 2017, 09:26:06
    Author     : ITU
--%>

<%@page import="fonction.JSONFonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    String idElements = request.getParameter("idelements");
    String idToken = request.getParameter("idtoken");
    JSONFonction.getElementsFusionnesJSON(idElements, idToken, response.getWriter());
%>
