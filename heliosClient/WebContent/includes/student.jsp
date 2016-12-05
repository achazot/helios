<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2> hello student </h2>

<h1>Modules auxquels vous êtes inscrit</h1>

<form method="post" action="StudentController"> 
	<button type="submit" name="studentops" value="listSubscriptions">Go</button>
</form>

<!-- Display module list -->
<c:choose>
	<c:when test="${not empty subs}">
		Les modules trouvés sont :
			<ul>
				<c:forEach items="${subs}" var="sub">
				<li> ${sub.title}
					<form method="post" action="StudentController"> 
						<input type="hidden" name="openMod" value="${sub.id}">
						<button type="submit" name="studentops" value="openmodule">Accéder au module</button>
					</form>
				</c:forEach>
			</ul>
	</c:when>
</c:choose>

<h1>S'inscrire à un module</h1>

<form method="post" action="StudentController"> 
	<button type="submit" name="studentops" value="browseModules">Go</button>
</form>

<!-- Display module list -->
<c:choose>
	<c:when test="${not empty modules}">
		Les modules trouvés sont :
			<ul>
				<c:forEach items="${modules}" var="module">
				<li> ${module.title}
					<form method="post" action="StudentController"> 
						<input type="hidden" name="subMod" value="${module.id}">
						<button type="submit" name="studentops" value="subscribe">S'inscrire</button>
					</form>				
				</c:forEach>
			</ul>
	</c:when>
</c:choose>
