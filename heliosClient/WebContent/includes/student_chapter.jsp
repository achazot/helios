<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1>${module.title}</h1>
<h2>${chapter.title}</h2>
<p>${chapter.text}</p>
<form method="post" action="StudentController"> 
	<input type="hidden" name="openChapter" value="${chapter.id}">
	<input type="hidden" name="openMod" value="${module.id}">
	<button type="submit" name="studentops" value="doqcm">Faire le QCM</button>
</form>