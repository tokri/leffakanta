<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("newLine", "\r\n"); %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<img id="person_img" src="<c:if test="${not empty person.image_url}">${person.image_url}</c:if>
<c:if test="${empty person.image_url}">${pageContext.request.contextPath}/resources/img/default-person.jpg</c:if>">

<table id="person_bg"><tr><td>
<div class="person_name">
<h1>${person.person_name}&nbsp;&nbsp;
<a href="editperson?id=${person.person_id}"><img src="${pageContext.request.contextPath}/resources/img/edit-icon.png" alt="edit" width="20" height="20"></a>
</h1></div>

<div class="roles">
    <c:if test="${not empty roles.directing}">
        <h2>Directing</h2>
        <table id="roles_table">
            <c:forEach items="${roles.directing}" var="role" varStatus="loop">
                <tr><td>${role.year}</td><td><a href="movie?id=${role.movie_id}">${role.movie_title}</a></td></tr>
            </c:forEach>
        </table>
    </c:if>        
    <c:if test="${not empty roles.writing}">
        <h2>Writing</h2>
        <table id="roles_table">
            <c:forEach items="${roles.writing}" var="role" varStatus="loop">
                <tr><td>${role.year}</td><td><a href="movie?id=${role.movie_id}">${role.movie_title}</a></td></tr>
            </c:forEach>
        </table>
    </c:if>        
    <c:if test="${not empty roles.acting}">
        <h2>Acting</h2>
        <table id="roles_table">
            <c:forEach items="${roles.acting}" var="role" varStatus="loop">
                <tr><td>${role.year}</td><td><a href="movie?id=${role.movie_id}">${role.movie_title}</a></td><td>as ${role.character_name}</td></tr>
            </c:forEach>
        </table>
    </c:if>        
</div><p />
</td></tr></table>

<jsp:include page="includeFooter.jsp" />