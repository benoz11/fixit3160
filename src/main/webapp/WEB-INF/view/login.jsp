<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="fragments/header.jsp" />
	<title>FixIT Login Page</title>
	<style>
	.error {
		padding: 15px;
		margin-bottom: 20px;
		border: 1px solid transparent;
		border-radius: 4px;
		color: #a94442;
		background-color: #f2dede;
		border-color: #ebccd1;
	}
	
	.msg {
		padding: 15px;
		margin-bottom: 20px;
		border: 1px solid transparent;
		border-radius: 4px;
		color: #31708f;
		background-color: #d9edf7;
		border-color: #bce8f1;
	}
	</style>
</head>
<body>
	<div class="container">
		<!-- Even though this is the login page, directing to /login tells Spring Security to check the datasource against the given user and pass and create a session-->
		<form class="w-50 h-50 p-5 margin-auto" name='loginForm' action="<c:url value='/login' />" method='POST'> 
			<div class="form-group">
				<img class="img-fluid rounded mx-auto d-block" src="/resources/img/logo.png" alt="FixIT" />
				<h2 class="text-center">Welcome to FixIT</h2>
				<h3 class="text-center">Please Log in</h3>
			</div>
	
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
	
	
			<div class="form-group">
				<label for="username">User name</label>
				<input class="form-control" type='text' name='username' id='username' autofocus>
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input class="form-control" type='password' name='password' id='password'/>
			</div>
				<button type="submit" class="btn btn-primary">Log in</button>
	
			  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>