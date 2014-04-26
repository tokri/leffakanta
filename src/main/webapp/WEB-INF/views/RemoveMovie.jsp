<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Remove Movie From Collection</h2>

Are you sure you want to remove movie: <b>${movie.movieTitle} (${movie.year})</b>?<p />
<form action="removemovie" method="post">
    <input type="hidden" name="collection_id" value="${movie.collectionId}"/>
    <input type="submit" name="action" value="Yes" />
    <input type="submit" name="action" value="No" />
</form>

<jsp:include page="includeFooter.jsp" />