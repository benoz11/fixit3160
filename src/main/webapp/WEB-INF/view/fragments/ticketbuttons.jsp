<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>

<script src="/resources/js/jquery-3.4.1.min.js"></script>

<!-- User specific buttons for Ticket state changing -->
<form name="buttonForm" id="buttonForm" action="#" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" id="ticketId" value="${ticket.id}"/>
	<c:choose>
		<c:when test = "${ticket.state eq 'Open'}">
			<sec:authorize access="hasRole('Manager')">
				<button type="button" name="deleteTicketButton">Delete Ticket</button>
				<button type="button" name="closeTicketButton">Close Ticket</button>
				<button type="button" name="assignTicketButton">Assign Caseworker</button>
			</sec:authorize>
			
			<sec:authorize access="hasRole('Regular')">
				<button type="button" name="editTicketButton">Edit Ticket</button>
			</sec:authorize>
		</c:when>
		
		<c:when test = "${ticket.state eq 'In Progress'}">
			<sec:authorize access="hasRole('Manager')">
				<button type="button" name="deleteTicketButton">Delete Ticket</button>
				<button type="button" name="assignTicketButton">Assign Caseworker</button>
			</sec:authorize>
			
			<sec:authorize access="hasRole('Caseworker')">
				<button type="button" name="closeTicketButton">Close Ticket</button>
			</sec:authorize>
			
			<sec:authorize access="hasRole('Regular')">
				<button type="button" name="editTicketButton">Edit Ticket</button>
			</sec:authorize>
			<!--  all can comment, manager or assigned caseworker can ticket 'mark as resolution' when commenting -->
		</c:when>
		
		<c:when test = "${ticket.state eq 'Resolved'}">
			<sec:authorize access="hasRole('Manager')">
				<button type="button" name="deleteTicketButton">Delete Ticket</button>
				<button type="button" name="assignTicketButton">Assign Caseworker</button>
				<button type="button" name="completeTicketButton">Mark as Completed</button>
				<button type="button" name="rejectTicketButton">Reject Solution</button>
			</sec:authorize>
			
			<sec:authorize access="hasRole('Caseworker')">
				<button type="button" name="closeTicketButton">Close Ticket</button>
			</sec:authorize>
			
			<sec:authorize access="hasRole('Regular')">
				<button type="button" name="editTicketButton">Edit Ticket</button>
				<button type="button" name="completeTicketButton">Accept Solution</button>
				<button type="button" name="rejectTicketButton">Reject Solution</button>
			</sec:authorize>
			<!--  all can comment -->
		</c:when>
		
		<c:when test = "${ticket.state eq 'Completed'}">
			<!-- entered Completed statement -->
			<sec:authorize access="hasRole('Manager')">
				<!-- entered Completed-Manager statement -->
				<button type="button" name="deleteTicketButton">Delete Ticket</button>
				<button type="button" name="kbTicketButton">Move to Knowledge Base</button>
			</sec:authorize>
			<!--  all can comment -->
		</c:when>
		
		<c:when test = "${ticket.state eq 'Closed'}">
			<sec:authorize access="hasRole('Manager')">
				<button type="button" name="deleteTicketButton">Delete Ticket</button>
				<button type="button" name="openTicketButton">Re-Open Ticket</button>
				<!--  only manager can comment -->
			</sec:authorize>
		</c:when>
		
		<c:when test = "${ticket.state eq 'Knowledge Base'}">
			<sec:authorize access="hasRole('Manager')">
				<button type="button" name="completeTicketButton">Remove from Knowledge Base</button>
				<!--  only manager can comment -->
			</sec:authorize>
		</c:when>
	
	</c:choose>
</form>