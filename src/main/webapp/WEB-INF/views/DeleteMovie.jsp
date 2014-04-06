<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Delete Movie</h2>

Are you sure you want to delete movie: <b>${movie.movie_title} (${movie.year})</b>?<p />
<a href="confirmdeletemovie?id=${movie.movie_id}">Yes</a> <a href="movies">No</a>

<jsp:include page="includeFooter.jsp" />