<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>

<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Viewing Ticket</title>
	<style>
div.comment {
  height:150px;
  border: 0.25px solid #cccccc;
  font-family: Tahoma, sans-serif;
  border-radius:10px; 
  background-color: blue
  width: 590px;
  padding: 10px;
  margin: 10px;
}


</style>
</head>
<body>

	<jsp:include page="fragments/navbar.jsp" />
	<h1>Viewing Ticket</h1>
	
	<!-- Buttons here -->
	<a href="/tickets/${ticket.id}/editticket" class="btn btn-primary">Edit Ticket</a><br>
	<table class="table table-striped">
		<tbody>
			<tr><td>Name</td><td><c:out value="${ticket.name}" /></td></tr>
			<tr><td>State</td><td><c:out value="${ticket.state}" /></td></tr>
			<tr><td>Posted By</td><td><c:out value="${ticket.poster.name}" /></td></tr>
			<tr><td>Assigned To</td><td><c:out value="${ticket.caseworker.name}" /></td></tr>
			<tr><td>Description</td><td><c:out value="${ticket.description}" /></td></tr>
			<tr><td>Created</td><td><c:out value="${ticket.created}" /></td></tr>
			<tr><td>Edit</td><td><a href="/tickets/${ticket.id}/edit">Edit Ticket</a></td></tr>
			<tr><td>Delete</td><td>
			<form action="/tickets/${ticket.id}/delete" method="POST" class="no-margin-block-end"><button type="submit" class="btn btn-link button-none" onclick="return confirm('Delete Ticket?');">Delete Ticket</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form></td></tr>
		</tbody>
	</table>
	<jsp:include page="fragments/ticketbuttons.jsp" />
	<a href="/tickets" style="font-weight:bold">Back</a>
	
	<!-- Post a comment -->
	<br><div class="comment-post">
		<form action="/tickets/${ticket.id}/postcomment" method="POST">
			<textarea name="contents" id="contents" 
     cols="40" rows="5" style="width:600px; height:120px;border: 3px solid #cccccc; padding:5px; font-family: Tahoma, sans-serif;border-radius:10px; onblur="setbg('white')"; onfocus="this.value"; setbg('#e5fff3');"" required placeholder="Enter Your Comment Here..."></textarea>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			<sec:authorize access="hasRole('Manager')" var="isManager" />
			<sec:authorize access="hasRole('Caseworker')" var="isCaseworker" />
			<c:if test = "${ticket.state eq 'In Progress' && (isManager || isCaseworker)}">
			<br>
			<input type="checkbox" name="id" value="Resolution"> Post as Resolution 
			</c:if>
			<br>
			<button type="submit" class="btn btn-primary">Publish Comment</button><br>
		</form>
	</div><br>
	
	
	<!-- Comments section here -->
	<h3> Comments: </h3><br>
	<div class="comments">
		<c:forEach items="${ticket.comments}" var="comment">
			<div class="comment">
				<form action="/tickets/${ticket.id}/editcomment" method="POST" id="submitcommenteditform${comment.id}">
				<div class="comment-poster" style="font-weight:bold">${comment.poster.name}:</div>
				<textarea name="commentcontents" id="commentcontents${comment.id}" cols="200" rows="4" style="border-style:none; overflow:none; background-color: #eee" readonly >${comment.contents}</textarea>
				</div>
				
						<button type="button" name="editbutton" id="editbutton${comment.id}" onClick="editComment(${comment.id})">Edit Comment</button>
						<button type="button" name="canceleditbutton" id="canceleditbutton${comment.id}" onClick="cancelEditComment(${comment.id})" hidden="true">Cancel</button>
						<button type="button" name="submiteditbutton" id="submiteditbutton${comment.id}" onClick="submitCommentEdit(${comment.id})" hidden="true">Submit Changes</button>
					
					<input type="hidden" name="commentid" id="commentid" value="${comment.id}"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
				<!--<div class="comment-delete">-->
				<br>
					<form action="/tickets/${ticket.id}/deletecomment" method="POST" class="no-margin-block-end">
						<button type="submit"  onclick="return confirm('Delete Comment?');">Delete Comment</button>
						<input type="hidden" name="commentid" id="commentid" value="${comment.id}"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form> 
				
			
		</c:forEach>
	</div>
	
	
	<br>
	<a href="#top" style="font-weight:bold">Go to top</a>
	<br><br>
	
	
	
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>