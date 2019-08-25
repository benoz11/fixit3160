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
			<tr><td>Username</td><td><c:out value="${user.username}" /></td></tr>
			<tr><td>Created</td><td><c:out value="${user.created}" /></td></tr>
		</tbody>
	</table>
	<a href="/users">Back</a>
</body>
</html>