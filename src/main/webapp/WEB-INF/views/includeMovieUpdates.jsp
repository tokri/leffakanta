<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${removedMovie!=null}">
    <div id="note">Movie <b>${removedMovie}</b> removed from collection!</div> 
</c:if>