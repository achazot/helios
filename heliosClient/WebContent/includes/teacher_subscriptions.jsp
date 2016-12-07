<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			
<c:if test="${not empty subscriptions}">				
	<c:forEach items="${subscriptions}" var="subscription">
		<ul>
			<li>${subscription.student.name}
			${subscription.progress}
			${subscription.date}
		</ul>	
	</c:forEach>
</c:if>