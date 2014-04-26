<% Boolean desktop = (Boolean)session.getAttribute("desktop"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${desktop == true}"><!DOCTYPE html></c:if>
<c:if test="${desktop != true}"><!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"></c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leffakanta</title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/<c:if test="${desktop == true}">desktop</c:if><c:if test="${desktop != true}">mobile</c:if>.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">        
    </head>
<body>    
<div class="logo">
    <img width="273" height="98" src="${pageContext.request.contextPath}/resources/img/leffakanta.png" alt="Leffakanta">
</div>