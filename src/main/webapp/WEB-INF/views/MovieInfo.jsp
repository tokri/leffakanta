<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<div class="movie_poster">
    <img src="http://${movie.poster_url}" alt="movieposter"> 
</div>    
<div class="movie_title">
    <h1>${movie.movie_title} (${movie.year})</h1>
</div>
<div class="movie_rating">
    Rating: ${movie.rating}
</div><p />
<div class="movie_plot">
    <table width = 50%><tr><td>Plot:<br>${movie.plot_text}</td></tr></table>
</div><p />
<div class="movie_bg_image">
    <img src="http://${movie.background_url}" alt="background image"> 
</div>        
<jsp:include page="includeFooter.jsp" />