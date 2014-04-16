<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Edit Person</h2>
<div id="add" class="MovieData">
    <form:form action="editperson" method="POST" commandName='person'>
        <form:input type="hidden" path="person_id"/>
        <table id="edit_person_table">
            <tr><td>Name:</td><td><form:input path="person_name" size="70" htmlEscape="false"/>&nbsp;&nbsp;
            <form:errors path="person_name" cssClass="error" /></td></tr>
            <tr><td>Date of Birth:</td><td><form:input path="date_of_birth" size="10"/>&nbsp;&nbsp;
            <form:errors path="date_of_birth" cssClass="error" /></td></tr>
            <tr><td>Image URL:</td><td><form:input path="image_url" size="70" />&nbsp;&nbsp;
            <form:errors path="image_url" cssClass="error" /></td></tr>
        </table><br />
        <input type="submit" value='Update'><br />
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />