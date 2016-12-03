<%@ page import="entities.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2> hello student </h2>

<h1>Modules auxquels vous �tes inscrit</h1>

<form method="post" action="StudentController"> 
	<button type="submit" name="studentops" value="listSubscriptions">Go</button>
</form>

<!-- Display module list -->
<c:choose>
	<c:when test="${not empty subs}">
		Les modules trouv�s sont :
			<ul>
				<c:forEach items="${subs}" var="sub">
				<li> ${sub.title}
				<!-- Operate on current entry -->		
				</c:forEach>
			</ul>
	</c:when>
</c:choose>

<h1>S'inscrire � un module</h1>

<form method="post" action="StudentController"> 
	<button type="submit" name="studentops" value="browseModules">Go</button>
</form>

<!-- Display module list -->
<c:choose>
	<c:when test="${not empty modules}">
		Les modules trouv�s sont :
			<ul>
				<c:forEach items="${modules}" var="module">
				<li> ${module.title}
					<form method="post" action="StudentController"> 
						<input type="hidden" name="module" value="${module.id}">
						<button type="submit" name="studentops" value="subscribe">S'inscrire</button>
					</form>				</c:forEach>
			</ul>
	</c:when>
</c:choose>

