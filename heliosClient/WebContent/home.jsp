<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
	
		<title>La base</title>
		<meta name="description" content="Index">
		<meta name="author" content="dibi & kcs">
	</head>
	
	<body>
		<h2>Si t'es connecté on voit ton nom ici:</h2>
		<div>
			<% User user = (User) session.getAttribute("user"); %>
			<c:if test="${not empty user}">
				<%=user.getName()%>
				<%=user.getSurname()%>
				<%=user.getPassword()%>
				<%=user.getMail()%>
				<%=user.getLogin()%>
				<%=user.getGrp()%>
			</c:if>
		</div>
	</body>
</html>