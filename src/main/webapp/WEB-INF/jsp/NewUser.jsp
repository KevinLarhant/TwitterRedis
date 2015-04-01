<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Création nouvel utilisateur</title>
	
	 <!-- Includes des js -->
    <%@include file="../includes/incluJs.jspf" %>
    <!-- Includes des fichiers CSS -->
    <%@include file="../includes/incluStyles.jspf" %>
</head>
<body>

	<div class="customForm">
		<h1>Création User</h1>

		<form action="./newUser" class="form" role="form" method="post">
			<fieldset>
				<div class="form-group">
					<label for="login">Votre nom d'utilisateur / login</label>
					 <input	class="form-control" id="login" name="login" placeholder="Enter Login" required>
				</div>
				
				<div class="form-group">
					<label for="pwd1">Password</label>
					<input type="password" class="form-control" id="pwd1" name="pwd1" placeholder="Password" required>
				</div>
				
				<div class="form-group">
					<label for="pwd2">Répétez Password</label>
					 <input type="password" class="form-control" id="pwd2" name="pwd2"
					 	onkeyup="pwdCheck()" placeholder="Repeat Password" required>
					 	<span id="identicalPwd" class="identicalPwd text-error"></span>
				</div>
				<button type="submit" id="submitNewUser" class="btn btn-primary disabled">S'enregistrer</button>
			</fieldset>

		</form>
	</div>
</body>
</html>