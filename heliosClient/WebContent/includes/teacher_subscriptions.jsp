<%@ page import="entities.Subscription" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			
<h1>Statistiques du module ${module.title}</h1>			
<c:if test="${not empty subscriptions}">				
	<c:forEach items="${subscriptions}" var="subscription">

		<br>Nom de l'étudiant 				${subscription.student.name}
		<br>Date d'inscription				${subscription.date}
		<br>Nombre de chapitres terminés  	${subscription.progress}
		
		<c:if test="${subscription.progress != '0'}">
			<c:forEach items="${subscription.getInstances()}" var="instance">
				<br>${instance.note}
			</c:forEach>
			<br> 
		</c:if>
		<br>	
	</c:forEach>
</c:if>
