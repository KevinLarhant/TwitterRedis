<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login</title>

    <!-- Includes des js -->
    <%@include file="WEB-INF/includes/incluJs.jspf" %>
    <!-- Includes des fichiers CSS -->
    <%@include file="WEB-INF/includes/incluStyles.jspf" %>

</head>

<body>
    <div class="customForm">
        <h1>Login</h1>

        <form action="./login" class="form" role="form" method="post">
            <fieldset>
            <div class="form-group">
                <label for="login">Login</label>
                <input class="form-control" id="login" name="login" placeholder="Enter Login" required>
            </div>
            <div class="form-group">
                <label for="pwd">Password</label>
                <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Password" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            </fieldset>
            
            <a class="pull-right" href="./newUser">Nouvel utilisateur?</a>
        </form>

    </div>

</body>
</html>
