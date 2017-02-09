<%-- 
    Document   : getElementsJSON
    Created on : 1 févr. 2017, 07:47:31
    Author     : ITU
--%>

<%@page contentType="text/json" pageEncoding="UTF-8"%>
<%@page import="fonction.JSONFonction"%>
<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    JSONFonction.getElementsBaseJSON(response.getWriter());
%>
