<!--
* tickets.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
* Page used to view all tickets in FixIT system - proportion of tickets dsiplayed will depend
* on the role of the logged in user
-->

<%@ page import="java.time.LocalDateTime" %>
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
	<h1>Tickets</h1><br>
	<form id="searchForm">
		Search:<input type="text" name="searchTerm" value="" onkeyup = "search()" /><br>
	</form>

	<a href="/tickets/create" class="btn btn-primary">Create New Ticket</a>
	<% out.println(LocalDateTime.now());%>


	<!-- display clickable headers -->
	<table class="table table-striped">
		<thead>
			<tr>
				<th onclick="orderResults(0)">Name</th>
				<th onclick="orderResults(1)">Description</th>
				<th onclick="orderResults(2)">State</th>
				<th onclick="orderResults(3)">Posted By</th>
				<th onclick="orderResults(4)">Assigned To</th>
				<th onclick="orderResults(5)">Date Created</th>
				<th onclick="orderResults(6)">Priority Level</th>
				<th onclick="orderResults(7)">Priority Pointse</th>
			</tr>
		</thead>

		<!-- display each ticket's attributes on a new row -->
		<tbody id = "tableBody">
			<c:forEach items="${tickets}" var="ticket">
				<tr class="clickable-row" data-href="/tickets/${ticket.id}">
					<td><c:out value="${ticket.name}" /></td>
					<td><c:out value="${ticket.description}" /></td>
					<td><c:out value="${ticket.state}" /></td>
					<td><c:out value="${ticket.poster.name}" /></td>
					<td><c:out value="${ticket.caseworker.name}" /></td>
					<td><c:out value="${ticket.created}" /></td>
                    <td><c:out value="${ticket.prioritylevel}" /></td>
                    <td><c:out value="${ticket.prioritypoints}" /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

<jsp:include page="fragments/footer.jsp" />
</body>
</html>