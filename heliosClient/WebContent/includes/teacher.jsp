<%@ page import="entities.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2> hello teacher </h2>

<!-- Find students -->
<h1>Lister vos modules</h1>

<form method="post" action="TeacherController"> 
	<button type="submit" name="teacherops" value="browseModules">Go</button>
</form>

<!-- Display module list -->
<c:choose>
	<c:when test="${not empty modules}">
		Les modules trouvés sont :
			<ul>
				<c:forEach items="${modules}" var="module">
				<li> ${module.title}
				<!-- Operate on current entry -->		
					<form method="post" action="AdminController"> 
						<input type="hidden" name="login" value="${student.login}">
						<button type="submit" name="adminops" value="delete">Supprimer</button>
					</form>
				</c:forEach>
			</ul>
	</c:when>
</c:choose>

