<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includeHeader.jsp" />

<h2>Login</h2>

<div id="login" class="loginData">
    <form name="form" id="form" method="POST" action="login">
        <c:if test="${loginFail==true}">
            <div id="loginFailed">Invalid username and/or password, please try again!</div><p>
        </c:if>
        <c:if test="${logout==true}">
            <div id="logout">User has been logged out!</div><p>
        </c:if>
        <c:if test="${sessionExpired==true}">
            <div id="logout">You session has been expired, please login!</div><p>
        </c:if>                
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>        
        <input name='Login' class='submit' type='submit' value='Login'/> <br>        
    </form>
    
    <br><br>HUOM! testitunnukset ovat<br><br>
    k&#228;ytt&#228;j&#228; -oikeuksilla:<br>
    tunnus: <b>ohdake</b><br> salasana: <b>test</b><br><br>
    admin -oikeuksilla:<br>
    tunnus: <b>mikkelinmies</b><br> salasana: <b>test</b>
</div>

<jsp:include page="includeFooter.jsp" />
