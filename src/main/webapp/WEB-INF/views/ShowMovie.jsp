<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("newLine", "\r\n"); %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<img id="movie-poster" src="<c:if test="${not empty movie.posterUrl}">${movie.posterUrl}</c:if>
<c:if test="${empty movie.posterUrl}">${pageContext.request.contextPath}/resources/img/default-poster.jpg</c:if>">

<table id="text-bg"><tr><td>
<div class="movie-title">
<h1>${movie.movieTitle} (${movie.year})&nbsp;&nbsp;
<a href="editmovie?id=${movie.movieId}"><img src="${pageContext.request.contextPath}/resources/img/edit-icon-white.png" alt="edit" width="20" height="20"></a>
</h1>
</div>
<div class="movie-genres">
    <c:forEach items="${movie.genres}" var="genre" varStatus="loop">
        ${genre.genreName} <c:if test="${!loop.last}"> | </c:if>
    </c:forEach>
</div><p />
<div class="movie-rating">
    Rating: ${movie.rating}
</div><p />
<div class="movie-plot">
    Plot:<br>${fn:replace(movie.plotText, newLine, "<br />")}
</div><p />
</td></tr></table>
<p />
<c:if test="${fn:length(movie.directors) > 0 || fn:length(movie.writers) > 0}">
    <div class="movie-crew">
        <table id="crew-table"><tr><td>
            <c:if test="${not empty movie.directors}"><b>Director</b><c:if test="${fn:length(movie.directors) > 1}">s</c:if>:</b></c:if>
            </td><td>
            <c:if test="${not empty movie.writers}"><b>Writer<c:if test="${fn:length(movie.writers) > 1}">s</c:if>:</b></c:if>
            </td></tr><tr><td>
        <c:forEach items="${movie.directors}" var="director" varStatus="loop">
            <a href="person?id=${director.personId}">${director.personName}</a><c:if test="${!loop.last}">,&nbsp;&nbsp;&nbsp;</c:if>
        </c:forEach></td><td>
        <c:forEach items="${movie.writers}" var="writer" varStatus="loop">
            <a href="person?id=${writer.personId}">${writer.personName}</a><c:if test="${!loop.last}">,&nbsp;&nbsp;&nbsp;</c:if>
        </c:forEach></td></tr></table>
    </div><p />
</c:if>
    <c:forEach items="${movie.cast}" var="cast">
        <table id="cast-table"><tr><td><a href="person?id=${cast.personId}"><img id="actor-img" src="
            <c:if test="${not empty cast.imageUrl}">${cast.imageUrl}</c:if>
            <c:if test="${empty cast.imageUrl}">${pageContext.request.contextPath}/resources/img/default-person.jpg</c:if>"></a></td>
            <td><a href="person?id=${cast.personId}">${cast.personName}</a><br> as ${cast.characterName}</td></tr></table>
    </c:forEach>

<img id="movie-bg" src="<c:if test="${not empty movie.backgroundUrl}">${movie.backgroundUrl}</c:if>
<c:if test="${empty movie.backgroundUrl}">${pageContext.request.contextPath}/resources/img/default-bg.jpg</c:if>">

<jsp:include page="includeFooter.jsp" />