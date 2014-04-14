<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<div class="menu_divider"><p />
    <a class="menu" href="login">Back to login</a>
</div><p />

<h2>Register a new account</h2>
<div id="add" class="AccountData">
    <form:form action="register" method="POST" commandName='user'>
            Username: <form:input path="username" size="20"/>
                <font color="red"> <form:errors path="username"></form:errors></font><br/>
            Password <form:input type="password" path="password_new" size="20"/>
                <font color="red"><form:errors path="password_new"></form:errors></font><br/>
            Confirm password <form:input type="password" path="password_new_confirm" size="20"/>
                <font color="red"><form:errors path="password_change_valid"></form:errors></font><br/>
            <input type="submit" value='Send'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />