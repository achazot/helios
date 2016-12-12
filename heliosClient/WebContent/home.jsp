<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<head>
		<meta charset="utf-8">
		<title>Profil Personnel</title>
		<meta name="description" content="Index">
		<meta name="author" content="dibi & kcs">
		
		<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
		        rel = "stylesheet">
		<link href = "css/styles.css" rel = "stylesheet">
		<link href = "css/teacher.css" rel = "stylesheet">
		<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
		<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>    
		<!-- Javascript date picker -->
		<script>
			$(function() {
	            $( "#datepicker-5" ).datepicker({
	               beforeShowDay : function (date) {
	                  var dayOfWeek = date.getDay ();
	                  if (dayOfWeek == 0 || dayOfWeek == 6) return [false];
	                  else return [true];
	               }
	            });
	         });
		</script>
	</head>
	
	<body>
	
	
		<div class="wrapper">
		
			<header>
				<a href="/helios"><img src="img/helios_logo.svg" style="height: 64px;"/></a>
				<div tabindex="0" class="onclick-menu">
					<div class="onclick-menu-label">
						<span><b>${user.login}&nbsp&nbsp</b></span>
						<img src="img/user.svg" class="onclick-menu-icon"/>
					</div>
					<!-- Personal informations -->
					<ul class="onclick-menu-content clearfix">
						<li> Prénom : ${user.name} </li>
						<li> Nom : ${user.surname} </li>
						<li> Mail : ${user.mail} </li>
						<li> Login : ${user.login} </li>
						<li> Groupe : ${user.grp} </li>
						<li><br/>
							<form method="post" action="GlobalController">
						        <button type="submit" name="userops" value="disconnect" style="pointer-events: auto;" class="btn-action">Déconnexion</button>
						    </form>
						</li>
					</ul>	
				</div>	
			</header>
			
			<jsp:include page= "includes/modals.jsp"/>     	
	
			<div class="page-container">
	    		<jsp:include page= "${viewPage}"/>
	    	</div>
	    	    	
    	</div>
    	
    	
		<footer> 
			<span>Lisa Aubry<br/>Alban Chazot</span>
			<span><img src="img/sun.svg" style="width:64px;" alt="Helios 2016"/></span>
			<span>No contact<br/>Full contact</span>
		</footer>
	
	</body>
	
</html>