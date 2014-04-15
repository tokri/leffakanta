<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="includeHeader.jsp" %>
<%@include file="includeMenu.jsp" %>

<h2>${head} Movie</h2>
<div id="add" class="MovieData">
    <form:form action="${fn:toLowerCase(head)}movie" method="POST" commandName='movie'>
        <c:if test = "${head == 'Edit'}">
            <form:input type="hidden" path="movie_id"/>
        </c:if>
            Title: <form:input path="movie_title" size="50" htmlEscape="false"/>
                <font color="red"> <form:errors path="movie_title"></form:errors></font><br/>
            Year: <form:input path="year" size="5"/>
                <font color="red"><form:errors path="year"></form:errors></font><br/>
            Rating: <form:input path="rating"  size="5"/>
                <font color="red"><form:errors path="rating" ></form:errors></font><br/>
            Plot: <form:textarea path="plot_text" rows="5" cols="60" htmlEscape="false"/>
                <font color="red"><form:errors path="plot_text" ></form:errors></font><br/>
            Poster URL: <form:input path="poster_url" size="70" />
                <font color="red"><form:errors path="poster_url" ></form:errors></font><br/>
            Background URL: <form:input path="background_url" size="70"/>
                <font color="red"><form:errors path="background_url" ></form:errors></font><br/>
            Trailer URL: <form:input path="trailer_url" size="70"/>
                <font color="red"><form:errors path="trailer_url" ></form:errors></font><br/>
            <input type="submit" value='Send'>
    </form:form>  
</div>        
<jsp:include page="includeFooter.jsp" />