<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<section>
	<h1> Administration du site </h1>
	
	<!-- Find students -->
	<form method="post" action="AdminController"> 
		<button type="submit" name="adminops" value="browseStudents" class="btn-action">Lister les �tudiants inscrit</button>
	</form>
	<!-- Display students list-->
	<c:if test="${not empty students}">
			Les �tudiants trouv�s sont :
		<ul>
			<c:forEach items="${students}" var="student">
			  <li> ${student.name} ${student.surname} </li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<!-- Find teachers -->
	<form method="post" action="AdminController"> 
		<button type="submit" name="adminops" value="browseTeachers" class="btn-action">Lister les professeurs inscrits</button>
	</form>
	<!-- Display teachers list -->
	<c:if test="${not empty teachers}">
			Les professeurs trouv�s sont :
		<ul>
			<c:forEach items="${teachers}" var="teacher">
			   <li>	${teacher.name} ${teacher.surname} </li>
			</c:forEach>
		</ul>
	</c:if>
	
	<!-- Create account -->
		
	<form method="post" action="AdminController"> 
		<button type="submit" name="adminops" value="getForm" class="btn-action">Cr�er un compte</button>
	</form>
	
	<!-- Success control -->
	<c:if test="${not empty userCreated}">
		<strong style="color:green;"> Un utilisateur a �t� cr�� avec succ�s <br></strong>
	</c:if>	
	
	<c:choose>
		<c:when test="${not empty accountForm}">
			Veullez remplir le formulaire suivant :
			
			<!-- Error control -->
			<c:if test="${not empty errorForm}">
				<strong style="color:red;"> <br> Erreur(s) : <br> ${errorForm}</strong>
			</c:if>	
			
			<!-- Account creation form -->		
			<form method="post" action="AdminController"> 
				Nom:<br>
				<input type="text" name="surname_form" maxlength="10"><br>
	  			Pr�nom:<br>
	  			<input type="text" name="name_form" maxlength="10"><br>
	  			Email (doit se terminer par @helios.fr):<br>
				<input type="email" name="mail_form"><br>
	  			Goupe:<br>
	  			<select name="grp_form">
	  				<option value="student">Etudiant</option>
	  				<option value="teacher">Professeur</option>
	  			</select><br>
	  			Identifiant de connexion:<br>
				<input type="text" name="login_form" value="login"><br>
	  			Mot de passe:<br>
	  			<input type="password" name="password_form" value="1234">
	  			<button type="submit" name="adminops" value="submitForm">Valider</button>
			</form>
			<c:if test="${not empty userCreated}">
				${userCreated}
			</c:if>
		</c:when>
	</c:choose>
</section>


