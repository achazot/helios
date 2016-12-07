<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Résultats du QCM du chapitre ${chapter.title}</h1>

<c:choose>
	<c:when test="${qcmSuccess}">
		<p>Vous avez réussi le QCM !</p>
	</c:when> 
	<c:otherwise> 
		<p>Vous avez échoué lamentablement.<p>
	</c:otherwise> 
</c:choose>

<p>Votre note: ${qcmUserNote}/${qcmNote}. ${qcmMinimum} points sont requis pour valider le chapitre.</p>

<c:choose>
	<c:when test="${showRightAnswers}">
		<div>
		<h3>Correction:</h3>
		<ul>
		<c:forEach items="${qList}" var="question">
			<li>${question.text} <i>(${question.points} points)</i></li>
			<ul>
			<c:forEach items="${question.answers}" var="answer">
				<c:if test="${answer.valid}">
					<li>${answer.text}</li>
				</c:if> 
			</c:forEach>
			</ul>
		</c:forEach>
		</ul>
		</div>
	</c:when> 
	<c:otherwise> 
		<div>Le professeur a décidé de ne pas communiquer les réponses</div>
	</c:otherwise> 
</c:choose>

<c:if test="${not qcmSuccess}">
<form method="post">
	<input type="hidden" name="openMod" value="${module.id}" required/>
	<input type="hidden" name="openChapter" value="${chapter.id}" required/>	
    <button type="submit" name ="studentops" value="doqcm" > Essayer de nouveau </button>
</form>
</c:if> 
<form method="post">
	<input type="hidden" name="openMod" value="${module.id}" required/>
    <button type="submit" name ="studentops" value="openmodule" > Retourner aux chapitres </button>
</form>
		