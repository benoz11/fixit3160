<!--
* assigncaseworker.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
* Page that allows a manager to assign a ticket to a caseworker
-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Assigning Caseworker</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Assigning Caseworker</h1>
	<h3>Ticket</h3>
	<table class="table table-striped">
		<tbody>
			<tr><td>Name</td><td><c:out value="${ticket.name}" /></td></tr>
			<tr><td>State</td><td><c:out value="${ticket.state}" /></td></tr>
			<tr><td>Posted By</td><td><c:out value="${ticket.poster.name}" /></td></tr>
			<tr><td>Assigned To</td><td><c:out value="${ticket.caseworker.name}" /></td></tr>
			<tr><td>Description</td><td><c:out value="${ticket.description}" /></td></tr>
			<tr><td>Created</td><td><c:out value="${ticket.created}" /></td></tr>
		</tbody>
	</table>
	<h3>Assign</h3>
	<form action="/tickets/${ticket.id}/assign/submit" method="POST" class="no-margin-block-end">
		<select name="caseworkerid">
			<c:forEach items="${caseworkers}" var="caseworker">
				<option value="${caseworker.id}">${caseworker.name}</option>		<!-- display list of caseworkers -->
			</c:forEach>
		</select>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit">Assign</button>
	</form>
	<a href="/tickets/${ticket.id}">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>