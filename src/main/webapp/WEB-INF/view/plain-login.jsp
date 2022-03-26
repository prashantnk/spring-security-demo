<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/public/myStyle.css"/>
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath}/authenticateTheUser">
	
	<c:if test="${param.error != null}">
		<i class="failed">Bad Credentials !</i>
	</c:if>
	
	<div>
		<label for="username">UserName : </label>
		<input type="text" name="username"/>
	</div>
	<div>
		<label for="password">Password : </label>
		<input type="password" name="password"/>
	</div>
	<button type="submit"> Submit </button>
	
	<!-- Let's manually add csrf token without using form tag -->
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

</form>

</body>
</html>