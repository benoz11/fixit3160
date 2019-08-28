<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Editing User</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Viewing User</h1>
	<form action="/users/${user.id}/edit/submit" method="POST">
		<table class="table table-striped">
			<tbody>
				<tr><td>Name</td><td><input type="text" name="name" id="name" value="${user.name}"></td></tr>
				<tr><td>Role</td>
				<td>
					<input type="hidden" name="originalrole" id="originalrole" value="${user.role}">
					<input type="radio" name="role" id="Manager" value="Manager">
					<label for="Manager">Manager</label>
					<input type="radio" name="role" id="Caseworker" value="Caseworker">
					<label for="Caseworker">Caseworker</label>
					<input type="radio" name="role" id="Regular" value="Regular">
					<label for="Regular">Regular</label>
				</td></tr>
				<tr><td>User name</td><td><input type="text" name="username" id="username" value="${user.username}"></td></tr>
			</tbody>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn btn-primary">Submit Changes</button>
	</form>
	<a href="/users">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>