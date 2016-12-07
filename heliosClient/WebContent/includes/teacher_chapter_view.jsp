<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>${chapter.title}</h1>

<p>
	${chapter.text}
</p>    

<h1>Retour à la liste des modules</h1>
<form method="post" action="TeacherController">
	<button type="submit" name="teacherops" value="browseModules">Terminer</button>
</form>
