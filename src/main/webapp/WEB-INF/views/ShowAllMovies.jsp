<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Manage All Movies</h2>

<c:if test="${not empty movieList}">
    <c:if test="${searchValue == null}"><form id="search" action="searchmovies" method="POST">
        <input id="searchValue" name="searchValue" type="text" value="" placeholder="Search..." size="35"/></form><p />
    </c:if><c:if test="${searchValue != null}">
        <div id="note"><p/>Search results for '${searchValue}':<p /><a class="menu" href="movies?page=1">(Show All)</a></div><p />
    </c:if>   
    <table id="movies-table">
        <tr>
            <th>Title</th>
            <th>Year</th>
            <th>Rating</th>
            <th>Owners</th>
            <th>Edit</th>
            <th>Delete</th>
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
    <c:if test="${pageCount>1}">
        <p class="left">Page ${page} of ${pageCount}</p>
        <p class="right">
            <c:if test="${page > 1}"><a class="menu" href="movies?page=${page - 1}">← Previous</a></c:if>
            <c:if test="${page <= 1}"><span class="inactive">← Previous</span></c:if>&nbsp;&nbsp;        
            <c:if test="${page < pageCount}"><a class="menu" href="movies?page=${page + 1}">Next →</a></c:if>
            <c:if test="${page >= pageCount}"><span class="inactive">Next →</span></c:if>
        </p>
    </c:if><br/><br/>
    <p class="info"><c:if test="${searchValue == null}">Total of <b>${movieCount}</b> movie<c:if test="${movieCount > 1}">s.</c:if></c:if>
    <c:if test="${searchValue != null}">Found <b>${movieCount}</b> movie<c:if test="${movieCount > 1}">s.</c:if></c:if></p>
</c:if>

<c:if test="${empty movieList}">
    <p><c:if test="${searchValue == null}"><b>No movies in the collection :(</b></c:if>
       <c:if test="${searchValue != null}">0 movies found.</c:if></p>
</c:if>
    
<jsp:include page="includeFooter.jsp" />