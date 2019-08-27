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
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Name</th>
				<th>Role</th>
				<th>Username</th>
				<th>Date Created</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr class="clickable-row" data-href="/users/${user.id}">
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.role}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.created}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>