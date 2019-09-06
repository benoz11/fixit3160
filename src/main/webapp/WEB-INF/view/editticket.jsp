<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Edit Ticcket</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Viewing Ticket</h1>
	<form action="/tickets/${ticket.id}/edit/submit" method="POST">
		<table class="table table-striped">
			<tbody>
				<tr><td>Description</td><td><input type="text" name="description" id="description" value="${ticket.description}"></td></tr>
				<tr><td>State</td>
				<td>
					<input type="hidden" name="originalstate" id="originalstate" value="${ticket.state}">
					<input type="radio" name="state" id="open" value="Open">
					<label for="Open">Open</label>
					
					<input type="radio" name="state" id="inprogress" value="inprogress">
					<label for="In-Progress">In-Progress</label>
					
					<input type="radio" name="state" id="complete" value="complete">
					<label for="Open">Complete</label>
					
					<input type="radio" name="state" id="kb" value="kb">
					<label for="Open">Knowledge Base</label>
					
					<input type="radio" name="state" id="resolved" value="resolve">
					<label for="Open">Resolved</label>
					
				</td></tr>
				<tr><td>Name</td><td><input type="text" name="name" id="name" value="${ticket.name}"></td></tr>
			</tbody>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn btn-primary">Submit Changes</button>
	</form>
	<a href="/viewticket">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>