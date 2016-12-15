<%@ page import="entities.Subscription" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="subsection">

	<h2>Statistiques du module ${module.title}</h2>			
	<div class="subsection-content">
		<table>
		<tr>
			<th> Nom de l'étudiant </th>
			<th> Date d'inscription </th>
			<th> Nombre de chapitres terminés </th>
			<th> Détails d'avancement </th>
		</tr>
		<c:if test="${not empty subscriptions}">				
			<c:forEach items="${subscriptions}" var="subscription">
				<tr>
					<td> ${subscription.student.name} </td>
					<td> ${subscription.date.toLocaleString().substring(0, subscription.date.toLocaleString().lastIndexOf(' ')) } </td>
					<td> ${subscription.progress} </td>

				
				<c:if test="${subscription.progress != '0'}">
					<c:forEach items="${subscription.getInstances()}" var="instance">
					 
						<td><strong>note</strong> : ${instance.note}, <strong>essais</strong> : ${instance.trials}, <strong>date</strong> : ${instance.date.toLocaleString().substring(0, instance.date.toLocaleString().lastIndexOf(' ')) }<td> 
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