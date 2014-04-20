<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<div class="menu_divider"><p />
    <a class="menu" href="login">Back to login</a>
</div><p />

<h2>Register a new account</h2>
<div id="account-data">
    <form:form action="register" method="POST" commandName='user'>
        <table id="register-table">
            <tr><td>Username:</td><td><form:input path="username" size="20"/>&nbsp;&nbsp;
            <form:errors path="username" cssClass="error" /></td></tr>
            <tr><td>Password:</td><td><form:input type="password" path="passwordNew" size="20"/>&nbsp;&nbsp;
            <form:errors path="passwordNew" cssClass="error" /></td></tr>
            <tr><td>Confirm password</td><td><form:input type="password" path="passwordNewConfirm" size="20"/>&nbsp;&nbsp;
            <form:errors path="passwordChangeValid" cssClass="error" /></td></tr>
        </table><br />        
        <input type="submit" value='Send'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />