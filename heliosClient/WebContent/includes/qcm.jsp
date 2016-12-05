<%@ page import="entities.Module" %>
<%@ page import="entities.Chapter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 
<% java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd"); %>

<!-- Create QCM  -->
<h1>Créer un QCM</h1>

<!-- Error control -->
<c:if test="${not empty errorForm}">
	<strong style="color:red;"> 
		Erreur(s) : <br> 
		${errorForm}
	</strong>
</c:if>	

<!-- General Informations -->
	Titre du module 	: 	${module.title} 						<br>
	Titre du chapitre 	: 	${chapter.title} 						<br>
   	Date de création 	: 	<%= df.format( new java.util.Date() )%> <br>

<c:choose>

	<c:when test="${not empty qcm}">
		
		<% session.setAttribute("isQCMCreated", "true"); %>
		
		Date d'expiration	: ${qcm.expiration} 						<br>
		Total des points 	: 	${qcm.total} 							<br>
		Affichage des réponses correctes : <c:if test="${qcm.answersShown}">Oui</c:if><c:if test="not ${qcm.answersShown}">Non</c:if>
		<form method="post" action="TeacherController">
			<button type="submit" name="teacherops" value="modifyQCM">Modifier</button>
		</form>
		
		<%-- Questions List --%>
		<c:if  test="${not empty questions}">
			<h1>Questions du QCM </h1>
			<ul>
				<c:forEach items="${questions}" var="question">
				
					<%-- Question view --%>
					<li><strong> ${question.text} </strong><br>
					<em>Question sur ${question.points}	points<br></em>
					<ul>
						<c:forEach items="${question.answers}" var="answer">
							<li>
							<c:choose>
								<c:when test="${answer.valid}">
									<strong style="color: green;"> [ * ] </strong> 
								</c:when> 
								<c:otherwise> 
									<strong style="color:red;"> [ * ] </strong> 
								</c:otherwise> 
							</c:choose>
								Réponse " ${answer.text} "
						</c:forEach>
					</ul>
					
					<%-- Add Answer --%>
					<form method="post" action="TeacherController">
						
						Ajouter une réponse à cette question <br>
							<input type="text" name="answer" value="" required> <br>
						
						Indiquer si cette réponse est correcte <br>
							<select name="valid">
								<option value="yes">Oui</option>
								<option value="no">non</option>
				  			</select><br>
	
						<input type="hidden" name="questionID" value="${question.id}" required>
						<input type="hidden" name="qcm" value="${qcm.id}" required>
						<button type="submit" name="teacherops" value="addAnswer">Valider cette réponse</button>			
					</form>
					<br>
				</c:forEach>
			</ul>
		</c:if>
		
		<%-- Add Question --%>
		<form method="post" action="TeacherController">
			<h1> Ajouter une nouvelle question </h1> 
				<input type="text" name="question" value="" required>
			<input type="number" name="points" min="1" max="100" required>
			<input type="hidden" name="qcm" value="${qcm.id}" required>
			<button type="submit" name="teacherops" value="addQuestion">Valider cette question</button>
		</form>
		
	</c:when>
	
	<c:otherwise>		
		<form method="post" action="TeacherController">
		
		   	Date d'expiration <br>
				<input type = "date" name="expiration" id = "datepicker-5" required> <br> 
			Souhaitez-vous donner les réponses correctes à la fin du QCM ? <br>
				<select name="showAnswers">
					<option value="yes">Oui</option>
					<option value="no">non</option>
			  	</select><br>
			<input type="hidden" name="module" value="${module.id}" required>
			<input type="hidden" name="chapter" value="${chapter.id}" required>
			<input type="hidden" name="isQCMCreated" value="false" required>					
			<button type="submit" name="teacherops" value="addQCM">Suivant</button>
		
		</form>
	</c:otherwise>

</c:choose>

        