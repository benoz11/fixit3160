<!--
* dashboard.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
* screen shown after successful login
-->
<html>
<head>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Dashboard</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Hello <sec:authentication property="name"/></h1>

	<!-- display statistic dashbaords -->
	<sec:authorize access="hasRole('Manager')">
		<iframe class="plotlyDashboard" src="//plot.ly/dashboard/aandster:0/embed"></iframe>
	</sec:authorize>

	<sec:authorize access="hasRole('Caseworker')">
		<iframe class="plotlyDashboard" src="//plot.ly/dashboard/aandster:0/embed"></iframe>
	</sec:authorize>

	<sec:authorize access="hasRole('Regular')">
		<iframe class="plotlyDashboard" src="//plot.ly/dashboard/aandster:1/embed"></iframe>
	</sec:authorize>

</body>
</html>