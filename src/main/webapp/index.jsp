<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>

<html>
<head>
    <title>Twitter Redis</title>
    <!-- Includes des js -->
    <%@include file="WEB-INF/includes/incluJs.jspf" %>

    <!-- Includes des fichiers CSS -->
    <%@include file="WEB-INF/includes/incluStyles.jspf" %>

</head>
<body onload="populateListTweet()">
<div class="page-header">
    <h2>Twittor</h2>
</div>

<div id="menu" style="position:fixed;border:1px #FFFFFF solid;">Menu</div>

<%--contenu--%>
<div class="container">
    <div id="postTweet">
        <form action="./tweeting" class="form" role="form" method="POST">
            <textarea id="tweetMsg" class="col-xs-12" placeholder="Tweet your thought" name="tweetMsg"></textarea>
            <input type="submit" class="btn btn-info pull-right" value="Poster">
        </form>
    </div>

    <br />

    <div id="listTweet" class="container-fluid">
        <div class="progress">
            Pas de tweet à afficher
        </div>
    </div>
</div>

</body>
</html>
