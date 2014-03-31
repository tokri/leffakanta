<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includeHeader.jsp" />

    <img src="http://${movie.poster_url}" alt="movieposter"> 
    <h1>${movie.movie_title} (${movie.year})</h1>
    Rating: ${movie.rating} <p>
    <table width = 50%><tr><td>Plot:<br>${movie.plot_text}</td></tr></table><p>
    <img src="http://${movie.background_url}" alt="background image"> 
        
<jsp:include page="includeFooter.jsp" />