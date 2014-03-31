<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includeHeader.jsp" />

<h2>Add Movie</h2>
<div id="add" class="MovieData">
    <form name="form" id="form" method="POST" action="login">
        Title: <input type="text" name="title"><br>
        Year:  <input type="text" name="year"><br>
        Rating:  <input type="text" name="year"><br>
        Plot:  <input type="text" name="year"><br>
        Poster URL: <input type="text" name="poster_url"><br>
        Background URL: <input type="text" name="background_url"><br>
        
        <input name='AddMovie' class='submit' type='submit' value='AddMovie'/> <br>
    </form>
</div>
        
<jsp:include page="includeFooter.jsp" />