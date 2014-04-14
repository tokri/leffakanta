<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>

    <h2>A new account successfully registered!</h2>

<form action="newAccountConfirmed" method="post">
    <input type="submit" name="action" value="Continue" />
</form>
    
<jsp:include page="includeFooter.jsp" />