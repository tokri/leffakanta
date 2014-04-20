<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("newLine", "\r\n"); %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<img id="person-img" src="<c:if test="${not empty person.imageUrl}">${person.imageUrl}</c:if>
<c:if test="${empty person.imageUrl}">${pageContext.request.contextPath}/resources/img/default-person.jpg</c:if>">

<table id="person-bg"><tr><td>
<div class="person-name">
<h1>${person.personName}&nbsp;&nbsp;
<a href="editperson?id=${person.personId}"><img src="${pageContext.request.contextPath}/resources/img/edit-icon.png" alt="edit" width="20" height="20"></a>
</h1></div>

<div class="roles">
    <c:if test="${not empty roles.directing}">
        <h2>Directing</h2>
        <table id="roles-table">
            <c:forEach items="${roles.directing}" var="role" varStatus="loop">
                <tr><td>${role.year}</td><td><a href="movie?id=${role.movieId}">${role.movieTitle}</a></td></tr>
            </c:forEach>
        </table>
    </c:if>        
    <c:if test="${not empty roles.writing}">
        <h2>Writing</h2>
        <table id="roles-table">
            <c:forEach items="${roles.writing}" var="role" varStatus="loop">
                <tr><td>${role.year}</td><td><a href="movie?id=${role.movieId}">${role.movieTitle}</a></td></tr>
            </c:forEach>
        </table>
    </c:if>        
    <c:if test="${not empty roles.acting}">
        <h2>Acting</h2>
        <table id="roles-table">
            <c:forEach items="${roles.acting}" var="role" varStatus="loop">
                <tr><td>${role.year}</td><td><a href="movie?id=${role.movieId}">${role.movieTitle}</a></td><td>as ${role.characterName}</td></tr>
            </c:forEach>
        </table>
    </c:if>        
</div><p />
</td></tr></table>

<jsp:include page="includeFooter.jsp" />