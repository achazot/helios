<%@ page import="entities.Module" %>
<%@ page import="entities.Chapter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- View Chapters -->	
<c:if test="${not empty chapters}">
	<section class="subsection">	
		<h2>Détails du module " ${module.title} "</h2>
		<div class="subsection-content">
			<%-- Chapters list --%>			
		
			<c:forEach items="${chapters}" var="chapter">
				<strong> ${chapter.title} </strong>
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

		</div>
	</section>
</c:if>


<!-- Subscriptions details -->

	
<c:if test="${ not empty subscriptions }">	
	<section class="subsection">
		<h2>Statistiques du module</h2>
		<div class="subsection-content">
			Il y a ${subscriptions.size()} étudiant(s) inscrit(s)
			<form method="post" action="TeacherController">
				<button type="submit" name="teacherops" value="viewSubscriptions">Voir la liste des inscrits</button> 
			</form>
		</div>
	</section>
</c:if>

<%-- Create Chapter --%>
<section class="subsection">
	<h2>Ajouter un chapitre</h2>
	<div class="subsection-content">
		<form method="post" action="TeacherController">
			<input type="hidden" name="module" value="${module.id}"> 
			<button type="submit" name="teacherops" value="getChapterForm">Créer un chapitre</button>
		</form>
	</div>
</section>


<%-- Return to home page --%>
<section class="subsection">
	<h2>Retour à la liste des modules</h2>
	<div class="subsection-content">
		<form method="post" action="TeacherController">
			<button type="submit" name="teacherops" value="browseModules">Terminer</button>
		</form>
	</div>
</section>

