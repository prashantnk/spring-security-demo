<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  
<html>

<head>
	<title>Home Page</title>
</head>

<body>
	<h2>Home Page</h2>
	<hr>
	
	Welcome to the home page!
	
	<hr>
	
	User : <security:authentication property="principal.username"/>
	|   
	Roles : <security:authentication property="principal.authorities"/>
	
	<hr>
	
	<p>
		<a href="${pageContext.request.contextPath}/leaders">
			Managers Meeting Link (Only accessible by managers)
		</a>
	</p>
	
	<hr>
	
	<form:form method="post" action="${pageContext.request.contextPath}/logout">
	
		<button type="submit">Logout</button>
	
	</form:form>	
	

</body>

</html>