<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
	
		<title>La base</title>
		<meta name="description" content="Index">
		<meta name="author" content="dibi & kcs">
	</head>
	
	<body>
		<h2>Authentifie ta mère</h2>
			<form action="Controller">
			 	<input type="text" name="mail" value ="mail"/>
				<input type="text" name="password" value ="password"/>
		        <button type="submit" name="userops" value="connect">log in</button>
		        <button type="submit" name="userops" value="disconnect">log out</button>
		    </form>
	</body>
</html>