<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>My Account</h2>
<div id="add" class="AccountData">
    <form:form action="account" method="POST" commandName='user'>
            <form:input type="hidden" path="user_id"/>
            Username: <form:input path="username" size="20"/>
                <font color="red"> <form:errors path="username"></form:errors></font><br/>
            Current password: <form:input type="password" path="password_entered" size="20"/>
                <font color="red"> <form:errors path="password_entered"></form:errors></font><br/>
            <br/>
            New password <form:input type="password" path="password_new" size="20"/>
                <font color="red"><form:errors path="password_new"></form:errors></font><br/>
            Confirm new password <form:input type="password" path="password_new_confirm" size="20"/>
                <font color="red"><form:errors path="password_change_valid"></form:errors></font><br/>
            <input type="submit" value='Send'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />