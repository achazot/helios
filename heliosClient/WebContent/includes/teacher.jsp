<%@ page import="entities.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav>
	<h2> Menu </h2>
	
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
						<form method="post" action="TeacherController"> 
							<input type="hidden" name="module" value="${module.id}">
							<button type="submit" name="teacherops" value="viewModule"> Voir </button>
						</form>
					</c:forEach>
				</ul>
		</c:when>
	</c:choose>
</nav>
<section>
	<jsp:include page= "${actionPage}"/>
</section>