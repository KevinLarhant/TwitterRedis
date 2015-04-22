<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des utilisateurs</title>

    <!-- Includes des js -->
    <%@include file="../includes/incluJs.jspf" %>

    <!-- Includes des fichiers CSS -->
    <%@include file="../includes/incluStyles.jspf" %>
</head>

<body onload="populateListUsers()" style="position: relative">

<div class="customHeader">
    <div class="page-header">
        <h2>Twittor</h2>

        <div class="pull-right menuHeader" style="top:25px;">
            <div class="glyphicon glyphicon-user"><%=session.getAttribute("login")%>
            <a class="glyphicon glyphicon-off" href="logout"><span class="logout">Déconnexion</span></a>
            </div>
        </div>
    </div>
</div>

<div>Clicker sur <span class="glyphicon glyphicon-plus"></span> pour follow une personne. Ses futurs tweets seront affichés sur votre page d'acceuil</div>

<div id="userList">
    Pas d'utilisateurs à afficher
</div>

<div class="col-xs-12 botUserList">
    <div id="notif"></div>

    <div><a href="index">Retour maison</a></div>
</div>

</body>
</html>
