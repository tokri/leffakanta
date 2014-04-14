<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>Manage Accounts</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Edit</th>
        <th>Remove</th>
    </tr>    
    <c:forEach items="${userList}" var="user">
    <tr>
        <td>${user.username}</td>
        <td><c:if test="${user.is_admin == true}">Admin</c:if><c:if test="${user.is_admin == false}">Normal</c:if></td>
        <td><a href="editaccount?id=${user.user_id}"><img src="${pageContext.request.contextPath}/resources/img/edit-icon.png" alt="edit" width="20" height="20"></a></td>
        <td><c:if test="${ownId != user.user_id}"><a href="deleteaccount?id=${user.user_id}"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt="delete" width="20" height="20"></a></c:if>
            <c:if test="${ownId == user.user_id}"><img src="${pageContext.request.contextPath}/resources/img/delete-icon.png" alt="delete" width="20" height="20"  style="opacity:0.3;filter:alpha(opacity=30);"></c:if></td>
    </tr>
    </c:forEach>
</table>
<br>Total of ${userCount} user<c:if test="${userCount > 1}">s</c:if>
    
<jsp:include page="includeFooter.jsp" />