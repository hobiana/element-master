<%-- 
    Document   : getDefisJSON
    Created on : 8 févr. 2017, 13:02:30
    Author     : ITU
--%>

<%@page contentType="text/json" pageEncoding="UTF-8"%>
<%@page import="fonction.JSONFonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    String idToken = request.getParameter("idtoken");
    JSONFonction.getElementDefisJSON(idToken, response.getWriter());
%>
