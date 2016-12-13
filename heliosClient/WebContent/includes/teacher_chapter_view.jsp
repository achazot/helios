<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section class="subsection">
	<h2>${chapter.title}</h2>	
	<div class="subsection-content">
		<p>
			${chapter.text}
		</p>    
	</div>
</section>

<section class="subsection">
	<h2>Retour à la liste des modules</h2>
	<div class="subsection-content">
		<form method="post" action="TeacherController">
			<button type="submit" name="teacherops" value="browseModules">Terminer</button>
		</form>
	</div>
</section>	