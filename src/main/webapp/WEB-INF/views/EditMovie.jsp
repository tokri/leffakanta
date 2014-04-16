<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>${head}</h2>
<div id="add" class="MovieData">
    <form:form action="editmovie" method="POST" commandName='movie'>
        <form:input type="hidden" path="movie_id"/>
        <form:input type="hidden" path="new_movie"/>
        <table id="edit_movie_table">
            <tr><td>Title:</td><td><form:input path="movie_title" size="70" htmlEscape="false"/>&nbsp;&nbsp;
            <form:errors path="movie_title" cssClass="error" /></td></tr>
            <tr><td>Year:</td><td><form:input path="year" size="5"/>&nbsp;&nbsp;
            <form:errors path="year" cssClass="error" /></td></tr>
            <tr><td>Runtime:</td><td><form:input path="runtime"  size="5"/>&nbsp;&nbsp;
            <form:errors path="runtime" cssClass="error" /></td></tr>
            <tr><td>Rating:</td><td><form:input path="rating"  size="5"/>&nbsp;&nbsp;
            <form:errors path="rating" cssClass="error" /></td></tr>
            <tr><td>Plot:</td><td><form:textarea path="plot_text" rows="5" cols="60" htmlEscape="plot_text"/>&nbsp;&nbsp;
            <form:errors path="plot_text" cssClass="error" /></td></tr>
            <tr><td>Poster URL:</td><td><form:input path="poster_url" size="70" />&nbsp;&nbsp;
            <form:errors path="poster_url" cssClass="error" /></td></tr>
            <tr><td>Background URL:</td><td><form:input path="background_url" size="70"/>&nbsp;&nbsp;
            <form:errors path="background_url" cssClass="error" /></td></tr>
            <tr><td>Trailer URL:</td><td><form:input path="trailer_url" size="70"/>&nbsp;&nbsp;
            <form:errors path="trailer_url" cssClass="error" /></td></tr>
        </table>
        <h3>Directing</h3>
        <table id="edit_movie_table">
            <tr><td><input id="director" size="20" htmlEscape="false"/></td><td><button type="button">Add Director</button></td></tr>
        </table>
        <h3>Writing</h3>
        <table id="edit_movie_table">
            <tr><td><input id="writer" size="20" htmlEscape="false"/></td><td><button type="button">Add Writer</button></td></tr>
        </table>
        <h3>Cast</h3>
        <table id="edit_movie_table">
            <tr><td><input id="actor" size="20" htmlEscape="false"/></td><td><button type="button">Add Actor</button></td></tr>
        </table><br />
        <input type="submit" value='Update'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />