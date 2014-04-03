<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<img id="movie_poster" src="http://${movie.poster_url}">

<div class="movie_title">
<h1>${movie.movie_title} (${movie.year})</h1>
</div>
<div class="movie_rating">
    Rating: ${movie.rating}
</div><p />
<div class="movie_plot">
    Plot:<br>${movie.plot_text}
</div><p />
<img id="movie_bg" src="http://${movie.background_url}"><jsp:include page="includeFooter.jsp" />