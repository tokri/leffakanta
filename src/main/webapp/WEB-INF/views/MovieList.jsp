<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="PageHeader.jsp" />
<h2>Movies</h2>
<c:if test="${not empty movieList}">
<table>
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Rating</th>
    </tr>    
    <c:forEach items="${movieList}" var="movie">
        <tr>
            <td><a href="movie?id=${movie.movie_id}">${movie.movie_title}</a></td>
            <td>${movie.year}</td>
            <td>${movie.rating}</td>
        </tr>
    </c:forEach>
</table> 
</c:if>
<c:if test="${empty movieList}">
    <p><b>No movies in the collection :(</b></p>
</c:if>
<jsp:include page="PageFooter.jsp" />