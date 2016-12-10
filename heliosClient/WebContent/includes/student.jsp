<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav>
	<h2> Menu </h2>
	
	<ul>	
		<li>
			<form method="post" action="StudentController"> 
				<button type="submit" name="studentops" value="home">Accueil</button>
			</form>
		</li>
		<!-- Display module list -->
		<li> 
			<form method="post" action="StudentController"> 
				<button type="submit" name="studentops" value="menuToggleSubs">+</button>
			</form>
			Modules suivis
			<c:if test="${menuShowSubs}">
					<ul>
						<c:forEach items="${subs}" var="sub">
						<li> 
							<form method="post" action="StudentController"> 
								<input type="hidden" name="openMod" value="${sub.id}">
								<button type="submit" name="studentops" value="openmodule">${sub.title}</button>
							</form>
						</li>
						</c:forEach>
					</ul>
			</c:if>
		</li>
				
		<!-- Display module list -->
		<li>
			<form method="post" action="StudentController"> 
				<button type="submit" name="studentops" value="menuToggleModules">+</button>
			</form>
	 		Tous les modules
			<c:if test="${menuShowModules}">
					<ul>
						<c:forEach items="${modules}" var="module">
						<li>
							<form method="post" action="StudentController"> 
								<input type="hidden" name="subMod" value="${module.id}">
								<button type="submit" name="studentops" value="subscribe">${module.title}</button>
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