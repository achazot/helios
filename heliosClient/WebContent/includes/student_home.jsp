<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1> Bienvenue sur Helios </h1>

<h2> Prochains QCMs </h2>
<ul>
	<c:forEach items="${nextQCMs}" var="qcm">
	<li> 
		<form method="post" action="StudentController"> 
			<input type="hidden" name="openChapter" value="${qcm.chapter.id}">
			<button type="submit" name="studentops" value="doqcm" class="btn-action">${qcm.chapter.module.title} : ${qcm.chapter.title}</button>
		</form>
		&nbsp;A r�aliser pour le ${qcm.expiration.toLocaleString()}
	</li>		
	</c:forEach>
</ul>

<h2> R�capitulatif de vos modules </h2>
<!-- Display subscriptions list -->
<ul>
	<c:forEach items="${subs}" var="sub">
	<li> 
		<form method="post" action="StudentController" style="display: inline-block;"> 
			<input type="hidden" name="openMod" value="${sub.id}">
			<button type="submit" name="studentops" value="openmodule" class="btn-action">${sub.title}</button>
		</form>
		: ${percentageMap.get(sub)}%				
	</li>		
	</c:forEach>
</ul>

<h2> S'inscrire � un module </h2>
<!-- Display modules list -->
<ul>
	<c:forEach items="${modules}" var="module">
	<li> 
		<form method="post" action="StudentController"> 
			<input type="hidden" name="subMod" value="${module.id}">
			<button type="submit" name="studentops" value="subscribe" class="btn-action">${module.title}</button>
		</form>				
	</li>		
	</c:forEach>
</ul>

