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

<div id="userList">
    Pas d'utilisateurs à afficher
</div>

<div style="position:absolute; bottom:0">
    <div id="notif"></div>

    <div><a href="index">Retour maison</a></div>
</div>

</body>
</html>
