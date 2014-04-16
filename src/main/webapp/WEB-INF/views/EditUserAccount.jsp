<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Edit Account</h2>
<div id="add" class="AccountData">
    <form:form action="editaccount" method="POST" commandName='user'>
            <form:input type="hidden" path="user_id"/>
            <table id="edit_account_table">
                <tr><td>Username:</td><td><form:input path="username" size="20"/>&nbsp;&nbsp;
                <form:errors path="username" cssClass="error" /></td></tr>
                <tr><td>Admin:</td><td><c:if test="${disable_admin == true}">True<form:input type="hidden" path="is_admin"/></c:if>
                <c:if test="${disable_admin == null}"><form:checkbox path="is_admin" /></c:if></td></tr>
                <tr><td>&nbsp;<br/></td><td></td></tr>
                <tr><td>New password:</td><td><form:input type="password" path="password_new" size="20"/>&nbsp;&nbsp;
                <form:errors path="password_new" cssClass="error" /></td></tr>
                <tr><td>Confirm new password:</td><td><form:input type="password" path="password_new_confirm" size="20"/>&nbsp;&nbsp;
                <form:errors path="password_change_valid" cssClass="error" /></td></tr>
            </table><br />
            <input type="submit" value='Update'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />
