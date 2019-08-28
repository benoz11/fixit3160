<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Users</title>
	<script src="/resources/js/searchScript.js"></script>
	<script src="/resources/js/orderingScript.js"></script>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Users</h1>
	<a href="/users/create" class="btn btn-primary">Create New User</a>

	<form id="searchForm">
		Search:<input type="text" name="searchTerm" value="" onkeyup = "search()" />
	</form>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th onclick="orderResults(0)">Name</th>
				<th onclick="orderResults(1)">Role</th>
				<th onclick="orderResults(2)">Username</th>
				<th onclick="orderResults(3)">Date Created</th>
			</tr>
		</thead>
		<tbody id="tableBody">
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