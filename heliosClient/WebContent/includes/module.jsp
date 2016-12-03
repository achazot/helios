<%@ page import="entities.Module" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<% Module module = (Module) session.getAttribute("module"); %>	

<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd"); %>

<!-- Create QCM  -->
<h1>Creer un QCM</h1>

<form method="post" action="TeacherController">

	Titre du module <br>
		<input type="text" name="moduleName" value="${module.title}" disabled> <br>
	Titre du chapitre <br>
	    <select name="chapter">
	  		<option value="1">Chapitre 1</option>
	  		<option value="2">Chapitre 2</option>
	  	</select> <br>
	Titre du QCM <br>
		<input type="text" name="title" value ="title"/> <br>
	Total des points <br>
		<input type="number" name="total" value ="20" min="1" max="40"/> <br>
   	Date de création <br>
  		<input type="date" name="creation" value="<%= df.format( new java.util.Date() )%>" disabled/><br>
	Date d'expiration <br>
		<input type="date" name="expiration"/><br>
	<button type="submit" name="teacherops" value="createQCM">Créer</button>

</form>
