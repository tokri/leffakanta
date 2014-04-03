<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Movies</h2>
<c:if test="${not empty movieList}">
<table>
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Rating</th>
        <th>Format</th>
        <th>Availability</th>
    </tr>    
    <c:forEach items="${movieList}" var="movie">
    <tr>
        <td><a href="movie?id=${movie.movie_id}">${movie.movie_title}</a></td>
        <td>${movie.year}</td>
        <td>${movie.rating}</td>
        <td>${movie.formatType}</td>
        <td>${movie.availability}</td>        
    </tr>
    </c:forEach>
</table>
<br>Total of ${movieCount} movie<c:if test="${movieCount > 1}">s</c:if>
</c:if>

<c:if test="${empty movieList}">
    <p><b>No movies in the collection :(</b></p>
</c:if>
    
<jsp:include page="includeFooter.jsp" />