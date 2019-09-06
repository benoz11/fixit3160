<!--
* editmyprofile.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
* Page for editing user profile
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Editing My Profile</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>My Profile</h1>
	<form action="/myprofile/edit/submit" method="POST">
		<table class="table table-striped">
			<tbody>
				<tr><td>Name</td><td><input type="text" name="name" id="name" value="${user.name}"></td></tr>
				<tr><td>User name</td><td><input type="text" name="username" id="username" value="${user.username}"></td></tr>
			</tbody>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn btn-primary">Submit Changes</button>
	</form>
	<a href="/myprofile">Back</a>
	
<jsp:include page="fragments/footer.jsp" />
</body>
</html>