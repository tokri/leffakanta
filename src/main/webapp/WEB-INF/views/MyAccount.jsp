<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>My Account</h2>
<div id="login" class="loginData">
    <form name="form" id="form" method="POST" action="login">
        Username: <input type="text" name="username"><br>
        Current password: <input type="password" name="password"><br>
        <br>        
        New password: <input type="password" name="password"><br>
        Confirm new password: <input type="password" name="password_confirm"><br>
        <input name='Update' class='submit' type='submit' value='Update'/> <br>
    </form>
</div>        
<jsp:include page="includeFooter.jsp" />