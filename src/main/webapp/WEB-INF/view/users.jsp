<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Users</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Users</h1>
	
	<table class="table table-striped">
		<thead class="thead-dark">
			<tr>
				<th>Name</th>
				<th>Role</th>
				<th>Username</th>
				<th>Date Created</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.role}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.created}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>