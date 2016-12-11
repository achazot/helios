<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav>
	<h2> Menu </h2>
	
	<ul class = "menu">
		<li>
			<form method="post" action="StudentController"> 
				<button type="submit" name="studentops" value="home" class="btn-action">Accueil</button>
			</form>
		</li>
		<li>
			<form method="post" action="StudentController"> 
				<button type="submit" name="studentops" value="menuToggleSubs" class="btn-expand">+&nbsp; Modules Suivis</button>
			</form>
			<c:if test="${menuShowSubs}">
				<ul>
					<c:forEach items="${subs}" var="sub">
					<li> 
						<form method="post" action="StudentController"> 
							<input type="hidden" name="openMod" value="${sub.id}">
							<button type="submit" name="studentops" value="openmodule" class="btn-action">${sub.title}</button>
						</form>
					</li>
					</c:forEach>
				</ul>
			</c:if>
		</li>
		<li>
			<form method="post" action="StudentController"> 
				<button type="submit" name="studentops" value="menuToggleModules" class="btn-expand">+&nbsp; Tous les modules</button>
			</form>
			<c:if test="${menuShowModules}">
				<ul>
					<c:forEach items="${modules}" var="sub">
					<li> 
						<form method="post" action="StudentController"> 
							<input type="hidden" name="subMod" value="${sub.id}">
							<button type="submit" name="studentops" value="subscribe" class="btn-action">${sub.title}</button>
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