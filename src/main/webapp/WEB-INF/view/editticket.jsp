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
				<tr><td>Name</td><td><input type="text" name="name" id="name" size="50" maxlength="50" value="${ticket.name}"></td></tr>
				<tr><td>Description</td><td><input type="text" name="description" id="description" size="50" maxlength="200" value="${ticket.description}"></td></tr>
			</tbody>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn btn-primary">Submit Changes</button>
	</form>
	<a href="/tickets/${ticket.id}">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>