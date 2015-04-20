<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>

<html>
<head>
    <title>Twitter Redis</title>
    <!-- Includes des js -->
    <%@include file="WEB-INF/includes/incluJs.jspf"%>

    <!-- Includes des fichiers CSS -->
    <%@include file="WEB-INF/includes/incluStyles.jspf"%>

</head>
    <body>
        <h2>Twittor</h2>

        <div id="menu" style="position:fixed;border:1px #FFFFFF solid;">Menu</div>

        <%--contenu--%>
        <div class="container">
        <div id="postTweet">
            <form action="./tweeting" class="form" role="form" method="post">
                <textarea placeholder="Tweet your thought"></textarea>
            </form>
        </div>

        <div id="listTweet">listTweet</div>
        </div>

    </body>
</html>
