<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
	
		<title>La base</title>
		<meta name="description" content="Index">
		<meta name="author" content="dibi & kcs">
	</head>
	
	<body>
		
		<h2>Authentifie ta mère</h2>
			<form method="post" action="GlobalController">
			 	<input type="text" name="login" value ="bertho"/>
				<input type="password" name="password" value ="azerty"/>
		        <button type="submit" name="userops" value="connect">Connexion</button>
		    </form>
		<p>
			Tentatives de connexion ${trials}
		</p>				    
	</body>
</html>