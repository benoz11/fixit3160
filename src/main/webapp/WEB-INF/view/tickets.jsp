<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<script src="/resources/js/searchScript.js"></script>
	<script src="/resources/js/orderingScript.js"></script>
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
				<th onclick="orderResults(0)">Name</th>
				<th onclick="orderResults(1)">Description</th>
				<th onclick="orderResults(2)">State</th>
				<th onclick="orderResults(3)">Posted By</th>
				<th onclick="orderResults(4)">Assigned To</th>
				<th onclick="orderResults(5)">Date Created</th>
			</tr>
		</thead>
		<tbody id = "tableBody">
			<c:forEach items="${tickets}" var="ticket">
				<tr class="clickable-row" data-href="/tickets/${ticket.id}">
					<td><c:out value="${ticket.name}" /></td>
					<td><c:out value="${ticket.description}" /></td>
					<td><c:out value="${ticket.state}" /></td>
					<td><c:out value="${ticket.poster.name}" /></td>
					<td><c:out value="${ticket.caseworker.name}" /></td>
					<td><c:out value="${ticket.created}" /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

<jsp:include page="fragments/footer.jsp" />
</body>
</html>