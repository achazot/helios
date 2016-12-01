<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2> hello admin </h2>

<!-- Find students -->
<h1>Lister les étudiants inscrits</h1>
<form method="post" action="AdminController"> 
	<button type="submit" name="adminops" value="browseStudents">Go</button>
</form>
<!-- Display students list-->
<c:choose>
	<c:when test="${not empty students}">
		Les étudiants trouvés sont :
			<ul>
				<c:forEach items="${students}" var="student">
				<li> ${student.name} ${student.surname}
				<!-- Operate on current entry -->		
					<form method="post" action="AdminController"> 
						<input type="hidden" name="login" value="${student.login}">
						<button type="submit" name="adminops" value="delete">Supprimer</button>
					</form>
				</c:forEach>
			</ul>
	</c:when>
</c:choose>


<!-- Find teachers -->
<h1>Lister les professeurs inscrits</h1>
<form method="post" action="AdminController"> 
	<button type="submit" name="adminops" value="browseTeachers">Go</button>
</form>
<!-- Display teachers list -->
<c:choose>
	<c:when test="${not empty teachers}">
		Les professeurs trouvés sont :
			<ul>
				<c:forEach items="${teachers}" var="teacher">
					<li> ${teacher.name} ${teacher.surname} 
					<!-- Operate on current entry -->	
						<form method="post" action="AdminController">	
							<input type="hidden" name="login" value="${teacher.login}">
							<button type="submit" name="adminops" value="delete">Supprimer</button>
						</form>
				</c:forEach>
			</ul>
	</c:when>
</c:choose>

<!-- Create account -->
<h1>Créer un compte</h1>
	
<form method="post" action="AdminController"> 
	<button type="submit" name="adminops" value="getForm">Go</button>
</form>

<!-- Success control -->
<c:if test="${not empty userCreated}">
	<strong style="color:green;"> Un utilisateur a été créé avec succès <br></strong>
</c:if>	

<c:choose>
	<c:when test="${not empty accountForm}">
		Veullez remplir le formulaire suivant :
		
		<!-- Error control -->
		<c:if test="${not empty errorForm}">
			<strong style="color:red;"> <br> Erreur : ${errorForm}</strong>
		</c:if>	
		
		<!-- Account creation form -->		
		<form method="post" action="AdminController"> 
			Nom:<br>
			<input type="text" name="surname_form" maxlength="10"><br>
  			Prénom:<br>
  			<input type="text" name="name_form" maxlength="10"><br>
  			Email (doit se terminer par @helios.fr):<br>
			<input type="email" name="mail_form"><br>
  			Goupe:<br>
  			<select name="grp_form">
  				<option value="student">Etudiant</option>
  				<option value="teacher">Professeur</option>
  			</select><br>
  			Identifiant de connexion:<br>
			<input type="text" name="login_form"><br>
  			Mot de passe:<br>
  			<input type="password" name="password_form">
  			<button type="submit" name="adminops" value="submitForm">Valider</button>
		</form>
		<c:if test="${not empty userCreated}">
			${userCreated}
		</c:if>
	</c:when>
</c:choose>



