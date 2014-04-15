<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("newLine", "\r\n"); %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<img id="movie_poster" src="<c:if test="${not empty movie.poster_url}">${movie.poster_url}</c:if>
<c:if test="${empty movie.poster_url}">${pageContext.request.contextPath}/resources/img/default-poster.jpg</c:if>">

<table id="text_bg"><tr><td>
<div class="movie_title">
<h1>${movie.movie_title} (${movie.year})</h1>
</div>
<div class="movie_genres">
    <c:forEach items="${movie.genres}" var="genre" varStatus="loop">
        ${genre.genre_name} <c:if test="${!loop.last}"> | </c:if>
    </c:forEach>
</div><p />
<div class="movie_rating">
    Rating: ${movie.rating}
</div><p />
<div class="movie_plot">
    Plot:<br>${fn:replace(movie.plot_text, newLine, "<br />")}
</div><p />
</td></tr></table>
<p />
<c:if test="${not empty movie.directors || not empty movie.writers}">
    <div class="movie_crew">
        <table id="crew_table"><tr><td>
            <c:if test="${not empty movie.directors}"><b>Director</b><c:if test="${fn:length(movie.directors) > 1}">s</c:if>:</b></c:if>
            </td><td>
            <c:if test="${not empty movie.writers}"><b>Writer<c:if test="${fn:length(movie.writers) > 1}">s</c:if>:</b></c:if>
            </td></tr><tr><td>
        <c:forEach items="${movie.directors}" var="director" varStatus="loop">
            ${director.person_name} <c:if test="${!loop.last}">, </c:if>
        </c:forEach></td><td>
        <c:forEach items="${movie.writers}" var="writer" varStatus="loop">
            ${writer.person_name} <c:if test="${!loop.last}">, </c:if>
        </c:forEach></td></tr></table>
    </div><p />
</c:if>
    <c:forEach items="${movie.cast}" var="cast">
        <table id="cast_table"><tr><td><img id="actor_img" src="${cast.image_url}"></td>
            <td><b>${cast.person_name}</b><br> as ${cast.character_name}</td></tr></table>
    </c:forEach>

<img id="movie_bg" src="<c:if test="${not empty movie.background_url}">${movie.background_url}</c:if>
<c:if test="${empty movie.background_url}">${pageContext.request.contextPath}/resources/img/default-bg.jpg</c:if>">

<jsp:include page="includeFooter.jsp" />