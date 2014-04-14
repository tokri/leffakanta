<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Delete Account</h2>

Are you sure you want to delete account: <b>${user.username}</b>?<p />
<form action="deleteaccount" method="post">
    <input type="hidden" name="user_id" value="${user.user_id}"/>
    <input type="submit" name="action" value="Yes" />
    <input type="submit" name="action" value="No" />
</form>

<jsp:include page="includeFooter.jsp" />