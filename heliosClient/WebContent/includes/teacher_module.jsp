<%@ page import="entities.Module" %>
<%@ page import="entities.Chapter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!-- View Chapters -->
<c:choose>
	<c:when test="${not empty chapters}">
			<h1>Détails du module " ${module.title} "</h1>
			
			<%-- Subscriptions details --%>
			<c:if test="${ not empty subscriptions }">	
				Il y a ${subscriptions.size()} étudiant(s) inscrit(s)
				<form method="post" action="TeacherController">
					<button type="submit" name="teacherops" value="viewSubscriptions">Voir la liste des inscrits</button> 
				</form>
			</c:if>
			
			<%-- Chapters list --%>			
			<ul>
				<c:forEach items="${chapters}" var="chapter">
				<li> ${chapter.title} <br>
				<form method="post" action="TeacherController">
					<input type="hidden" name="chapter" value="${chapter.id}">
					<button type="submit" name="teacherops" value="viewChapter">Voir le chapitre</button>
				</form>
				
				<%-- QCM details --%>
				<c:choose>
				
					<%-- No QCM --%>
					<c:when test="${chapter.getQcm() == false}">
						Il n'y a pas de QCM pour l'instant

						<form method="post" action="TeacherController"> 
							<input type="hidden" name="module" value="${module.id}">
							<input type="hidden" name="chapter" value="${chapter.id}">
							<button type="submit" name="teacherops" value="getQCMForm">Créer un QCM</button>
						</form>
					</c:when>
					
					<%-- View QCM --%>	
					<c:otherwise>
						<form method="post" action="TeacherController"> 
							<input type="hidden" name="chapter" value="${chapter.id}">
							<button type="submit" name="teacherops" value="viewQCM">Voir le QCM</button>
						</form>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</ul>
		<h1>Ajouter un chapitre</h1>
	</c:when>
	
	<c:otherwise>
		<h1>Ce module est un peu vide</h1>
	</c:otherwise>
</c:choose>

<%-- Create Chapter --%>
<form method="post" action="TeacherController">
	<input type="hidden" name="module" value="${module.id}"> 
	<button type="submit" name="teacherops" value="getChapterForm">Créer un chapitre</button>
</form>

<%-- Return to home page --%>
<h1>Retour à la liste des modules</h1>
<form method="post" action="TeacherController">
	<button type="submit" name="teacherops" value="browseModules">Terminer</button>
</form>


