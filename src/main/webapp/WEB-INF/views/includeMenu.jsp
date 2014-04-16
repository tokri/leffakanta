<% Boolean admin = (Boolean)session.getAttribute("admin"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu_divider"><p />
    <a class="menu" href="collection">My Collection</a> | 
    <a class="menu" href="addmovie">Add Movie</a> | 
<c:if test="${admin == true}"><a class="menu" href="movies">Manage Movies</a> | </c:if>
<c:if test="${admin == null}"><a class="menu" href="account">My Account</a> | </c:if>    
<c:if test="${admin == true}"><a class="menu" href="accounts">Manage Accounts</a> | </c:if>
    <a class="menu" href="logout">Sign Out</a>
</div><p />
