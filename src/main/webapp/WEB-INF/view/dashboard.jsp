<html>
<head>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Dashboard</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Hello <sec:authentication property="name"/></h1>

	<iframe width="100%" height="800" frameborder="0" scrolling="no" src="//plot.ly/dashboard/jordan.maddock:12/embed"></iframe>

</body>

</html>