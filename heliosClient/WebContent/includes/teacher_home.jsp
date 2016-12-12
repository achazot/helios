<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2> Accueil </h2>
 
<c:if test="${not empty modules}">

		<c:forEach items="${modules}" var="module">
			<form method="post" action="TeacherController"> 
				<input type="hidden" name="module" value="${module.id}">
				<h1>
					<button type="submit" name="teacherops" value="viewModule" class="btn-action">${module.title}</button>

				</h1>
			</form>

		</c:forEach>

</c:if>

<c:choose>
	<c:when test="${not empty showModuleForm}">
		<form method="post" action="TeacherController">
		Entrer le nom du module <br>
			<input type="text" name="title" value="" required> <br>
			<button type="submit" name="teacherops" value="addModule">Ajouter ce module</button>
		</form>
	</c:when>
	
	<c:otherwise>
		<form method="post" action="TeacherController"> 
			<h1>
				<button type="submit" name="teacherops" value="getModuleForm" class="btn-action">Créer un module</button>
			</h1>
		</form>
	</c:otherwise>
</c:choose>
