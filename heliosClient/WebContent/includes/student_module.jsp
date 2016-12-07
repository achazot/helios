<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>${module.title} </h1> 
<!-- View Chapters -->
<c:choose>
	<c:when test="${not empty chapters}">
			<ul>
				<c:forEach items="${chapters}" var="chapter">
				<li> ${chapter.title}
				<form method="post" action="StudentController">
					<input type="hidden" name="openMod" value="${module.id}">
					<input type="hidden" name="openChapter" value="${chapter.id}">
					<button type="submit" name="studentops" value="readchapter">Lire le chapitre</button>
				</form>
				<c:choose>
					<c:when test="${chapter.getQcm() == false}">
						Il n'y a pas de QCM pour l'instant
					</c:when>	
					<c:otherwise>
						<form method="post" action="StudentController"> 
							<input type="hidden" name="openMod" value="${module.id}">
							<input type="hidden" name="openChapter" value="${chapter.id}">
							<button type="submit" name="studentops" value="doqcm">Faire le QCM</button>
						</form>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</ul>
	</c:when>
	<c:otherwise>
		<h2>Ce cours ne comporte aucun chapitre</h2>
	</c:otherwise>
</c:choose>







