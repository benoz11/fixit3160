<!--
* viewticket.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
*
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>

<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Viewing Ticket</title>
	
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Viewing Ticket</h1>

	<!-- Buttons here -->
	<table class="table table-striped">
		<tbody>
			<tr><td>Name</td><td><c:out value="${ticket.name}" /></td></tr>
			<tr><td>State</td><td><c:out value="${ticket.state}" /></td></tr>
			<tr><td>Posted By</td><td><c:out value="${ticket.poster.name}" /></td></tr>
			<tr><td>Assigned To</td><td><c:out value="${ticket.caseworker.name}" /></td></tr>
			<tr><td>Description</td><td><c:out value="${ticket.description}" /></td></tr>
			<tr><td>Created</td><td><c:out value="${ticket.created}" /></td></tr>
		</tbody>
	</table>
	<jsp:include page="fragments/ticketbuttons.jsp" />
	<a href="/tickets" style="font-weight:bold">Back</a>
	
	<!-- Post a comment -->
	<br><div class="comment-post">
		<form action="/tickets/${ticket.id}/postcomment" method="POST">
			<textarea name="contents" id="contents" 
     cols="40" rows="5" maxlength="200" style="width:600px; height:120px;border: 3px solid #cccccc; padding:5px; font-family: Tahoma, sans-serif;border-radius:10px; onblur="setbg('white')"; onfocus="this.value"; setbg('#e5fff3');"" required placeholder="Enter Your Comment Here..."></textarea>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			<sec:authorize access="hasRole('Manager')" var="isManager" />
			<sec:authorize access="hasRole('Caseworker')" var="isCaseworker" />
			<c:if test = "${ticket.state eq 'In Progress' && (isManager || isCaseworker)}">
			<br>
			<input type="checkbox" name="resolution" id="resolution"><label for="resolution">Post as Resolution</label>
			</c:if>
			<br>
			<button type="submit" class="btn btn-primary">Publish Comment</button><br>
		</form>
	</div><br>
	
	
	<!-- Comments section here -->
	<h3> Comments: </h3><br>
	<div class="comments">
		<sec:authentication property="principal.username" var="securityusername"/>
		<sec:authorize access="hasRole('Manager')" var="isManager" />
		<c:forEach items="${ticket.comments}" var="comment">
			<div class="comment">
				<form class="no-margin-block-end" action="/tickets/${ticket.id}/editcomment" method="POST" id="submitcommenteditform${comment.id}">
					<div class="comment-poster" style="font-weight:bold">${comment.poster.name}:</div>
					<textarea name="commentcontents" id="commentcontents${comment.id}" cols="100" rows="5" style="border-style:none; resize:none; background-color: #eee" readonly >${comment.contents}</textarea>
					
					
					<c:if test="${securityusername eq comment.poster.username || isManager}">
						<div>
							<button type="button" name="editbutton" id="editbutton${comment.id}" onClick="editComment(${comment.id})">Edit Comment</button>
							<button type="button" name="canceleditbutton" id="canceleditbutton${comment.id}" onClick="cancelEditComment(${comment.id})" hidden="true">Cancel</button>
							<button type="button" name="submiteditbutton" id="submiteditbutton${comment.id}" onClick="submitCommentEdit(${comment.id})" hidden="true">Submit Changes</button>
							<input type="hidden" name="commentid" id="commentid" value="${comment.id}"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</div>
					</c:if>
				</form>
				<c:if test="${securityusername eq comment.poster.username || isManager}">
					<!--<div class="comment-delete">-->
					<form action="/tickets/${ticket.id}/deletecomment" method="POST">
						<button type="submit"  onclick="return confirm('Delete Comment?');">Delete Comment</button>
						<input type="hidden" name="commentid" id="commentid" value="${comment.id}"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form> 
				</c:if>
			</div>
			<br>
		</c:forEach>
	</div>
	<a href="#top" style="font-weight:bold">Go to top</a>

	<jsp:include page="fragments/footer.jsp" />
</body>
</html>