<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Viewing User</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Viewing User</h1>
	
	<table class="table table-striped">
		<tbody>
			<tr><td>Name</td><td><c:out value="${user.name}" /></td></tr>
			<tr><td>ID</td><td><c:out value="${user.id}" /></td></tr>
			<tr><td>Role</td><td><c:out value="${user.role}" /></td></tr>
			<tr><td>User name</td><td><c:out value="${user.username}" /></td></tr>
			<tr><td>Created</td><td><c:out value="${user.created}" /></td></tr>
			<tr><td>Edit</td><td><a href="/users/${user.id}/edit">Edit User</a></td></tr>
			<tr><td>Delete</td><td>
			<form action="/users/${user.id}/delete" method="POST" class="no-margin-block-end"><button type="submit" class="btn btn-link button-none" onclick="return confirm('Are you sure?');">Delete User</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form></td></tr>
		</tbody>
	</table>
	<a href="/users">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>