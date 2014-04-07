<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>${head} Movie</h2>
<div id="add" class="MovieData">
    <form name="form" id="form" method="POST" action="${fn:toLowerCase(head)}movie">
        <c:if test = "${head == 'Edit'}">
            <input type="hidden" name="movie_id" value="${movie.movie_id}">
        </c:if>
        Title: <input type="text" name="movie_title"  size="50" value="${movie.movie_title}" required>
        Year:  <input type="number" name="year"  size="5" value="${movie.year}" required><br>
        Rating:  <input type="number" name="rating"  size="5" value="${movie.rating}"><br>
        Plot:  <textarea name="plot_text" rows="5" cols="60">${movie.plot_text}</textarea><br>
        Poster URL: <input type="text" name="poster_url"  size="70" value="${movie.poster_url}"><br>
        Background URL: <input type="text" name="background_url" size="70" value="${movie.background_url}"><br>        
        <input name='${head}Movie' class='submit' type='submit' value='Send'/> <br>
    </form>
</div>        
<jsp:include page="includeFooter.jsp" />