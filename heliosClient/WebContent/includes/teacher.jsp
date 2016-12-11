<%@ page import="entities.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav>
	<h2> Menu </h2>
	
	<ul class = "menu">
		<li>
			<form method="post" action="StudentController"> 
				<button type="submit" name="teacherops" value="home" class="btn-action">Accueil</button>
			</form>
		</li>
		<li>
			<form method="post" action="TeacherController"> 
				<button type="submit" name="teacherops" value="browseModules" class="btn-expand">+&nbsp; Modules tutorés</button>
			</form>
			<c:if test="${not empty modules}">
				<ul>
					<c:forEach items="${modules}" var="module">
					<li> 
						<form method="post" action="TeacherController"> 
							<input type="hidden" name="module" value="${module.id}">
							<button type="submit" name="teacherops" value="viewModule" class="btn-action">${module.title}</button>
						</form>
					</li>
					</c:forEach>
				</ul>
			</c:if>
		</li>
	</ul>
</nav>
<section>
	<jsp:include page= "${actionPage}"/>
</section>