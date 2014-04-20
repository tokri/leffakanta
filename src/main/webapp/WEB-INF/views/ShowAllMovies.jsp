<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Manage Movies</h2>
<c:if test="${not empty movieList}">
<table id="movies-table">
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Rating</th>
        <th>Owners</th>
        <th>Edit</th>
        <th>Remove</th>
    </tr>    
    <c:forEach items="${movieList}" var="movie">
    <tr>
        <td><a href="movie?id=${movie.movieId}">${movie.movieTitle}</a></td>
        <td>${movie.year}</td>
        <td>${movie.rating}</td>
        <td>${movie.ownerCount}</td>
        <td><a href="editmovie?id=${movie.movieId}"><img src="${pageContext.request.contextPath}/resources/img/edit-icon.png" alt="edit" width="20" height="20"></a></td>        
        <td><a href="deletemovie?id=${movie.movieId}"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt="delete" width="20" height="20"></a></td>        
    </tr>
    </c:forEach>
</table>
<br>Total of ${movieCount} movie<c:if test="${movieCount > 1}">s</c:if>
</c:if>

<c:if test="${empty movieList}">
    <p><b>No movies in the collection :(</b></p>
</c:if>
    
<jsp:include page="includeFooter.jsp" />