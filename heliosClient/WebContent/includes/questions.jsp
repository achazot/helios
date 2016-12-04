<%@ page import="entities.Question" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 	
<!-- View Questions -->
<c:choose>
	<c:when test="${not empty questions}">
			<h1>Questions du QCM </h1>
			<ul>
				<c:forEach items="${questions}" var="question">
				<li> ${question.text} <br>
				Question sur ${question.points}	points<br>
				</c:forEach>
			</ul>
	</c:when>
</c:choose>

