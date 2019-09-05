<!--
* knowledgebase.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
* Ensure that same JSTL tags and formatting are used on every .jsp in the webapp
-->

<%@ page import="java.time.LocalDateTime" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<script src="/resources/js/searchScript.js"></script>
	<script src="/resources/js/orderingScript.js"></script>
	<title>FixIT Knowledge Base</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Knowledge Base</h1>
	<form id="searchForm">
		Search:<input type="text" name="searchTerm" value="" onkeyup = "search()" />
	</form>

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
				<th onclick="orderResults(7)">Priority Points</th>
			</tr>
		</thead>
		<!-- display tickets in knowledge, each one on a new line -->
		<tbody id = "tableBody">
			<c:forEach items="${knowledgeBaseTickets}" var="ticket">
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