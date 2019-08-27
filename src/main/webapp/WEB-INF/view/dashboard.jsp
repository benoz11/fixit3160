<html>
<head>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Dashboard</title>
</head>
<body>
	<jsp:include page="fragments/navbar.jsp" />
	<h1>Hello <sec:authentication property="name"/></h1>
</body>

</html>