<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Creating User</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Creating User</h1>
	<form action="/users/create/submit" method="POST">
		<table class="table table-striped">
			<tbody>
				<tr><td>Name</td><td><input type="text" name="name" id="name" required></td></tr>
				<tr><td>Role</td>
				<td>
					<input type="radio" name="role" id="Manager" value="Manager" required>
					<label for="Manager">Manager</label>
					<input type="radio" name="role" id="Caseworker" value="Caseworker">
					<label for="Caseworker">Caseworker</label>
					<input type="radio" name="role" id="Regular" value="Regular">
					<label for="Regular">Regular</label>
				</td></tr>
				<tr><td>User name</td><td><input type="text" name="username" id="username" required></td></tr>
				<tr><td>Password</td><td><input type="password" name="password" id="password" required></td>
			</tbody>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn btn-primary">Submit Changes</button>
	</form>
	<a href="/users">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>