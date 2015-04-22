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
<body onload="populateListTweet(), getStats()">

<div class="customHeader">
    <div class="page-header">
        <h2>Twittor</h2>

        <div class="pull-right menuHeader">
            <div class="glyphicon glyphicon-user"><%=session.getAttribute("login")%>
                <a class="glyphicon glyphicon-off" href="logout"><span class="logout">Déconnexion</span></a></div>

            <br>

            <a href="gotoUserList">Liste des utilisateurs</a>
        </div>
    </div>
</div>

<div id="menu">
    <ul><div id="titleMenu">Menu</div>
        <li class="itemMenu">
            <a href="#" onclick="populateListTweet()">Liste tweets</a>
        </li>
        <li class="form itemMenu" role="form">
            <input id="searchInTweet" type="search" class="form-control" placeholder="Search"
                   onkeyup="populateListTweet()">
        </li>
        <li class="itemMenu">
            <a href="#" onclick="displayFollower()">Followers</a>
        </li>
        <li class="itemMenu">
            <a href="#" onclick="displayFollowing()">Following</a>
        </li>
    </ul>
</div>

<%--contenu--%>
<div class="container">
    <div id="postTweet">
        <form action="./tweeting" class="form" role="form" method="POST">
            <textarea id="tweetMsg" class="col-xs-12" placeholder="Tweet your thought" name="tweetMsg"
                    maxlength="140"></textarea>
            <input type="submit" class="btn btn-info pull-right" value="Poster">
        </form>
    </div>

    <div id="myStat">
        Tweets persos : <span id="nbTweets"></span>,
        Tweets persos + personnes followed: <span id="nbTweetsDisplayed"></span>,
        Nombre de followers : <span id="nbFollower"></span>,
        Nombre de personnes followed : <span id="nbFollowing"></span>
    </div>

    <div id="listTweet" class="container-fluid">
        <div class="progress">
            Pas de tweet à afficher
        </div>
    </div>
</div>

</body>
</html>
