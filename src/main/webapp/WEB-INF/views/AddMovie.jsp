<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Add Movie</h2>
<div id="movie-data">
    <form:form action="addmovie" method="POST" commandName='movie'>
        <table id="add-movie-table">
            <tr><td>Title:</td><td><form:input path="movieTitle" size="70" htmlEscape="false"/>&nbsp;&nbsp;
            <form:errors path="movieTitle" cssClass="error" /></td></tr>
            <tr><td>Year:</td><td><form:input path="year" size="5"/>&nbsp;&nbsp;
            <form:errors path="year" cssClass="error" /></td></tr>
            <tr><td>Format:</td><td><form:select path="formatType" items="${formatTypeList}" />
            <form:errors path="formatType" cssClass="error" /></td></tr>
        </table><br />
        <input type="submit" value='Add Movie'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />