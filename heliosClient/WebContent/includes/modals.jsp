<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal">
	<c:if test="${pushModal != null}">
		<c:if test="${pushModal == 'successfullySubscribed'}">
			<div> Inscription effectu�e </div>
		</c:if> 	
		<c:if test="${pushModal == 'alreadySubscribed'}">
			<div> Vous �tes d�j� inscrit � ce module </div>
		</c:if> 	
		<c:if test="${pushModal == 'notSubscribed'}">
			<div> Vous n'�tes pas inscrit � ce module </div>
		</c:if> 	
		<c:if test="${pushModal == 'tooLate'}">
			<div> Il est trop tard pour r�pondre � ce qcm ! </div>
		</c:if> 	
	</c:if> 
</div>