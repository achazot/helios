<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1> Bienvenue sur Helios </h1>

<h2> Prochains QCMs </h2>
<ul>
	<c:forEach items="${nextQCMs}" var="qcm">
	<li> 
		<form method="post" action="StudentController"> 
			<input type="hidden" name="openChapter" value="${qcm.chapter}">
			<button type="submit" name="studentops" value="doqcm">${qcm.getChapter().title}</button>
		</form>
		A réaliser pour le ${qcm.expiration}
	</li>		
	</c:forEach>
</ul>

<h2> Récapitulatif de vos modules </h2>
<!-- Display subscriptions list -->
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

<h2> S'inscrire à un module </h2>
<!-- Display modules list -->
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

