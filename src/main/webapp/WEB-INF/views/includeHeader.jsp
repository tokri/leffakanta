<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leffakanta</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>
<body>
    
<div class="logo">
    <img width="273" height="98" src="${pageContext.request.contextPath}/resources/img/leffakanta.png" alt="Leffakanta"></img>
</div>

<c:if test="${showMenu == true}">
    <div class="menu_divider"><p />
    <a class="menu" href="movies">All Movies</a> | 
    <a class="menu" href="add">Add Movie</a> | 
    <a class="menu" href="account">Account</a> | 
    <a class="menu" href="logout">Sign Out</a>
    </div><p />
</c:if>