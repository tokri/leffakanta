<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Edit Account</h2>
<div id="account-data">
    <form:form action="editaccount" method="POST" commandName='user'>
            <form:input type="hidden" path="userId"/>
            <table id="edit-account-table">
                <tr><td>Username:</td><td><form:input path="username" size="20"/>&nbsp;&nbsp;
                <form:errors path="username" cssClass="error" /></td></tr>
                <tr><td>Admin:</td><td><c:if test="${disableAdmin == true}">True<form:input type="hidden" path="isAdmin"/></c:if>
                <c:if test="${disableAdmin == null}"><form:checkbox path="isAdmin" /></c:if></td></tr>
                <tr><td>&nbsp;<br/></td><td></td></tr>
                <tr><td>New password:</td><td><form:input type="password" path="passwordNew" size="20"/>&nbsp;&nbsp;
                <form:errors path="passwordNew" cssClass="error" /></td></tr>
                <tr><td>Confirm new password:</td><td><form:input type="password" path="passwordNewConfirm" size="20"/>&nbsp;&nbsp;
                <form:errors path="passwordChangeValid" cssClass="error" /></td></tr>
            </table><br />
            <input type="submit" value='Update'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />
