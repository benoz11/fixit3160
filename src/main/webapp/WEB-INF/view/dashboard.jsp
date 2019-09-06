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
	<!-- display statistics embedded in an iframe-->
	<iframe width="100%" height="800" frameborder="0" scrolling="no" src="//plot.ly/dashboard/jordan.maddock:12/embed"></iframe>

</body>
</html>