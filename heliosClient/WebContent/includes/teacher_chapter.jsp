<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

<section class="subsection">
	<!-- Create QCM  -->
	<h2> Créer un chapitre pour le module ${module.title} </h2>
	
	<div class="subsection-content">
		<!-- Error control -->
		<c:if test="${not empty errorForm}">
			<strong style="color:red;"> 
				Erreur(s) : <br> 
				${errorForm}
			</strong>
		</c:if>	
		
		
		
		<form method="post" action="TeacherController">
				Entrer le titre du chapitre : <br>
					<input type="text" name="title" value="" required> <br>
				Entrer le contenu du chapitre : <br>
					<textarea name="text" cols="40" rows="5" required></textarea> <br>
					<input type="hidden" name="module" value="${module.id}"> 
				<button type="submit" name="teacherops" value="addChapter">Valider ce Chapitre</button>
		</form>	
	</div>
</section>