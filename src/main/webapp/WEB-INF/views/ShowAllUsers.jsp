<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>
<%@include file="includeAccountUpdates.jsp" %>

<h2>Manage Accounts&nbsp;<form id="search" action="searchaccounts" method="POST">
<input id="searchValue" name="searchValue" type="text" value="" placeholder="Search..." size="35"/></form></h2><p />

<table id="accounts-table">
    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Edit</th>
        <th>Remove</th>
    </tr>    
    <c:forEach items="${userList}" var="user">
    <tr>
        <td>${user.username}</td>
        <td><c:if test="${user.isAdmin == true}">Admin</c:if><c:if test="${user.isAdmin == false}">Normal</c:if></td>
        <td><a href="editaccount?id=${user.userId}"><img src="${pageContext.request.contextPath}/resources/img/edit-icon.png" alt="edit" width="20" height="20"></a></td>
        <td><c:if test="${ownId != user.userId}"><a href="deleteaccount?id=${user.userId}"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt="delete" width="20" height="20"></a></c:if>
            <c:if test="${ownId == user.userId}"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt="delete" width="20" height="20"  style="opacity:0.3;filter:alpha(opacity=30);"></c:if></td>
    </tr>
    </c:forEach>
</table>
<br>Total of ${userCount} user<c:if test="${userCount > 1}">s</c:if>
    
<jsp:include page="includeFooter.jsp" />