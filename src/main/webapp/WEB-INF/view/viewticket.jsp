<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
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
			<tr><td>Edit</td><td><a href="/tickets/${ticket.id}/edit">Edit Ticket</a></td></tr>
			<tr><td>Delete</td><td>
			<form action="/tickets/${ticket.id}/delete" method="POST" class="no-margin-block-end"><button type="submit" class="btn btn-link button-none" onclick="return confirm('Are you sure?');">Delete Ticket</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form></td></tr>
		</tbody>
	</table>
	
	<!-- Comments section here -->
	<h1>Comments</h1>
	<div class="comments">
		<c:forEach items="${ticket.comments}" var="comment">
			<div class="comment">
				<div class="comment-poster">${comment.poster.name}:</div>
				<form action="/tickets/${ticket.id}/editcomment" method="POST" id="submitcommenteditform${comment.id}">
					<div class="comment-contents"><textarea name="commentcontents" id="commentcontents${comment.id}" readonly cols="50" rows="5">${comment.contents}</textarea></div>
					<div class="comment-edit">
						<button type="button" name="editbutton" id="editbutton${comment.id}" onClick="editComment(${comment.id})">Edit Comment</button>
						<button type="button" name="canceleditbutton" id="canceleditbutton${comment.id}" onClick="cancelEditComment(${comment.id})" hidden="true">Cancel</button>
						<button type="button" name="submiteditbutton" id="submiteditbutton${comment.id}" onClick="submitCommentEdit(${comment.id})" hidden="true">Submit Changes</button>
					</div>
					<input type="hidden" name="commentid" id="commentid" value="${comment.id}"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
				<div class="comment-delete">
					<form action="/tickets/${ticket.id}/deletecomment" method="POST" class="no-margin-block-end">
						<button type="submit" class="btn btn-link button-none" onclick="return confirm('Are you sure?');">Delete Comment</button>
						<input type="hidden" name="commentid" id="commentid" value="${comment.id}"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<!-- Post a comment -->
	<h3>Post your comment</h3>
	<div class="comment-post">
		<form action="/tickets/${ticket.id}/postcomment" method="POST">
			<textarea name="contents" id="contents" cols="40" rows="5" required></textarea>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button type="submit" class="btn btn-primary">Post Your Comment</button>
		</form>
	</div>
	
	<a href="/tickets">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>