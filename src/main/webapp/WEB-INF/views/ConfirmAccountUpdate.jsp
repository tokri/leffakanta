<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<c:if test="${noUpdates==null}">
    <h2>Account successfully updated!</h2>
</c:if>     
<c:if test="${noUpdates==true}">
    <h2>Nothing updated!</h2>
</c:if>     

<c:if test="${newUsername!=null}">
    Username changed to <b><i>${newUsername}</i></b>!<br /><br />
</c:if> 
<c:if test="${newPassword==true}">
    Password changed successfully!
</c:if> 

<jsp:include page="includeFooter.jsp" />