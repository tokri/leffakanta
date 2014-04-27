<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<c:if test="${movie.newMovie == true}">
    <div id="note"><p/>Movie added to collection!<p/></div><p />
</c:if>        
<h2><c:if test="${movie.newMovie == true}">Additional Details</c:if><c:if test="${movie.newMovie != true}">Edit Movie</c:if></h2>

<div id="movie-data">
    <table id="edit-movie-separator"><tr><td>
    <form:form action="editmovie" method="POST" commandName='movie'>
        <input type="hidden" name="mode" value="${mode}<c:if test="${mode == null}">1</c:if>"/>
        <form:input type="hidden" path="movieId"/>
        <form:input type="hidden" path="newMovie"/>
        <table id="edit-movie-table">
            <tr><td>Title:</td><td><form:input path="movieTitle" size="70" htmlEscape="false"/>&nbsp;&nbsp;
            <form:errors path="movieTitle" cssClass="error" /></td></tr>
            <tr><td>Year:</td><td><form:input path="year" size="5"/>&nbsp;&nbsp;
            <form:errors path="year" cssClass="error" /></td></tr>
            <tr><td>Runtime:</td><td><form:input path="runtime"  size="5"/>&nbsp;&nbsp;
            <form:errors path="runtime" cssClass="error" /></td></tr>
            <tr><td>Rating:</td><td><form:input path="rating"  size="5"/>&nbsp;&nbsp;
            <form:errors path="rating" cssClass="error" /></td></tr>
            <tr><td>Plot:</td><td><form:textarea path="plotText" rows="5" cols="60" htmlEscape="false"/>&nbsp;&nbsp;
            <form:errors path="plotText" cssClass="error" /></td></tr>
            <tr><td>Poster URL:</td><td><form:input path="posterUrl" size="70" />&nbsp;&nbsp;
            <form:errors path="posterUrl" cssClass="error" /></td></tr>
            <tr><td>Background URL:</td><td><form:input path="backgroundUrl" size="70"/>&nbsp;&nbsp;
            <form:errors path="backgroundUrl" cssClass="error" /></td></tr>
            <tr><td>Trailer URL:</td><td><form:input path="trailerUrl" size="70"/>&nbsp;&nbsp;
            <form:errors path="trailerUrl" cssClass="error" /></td></tr>
            <tr><td></td><td><input type="submit" value='Update'></form:form></td></tr>
        </table>                
        </td><td>
        <h3>Directing</h3>
        <table id="edit-movie-table">
            <c:forEach items="${movie.directors}" var="director" varStatus="loop">
                <tr><td><a href="person?id=${director.personId}">${director.personName}</a></td>
                <td><form action="removeperson" method="post">
                        <input type="hidden" name="personId" value="${director.personId}"/>
                        <input type="hidden" name="productionRole" value="${director.productionRole}"/>
                        <input type="hidden" name="roleId" value="${director.roleId}"/>
                        <input type="hidden" name="movieId" value="${movie.movieId}"/>
                        <input type="submit" name="action" value="Remove" />
                    </form></td></tr>                
            </c:forEach>            
            <tr><td><form action="addperson" method="post">
                        <input name="personName" size="20" htmlEscape="false"/></br>
                        <input type="hidden" name="productionRole" value="Director"/>
                        <input type="hidden" name="movieId" value="${movie.movieId}"/></td>
            <td><input type="submit" name="action" value="Add" /></form></td></tr>
        </table><br />
        <h3>Writing</h3>
        <table id="edit-movie-table">
            <c:forEach items="${movie.writers}" var="writer" varStatus="loop">
                <tr><td><a href="person?id=${writer.personId}">${writer.personName}</a></td>
                <td><form action="removeperson" method="post">
                        <input type="hidden" name="personId" value="${writer.personId}"/>
                        <input type="hidden" name="productionRole" value="${writer.productionRole}"/>
                        <input type="hidden" name="roleId" value="${writer.roleId}"/>
                        <input type="hidden" name="movieId" value="${movie.movieId}"/>
                        <input type="submit" name="action" value="Remove" />
                    </form></td></tr>                
            </c:forEach>
            <tr><td><form action="addperson" method="post">
                        <input name="personName" size="20" htmlEscape="false"/></br>
                        <input type="hidden" name="productionRole" value="Writer"/>
                        <input type="hidden" name="movieId" value="${movie.movieId}"/></td>
            <td><input type="submit" name="action" value="Add" /></form></td></tr>
        </table><br />
        <h3>Acting</h3>
        <table id="edit-movie-table">
            <c:forEach items="${movie.cast}" var="cast">
                <tr><td><a href="person?id=${cast.personId}">${cast.personName}</a> as ${cast.characterName}</td>
                <td><form action="removeperson" method="post">
                        <input type="hidden" name="personId" value="${cast.personId}"/>
                        <input type="hidden" name="productionRole" value="${cast.productionRole}"/>
                        <input type="hidden" name="roleId" value="${cast.roleId}"/>
                        <input type="hidden" name="characterName" value="${cast.characterName}"/>
                        <input type="hidden" name="movieId" value="${movie.movieId}"/>
                        <input type="submit" name="action" value="Remove" />
                    </form></td></tr>                
            </c:forEach>            
            <tr><td><form action="addperson" method="post">
                        Actor: <input name="personName" size="20" htmlEscape="false"/></br>
                        Character: <input name="characterName" size="20" htmlEscape="false"/>
                        <input type="hidden" name="productionRole" value="Actor"/>
                        <input type="hidden" name="movieId" value="${movie.movieId}"/></td>
            <td><input type="submit" name="action" value="Add" /></form></td></tr>
        </table><br />
</td></tr></table></div>        
<jsp:include page="includeFooter.jsp" />