<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal">
	<c:if test="${pushModal != null}">
		<c:if test="${pushModal == 'successfullySubscribed'}">
			<div> Inscription effectuée </div>
		</c:if> 	
		<c:if test="${pushModal == 'alreadySubscribed'}">
			<div> Vous êtes déjà inscrit à ce module </div>
		</c:if> 	
		<c:if test="${pushModal == 'notSubscribed'}">
			<div> Vous n'êtes pas inscrit à ce module </div>
		</c:if> 	
		<c:if test="${pushModal == 'tooLate'}">
			<div> Il est trop tard pour répondre à ce qcm ! </div>
		</c:if> 	
	</c:if> 
</div>