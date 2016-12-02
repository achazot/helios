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
		        <button type="submit" name="userops" value="disconnect">Déconnexion</button>
		    </form>
		</header>
		
		<aside> 
			<% User user = (User) session.getAttribute("user"); %>	
			
			<h2>Voir les informations vous concernant</h2>
			
			<form method="post" action="GlobalController"> 
				<button type="submit" name="userops" value="infos">Go</button>
			</form>
			
			<!-- Personal informations -->
			<c:if test="${not empty infos}">		
				<ul>
					<li> Prénom : <%=user.getName()%>
					<li> Nom : <%=user.getSurname()%>
				    <li> MdP : <%=user.getPassword()%>
					<li> Mail : <%=user.getMail()%>
					<li> Login : <%=user.getLogin()%>
					<li> Groupe : <%=user.getGrp()%>
				</ul>	
			</c:if>	
		<aside>
		
		<% String grp= user.getGrp(); %>     
		<jsp:include page= "<%=\"./includes/\" + grp + \".jsp\"%>">
			<jsp:param name="user" value="<%=user%>"/>
    	</jsp:include>
		<footer> 
			Footer
		</footer>
	
	</body>
	
</html>