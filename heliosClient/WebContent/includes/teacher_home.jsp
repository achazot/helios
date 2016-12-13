<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2> Accueil </h2>
 
 <section class="home-section"> 
  	
  	<h2>Vos modules</h2>
  	
  	<div class="home-section-content">
		<c:if test="${not empty modules}">
		
				<c:forEach items="${modules}" var="module">
					<form method="post" action="TeacherController"> 
						<input type="hidden" name="module" value="${module.id}">
						<button type="submit" name="teacherops" value="viewModule" class="btn-action">${module.title}</button>
					</form>
		
				</c:forEach>
		
		</c:if>
	</div>
</section>

<section class="home-section">
	<c:choose>
		<c:when test="${not empty showModuleForm}">
			<h2> Entrer le nom du module </h2>
				
  			<div class="home-section-content">
				<form method="post" action="TeacherController">
					<input type="text" name="title" value="" required> <br>
					<button type="submit" name="teacherops" value="addModule">Ajouter ce module</button>
				</form>
			</div>
		</c:when>
		
		<c:otherwise>
			<form method="post" action="TeacherController"> 
					<button type="submit" name="teacherops" value="getModuleForm" class="btn-action"><h3>Créer un module</h3></button>
			</form>
		</c:otherwise>
	</c:choose>
</section>