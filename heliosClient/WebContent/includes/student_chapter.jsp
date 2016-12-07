<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1>${module.title}</h1>
<h2>${chapter.title}</h2>
<p>${chapter.text}</p>

<c:choose>
	<c:when test="${chapter.getQcm() == false}">
		Il n'y a pas de QCM pour l'instant
	</c:when>	
	<c:otherwise>
		<form method="post" action="StudentController"> 
			<input type="hidden" name="openChapter" value="${chapter.id}">
			<input type="hidden" name="openMod" value="${module.id}">
			<button type="submit" name="studentops" value="doqcm">Faire le QCM</button>
		</form>
	</c:otherwise>
</c:choose>
				
