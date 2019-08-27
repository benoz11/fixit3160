<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<script src="/resources/js/searchScript.js"></script>
	<title>FixIT Tickets</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Tickets</h1>
	<form id="searchForm">
		Search:<input type="text" name="searchTerm" value="" onkeyup = "search()" />
	</form>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>State</th>
				<th>Posted By</th>
				<th>Assigned To</th>
				<th>Date Created</th>
			</tr>
		</thead>
		<tbody id = "ticketTableBody">
			<c:forEach items="${tickets}" var="ticket">
				<tr>
					<td><c:out value="${ticket.name}" /></td>
					<td><c:out value="${ticket.description}" /></td>
					<td><c:out value="${ticket.state}" /></td>
					<td><c:out value="${ticket.posterid}" /></td>
					<td><c:out value="${ticket.caseworkerid}" /></td>
					<td><c:out value="${ticket.created}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form method="get" action="/ticketwithdescription">
		<button type="submit" form="orderForm" name="orderBy" value="asc">Order A-Z</button>
		<button type="submit" form="orderForm" name="orderBy" value="desc">Order Z-A</button>
	</form>
</body>
</html>