<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${deletedUser!=null}">
    <div id="note"><b>Account ${deletedUser}</b> deleted!</div> 
</c:if>     
<c:if test="${accountUpdated==false}">
    <div id="note">Nothing updated!</div>
</c:if>     
<c:if test="${accountUpdated==true}">
    <div id="note"><br />Account <b>${user.username}</b> updated:<br />
    <ul id="bullets">
    <c:if test="${newUsername!=null}">
        <li>Username changed!</li>
    </c:if> 
    <c:if test="${newPassword==true}">
        <li>Password changed!</li>
    </c:if>
    <c:if test="${newRights!=null}">
        <li>Usage rights changed to: <b><i>${newRights}</i></b></li>
    </c:if>    
    </ul></div>
</c:if>