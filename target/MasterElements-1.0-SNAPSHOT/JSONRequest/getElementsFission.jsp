<%-- 
    Document   : getElementsFission
    Created on : 1 févr. 2017, 13:42:52
    Author     : ITU
--%>

<%@page import="fonction.JSONFonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    String idElement = request.getParameter("idelement");
    JSONFonction.getElementsFissionnesJSON(idElement, response.getWriter());
%>
