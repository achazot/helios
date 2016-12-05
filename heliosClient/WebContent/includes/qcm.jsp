<%@ page import="entities.Module" %>
<%@ page import="entities.Chapter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 
<% java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd"); %>

<!-- Create QCM  -->
<h1>Creer un QCM</h1>

<!-- Error control -->
<c:if test="${not empty errorForm}">
	<strong style="color:red;"> <br> Erreur(s) : <br> ${errorForm}</strong>
</c:if>	

	Titre du module : 	${module.title} <br>
	Titre du chapitre : ${chapter.title} <br>
	Total des points : 0 niggaz <br>
   	Date de création : 	<%= df.format( new java.util.Date() )%> <br>

<c:choose>

	<c:when test="${not empty qcm}">
		<% session.setAttribute("isQCMCreated", "true"); %>
		Date d'expiration: ${qcm.expiration} <br>
		Affichage des réponses correctes : ${qcm.answersShown}
		<form method="post" action="TeacherController">
			<button type="submit" name="teacherops" value="modifyQCM">Modifier</button>
		</form>
		<c:if test="${not empty questions}">
			<h1>Questions du QCM </h1>
			<ul>
				<c:forEach items="${questions}" var="question">
				<li> ${question.text} <br>
				Question sur ${question.points}	points<br>
				</c:forEach>
			</ul>
		</c:if>
		<form method="post" action="TeacherController">
			Entrer une question <br>
			<input type="text" name="question" value="">
			<input type="number" name="points" min="1">
			<input type="hidden" name="qcm"value="${qcm.id}">
			<button type="submit" name="teacherops" value="addQuestion">Valider cette question</button>
		</form>
		
	</c:when>
	
	<c:otherwise>		
		<form method="post" action="TeacherController">
		
		   	Date d'expiration <br>
				<input type = "date" name="expiration" id = "datepicker-5"> <br> 
			Souhaitez-vous donner les réponses correctes à la fin du QCM ? <br>
				<select name="showAnswers">
					<option value="yes">Oui</option>
					<option value="no">non</option>
			  	</select><br>
			<input type="hidden" name="module" value="${module.id}">
			<input type="hidden" name="chapter" value="${chapter.id}">
			<input type="hidden" name="isQCMCreated" value="false">					
			<button type="submit" name="teacherops" value="createQCM">Suivant</button>
		
		</form>
	</c:otherwise>

</c:choose>

        