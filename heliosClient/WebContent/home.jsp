<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
	
		<title>Profil Personnel</title>
		<meta name="description" content="Index">
		<meta name="author" content="dibi & kcs">
	</head>
	
	<body>
	
		<header> 
			Header
			<form method="post" action="GlobalController">
		        <button type="submit" name="userops" value="disconnect">log out</button>
		    </form>
		</header>
		
		<aside> 
			<h2>Informations vous concernant</h2>
			<% User user = (User) session.getAttribute("user"); %>			
			<ul>
				<li> Prénom : <%=user.getName()%>
				<li> Nom : <%=user.getSurname()%>
			    <li> MdP : <%=user.getPassword()%>
				<li> Mail : <%=user.getMail()%>
				<li> Login : <%=user.getLogin()%>
				<li> Groupe : <%=user.getGrp()%>
			</ul>	
		<aside>
		
		<% String grp= user.getGrp(); %>     
		<jsp:include page= "<%=\"./includes/\" + grp + \".jsp\"%>"/>

		<footer> 
			Footer
		</footer>
	
	</body>
	
</html>