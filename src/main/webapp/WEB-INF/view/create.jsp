<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Creating Ticket</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Creating Ticket</h1>
	<form action="/tickets/create/submit" method="POST">
		<table class="table table-striped">
			<tbody>
				<tr><td>Ticket Name</td><td><input type="text" name="name" id="name" required></td></tr>
				<tr><td>Description</td><td><input type="text" name="description" id="description" required></td></tr>
				

				
		
			</tbody>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn btn-primary">Submit Ticket</button>
	</form>
	<a href="/tickets">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>