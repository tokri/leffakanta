<% Boolean desktop = (Boolean)session.getAttribute("desktop"); %>
<c:choose>
    <c:when test="${desktop == true}"><!DOCTYPE html></c:when>
    <c:otherwise><!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"></c:otherwise>
</c:choose>   
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leffakanta</title>
        <c:choose>
            <c:when test="${desktop == true}"><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/desktop.css"></c:when>
            <c:otherwise><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mobile.css"></c:otherwise>
        </c:choose>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">        
    </head>
<body>    
<div class="logo">
    <img width="273" height="98" src="${pageContext.request.contextPath}/resources/img/leffakanta.png" alt="Leffakanta">
</div>