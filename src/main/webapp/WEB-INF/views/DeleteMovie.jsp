<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Delete Movie</h2>

Are you sure you want to delete movie: <b>${movie.movie_title} (${movie.year})</b>?<p />
<form action="confirmdeletemovie" method="post">
    <input type="hidden" name="movie_id" value="${movie.movie_id}"/>
    <input type="submit" name="action" value="Yes" />
    <input type="submit" name="action" value="No" />
</form>

<jsp:include page="includeFooter.jsp" />