<!--
* myprofile.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
* Allows a user to view their own profile
-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Viewing Profile</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>My Profile</h1>

	<!-- table for viewing a user -->
	<table class="table table-striped">
		<tbody>
			<tr><td>Name</td><td><c:out value="${user.name}" /></td></tr>
			<tr><td>Role</td><td><c:out value="${user.role}" /></td></tr>
			<tr><td>User name</td><td><c:out value="${user.username}" /></td></tr>
			<tr><td>Created</td><td><c:out value="${user.created}" /></td></tr>
			<tr><td>Edit</td><td><a href="/myprofile/edit">Edit User</a></td></tr>
		</tbody>
	</table>
	<a href="/">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>