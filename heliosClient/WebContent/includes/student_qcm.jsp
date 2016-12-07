<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1>${module.title}</h1>
<h2>${chapter.title}</h2>

<form method="post">
	<fieldset>
		<legend>Répondez aux questions suivantes : </legend>

		<c:forEach items="${qList}" var="question">
			<h4>${question.text} <i>(${question.points} points)</i></h4>
			<c:forEach items="${question.answers}" var="answer">
				<input type="checkbox" name="${question.id}" value="${answer.id}"/>${answer.text} <br/>
			</c:forEach>
		</c:forEach>
		
		<br/>
		<input type="hidden" name="qcmId" value="${qcm.id}" required/>
		<input type="hidden" name="moduleId" value="${module.id}" required/>
		<input type="hidden" name="chapterId" value="${chapter.id}" required/>								
		<button type="submit" name ="studentops" value="validateQCM" >Valider les réponses</button>

	</fieldset>
</form>
