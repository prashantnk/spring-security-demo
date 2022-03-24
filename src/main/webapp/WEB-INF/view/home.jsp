<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<html>

<head>
	<title>Home Page</title>
</head>

<body>
	<h2>Home Page</h2>
	<hr>
	
	Welcome to the home page!
	
	<form:form method="post" action="${pageContext.request.contextPath}/logout">
	
		<button type="submit">Logout</button>
	
	</form:form>

</body>

</html>