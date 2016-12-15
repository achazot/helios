<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
		<title>Helios</title>
		<meta name="description" content="Index">
		<meta name="author" content="dibi & kcs">
		<link href = "css/styles.css" rel = "stylesheet">
		
	</head>
	
	<body class="index-body">
		<section class="index-section">
			<a href="/helios"><img src="img/helios_logo.svg" class="index-logo"/></a>
			
			<div class="index-form">
				<h2>Authentification</h2>
				<form method="post" action="GlobalController">
					<input type="text" name="login" value ="bertho"/>
					<input type="password" name="password" value ="azerty"/>
					<button type="submit" name="userops" value="connect">Connexion</button>
				</form>
				<c:if test="${trials > 0}">
					<p> Tentatives de connexion: ${trials} </p>
				</c:if>				    
			</div>


		</section>
	</body>
</html>