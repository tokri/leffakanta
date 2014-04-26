<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>
<%@include file="includeAccountUpdates.jsp" %>

<h2>Manage Accounts</h2>
<c:if test="${searchValue == null}"><form id="search" action="searchaccounts" method="POST">
    <input id="searchValue" name="searchValue" type="text" value="" placeholder="Search..." size="35"/></form><p />
</c:if><c:if test="${searchValue != null}">
    <div id="note"><p/>Search results for '${searchValue}':<p /><a class="menu" href="accounts?page=1">(Show All)</a></div><p />
</c:if>
    
<c:if test="${not empty userList}">
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
    <c:if test="${pageCount>1}">
        <p class="left">Page ${page} of ${pageCount}</p>
        <p class="right">
            <c:if test="${page > 1}"><a class="menu" href="accounts?page=${page - 1}">← Previous</a></c:if>
            <c:if test="${page <= 1}"><span class="inactive">← Previous</span></c:if>&nbsp;&nbsp;        
            <c:if test="${page < pageCount}"><a class="menu" href="accounts?page=${page + 1}">Next →</a></c:if>
            <c:if test="${page >= pageCount}"><span class="inactive">Next →</span></c:if>
        </p>
    </c:if><br/><br/>
    <p class="info"><c:if test="${searchValue == null}">Total of <b>${userCount}</b> user<c:if test="${userCount > 1}">s.</c:if></c:if>
    <c:if test="${searchValue != null}">Found <b>${userCount}</b> user<c:if test="${userCount > 1}">s.</c:if></c:if></p>
</c:if>
 <c:if test="${empty userList}">
    0 users found.
 </c:if>
   
<jsp:include page="includeFooter.jsp" />