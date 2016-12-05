<%@ page import="entities.Module" %>
<%@ page import="entities.Chapter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!-- View Chapters -->
<c:choose>
	<c:when test="${not empty chapters}">
			<h1>Chapitres du module ${module.title} </h1>
			<ul>
				<c:forEach items="${chapters}" var="chapter">
				<li> ${chapter.title} <br>
				${chapter.text}	<br>
				<c:choose>
					<c:when test="${chapter.getQcm() == false}">
						Il n'y a pas de QCM pour l'instant
						
						<form method="post" action="TeacherController"> 
							<input type="hidden" name="module" value="${module.id}">
							<input type="hidden" name="chapter" value="${chapter.id}">
							<button type="submit" name="teacherops" value="getQCMForm">Créer un QCM</button>
						</form>
					</c:when>	
					<c:otherwise>
						<form method="post" action="TeacherController"> 
							<input type="hidden" name="chapter" value="${chapter.id}">
							<button type="submit" name="teacherops" value="viewQCM">Voir le QCM</button>
						</form>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</ul>
	</c:when>
</c:choose>
