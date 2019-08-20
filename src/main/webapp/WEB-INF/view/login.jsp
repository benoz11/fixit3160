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
	
	#login-box {
		width: 300px;
		padding: 20px;
		margin: 100px auto;
		background: #fff;
		-webkit-border-radius: 2px;
		-moz-border-radius: 2px;
		border: 1px solid #000;
	}
	</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>FixIT Login (Spring Security)</h1>

	<div id="login-box">

		<h2>Login</h2>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

<!-- Even though this is the login page, directing to /login tells Spring Security to check the datasource against the given user and pass and create a session-->
		<form name='loginForm'
		  action="<c:url value='/login' />" method='POST'> 

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
			</tr>
		  </table>

		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>