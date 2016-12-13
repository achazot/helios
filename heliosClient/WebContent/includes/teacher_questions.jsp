<%@ page import="entities.Question" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 	
<!-- View Questions -->
<c:choose>
	<c:when test="${not empty questionsv}">
		<section class="subsection">
			<h2>Questions du QCM </h2>
			<div class="subsection-content">
				<ul>
					<c:forEach items="${questionsv}" var="questionv">
					
						<%-- Question view --%>
						<li><strong> ${questionv.text} </strong><br>
						<em>Question sur ${questionv.points}	points<br></em>
						<ul>
							<c:forEach items="${questionv.answers}" var="answer">
								<li>
								<c:choose>
									<c:when test="${answer.valid}">
										<strong style="color: green;"> [ * ] </strong> 
									</c:when> 
									<c:otherwise> 
										<strong style="color:red;"> [ * ] </strong> 
									</c:otherwise> 
								</c:choose>
									Réponse " ${answer.text} "
							</c:forEach>
						</ul>
					</c:forEach>
				</ul>
			</div>
		</section>
	</c:when>
</c:choose>

<section class="subsection">
	<h2>Retour au module</h2>
		<div class="subsection-content">
			<form method="post" action="TeacherController">
				<input type="hidden" name="module" value="${module.id}">	
				<button type="submit" name="teacherops" value="viewModule">Terminer</button>
			</form>
		</div>		
</section>