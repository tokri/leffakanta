<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Edit Person</h2>
<div id=person-data">
    <form:form action="editperson" method="POST" commandName='person'>
        <form:input type="hidden" path="personId"/>
        <table id="edit-person-table">
            <tr><td>Name:</td><td><form:input path="personName" size="70" htmlEscape="false"/>&nbsp;&nbsp;
            <form:errors path="personName" cssClass="error" /></td></tr>
            <tr><td>Date of Birth:</td><td><form:input path="dateOfBirth" size="10"/>&nbsp;&nbsp;
            <form:errors path="dateOfBirth" cssClass="error" /></td></tr>
            <tr><td>Image URL:</td><td><form:input path="imageUrl" size="70" />&nbsp;&nbsp;
            <form:errors path="imageUrl" cssClass="error" /></td></tr>
        </table><br />
        <input type="submit" value='Update'><br />
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />