<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>

<script src="/resources/js/jquery-3.4.1.min.js"></script>

<!-- User specific buttons for Ticket state changing -->
<c:choose>
	<c:when test = "${ticket.state eq 'Open'}">
		<sec:authorize access="hasRole('Manager')">
			<button type="button">Delete Ticket</button>
			<button type="button">Close Ticket</button>
			<button type="button">Assign Caseworker</button>
		</sec:authorize>
		
		<sec:authorize access="hasRole('Regular')">
			<button type="button">Edit Ticket</button>
		</sec:authorize>
	</c:when>
	
	<c:when test = "${ticket.state eq 'In Progress'}">
		<sec:authorize access="hasRole('Manager')">
			<button type="button">Delete Ticket</button>
			<button type="button">Assign Caseworker</button>
		</sec:authorize>
		
		<sec:authorize access="hasRole('Caseworker')">
			<button type="button">Close Ticket</button>
		</sec:authorize>
		
		<sec:authorize access="hasRole('Regular')">
			<button type="button">Edit Ticket</button>
		</sec:authorize>
		<!--  all can comment, manager or assigned caseworker can ticket 'mark as resolution' when commenting -->
	</c:when>
	
	<c:when test = "${ticket.state eq 'Resolved'}">
		<sec:authorize access="hasRole('Manager')">
			<button type="button">Delete Ticket</button>
			<button type="button">Assign Caseworker</button>
			<button type="button">Mark as Completed</button>
			<button type="button">Reject Solution</button>
		</sec:authorize>
		
		<sec:authorize access="hasRole('Caseworker')">
			<button type="button">Close Ticket</button>
		</sec:authorize>
		
		<sec:authorize access="hasRole('Regular')">
			<button type="button">Edit Ticket</button>
			<button type="button">Accept Solution</button>
			<button type="button">Reject Solution</button>
		</sec:authorize>
		<!--  all can comment -->
	</c:when>
	
	<c:when test = "${ticket.state eq 'Completed'}">
		<!-- entered Completed statement -->
		<sec:authorize access="hasRole('Manager')">
			<!-- entered Completed-Manager statement -->
			<button type="button">Delete Ticket</button>
			<button type="button">Move to Knowledge Base</button>
		</sec:authorize>
		<!--  all can comment -->
	</c:when>
	
	<c:when test = "${ticket.state eq 'Closed'}">
		<sec:authorize access="hasRole('Manager')">
			<button type="button">Delete Ticket</button>
			<button type="button">Re-Open Ticket</button>
			<!--  only manager can comment -->
		</sec:authorize>
	</c:when>

</c:choose>