<%@ page import="entities.Module" %>
<%@ page import="entities.Chapter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 
<% Module module = (Module) session.getAttribute("module"); %>	
<% Chapter chpater = (Chapter) session.getAttribute("chpater"); %>	
<% java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd"); %>

<!-- Create QCM  -->
<h1>Creer un QCM</h1>

<!-- Error control -->
<c:if test="${not empty errorForm}">
	<strong style="color:red;"> <br> Erreur(s) : <br> ${errorForm}</strong>
</c:if>	
		
<form method="post" action="TeacherController">

	Titre du module <br>
		<input type="text" name="moduleTitle" value="${module.title}" disabled> <br>
	Titre du chapitre <br>
	    <input type="text" name="chapterTitle" value="${chapter.title}" disabled> <br>
	Total des points <br>
		<input type="number" name="total" value ="20" min="1" max="100"/> <br>
   	Date de création <br>
  		<input type="date" name="creation" value="<%= df.format( new java.util.Date() )%>" disabled/><br>
	Date d'expiration <br>
		<input type = "date" name="expiration" id = "datepicker-5"> <br> 
	Souhaitez-vous donner les réposnes correctes à la fin du QCM ? <br>
	<select name="showAnswers">
		<option value="yes">Oui</option>
		<option value="no">non</option>
  	</select><br>
	<input type="hidden" name="module" value="${module.id}">
	<input type="hidden" name="chapter" value="${chapter.id}">					
	<button type="submit" name="teacherops" value="createQCM">Créer</button>

</form>

        