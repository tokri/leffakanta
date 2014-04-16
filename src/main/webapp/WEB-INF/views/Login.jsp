<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>

<div class="menu_divider"><p />
    <a class="menu" href="register">Register a New Account</a>
</div><p />

<table id="logout_table"><tr><td>
    <c:if test="${logout==true}">
        <div id="logout"><p />Succesfully logged out!</div>
    </c:if>
    <c:if test="${sessionExpired==true}">
        <div id="expired"><p />You session has been expired, please login!</div>
    </c:if> 
</td></tr></table>    

<h2>Login</h2>

<div id="login" class="loginData">
    <c:if test="${loginFail==true}"><div class="error">Invalid username and/or password, please try again!</div><br /></c:if>
    <form name="form" id="form" method="POST" action="login">        
        <table id="login_table">
            <tr><td>Username:</td><td><input type="text" name="username"></td></tr>
            <tr><td>Password:</td><td><input type="password" name="password"></td></tr> 
        </table>        
        <input name='Login' class='submit' type='submit' value='Login'/> <br>        
    </form>
    
    <br><br>HUOM! testitunnukset ovat<br><br>
    k&#228;ytt&#228;j&#228;n oikeuksilla:<br>
    tunnus: <b>ohdake</b><br> salasana: <b>test</b><br><br>
    järjestelmänvalvojan oikeuksilla:<br>
    tunnus: <b>mikkelinmies</b><br> salasana: <b>test</b>
</div>
<jsp:include page="includeFooter.jsp" />