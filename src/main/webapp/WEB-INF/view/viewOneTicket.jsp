<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <jsp:include page="fragments/header.jsp" />
    <title>FixIT Tickets</title>
</head>
<body>
<jsp:include page="fragments/navbar.jsp" />
<h1>View a Specific Ticket</h1>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>State</th>
        <th>Posted By</th>
        <th>Assigned To</th>
        <th>Date Created</th>
    </tr>
</table>

</body>
</html>