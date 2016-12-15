<%@ page import="entities.Subscription" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="subsection">

	<h2>Statistiques du module ${module.title}</h2>			
	<div class="subsection-content">
		<table>
		<tr>
			<th> Nom de l'�tudiant </th>
			<th> Date d'inscription </th>
			<th> Nombre de chapitres termin�s </th>
			<th> D�tails d'avancement </th>
		</tr>
		<c:if test="${not empty subscriptions}">				
			<c:forEach items="${subscriptions}" var="subscription">
				<tr>
					<td> ${subscription.student.name} </td>
					<td> ${subscription.date.toLocaleString()} </td>
					<td> ${subscription.progress} </td>

				
				<c:if test="${subscription.progress != '0'}">
					<c:forEach items="${subscription.getInstances()}" var="instance">
						note : ${instance.note} <li>essais : ${instance.trials} <li>date : ${instance.date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' } ) }
					</c:forEach>
					<br> 
				</c:if>
				</tr>
				<br>	
			</c:forEach>
		</c:if>
		
		</table>
	</div>
</section>		