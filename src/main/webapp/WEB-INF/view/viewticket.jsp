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
				<div class="comment-contents">${comment.contents}</div>
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