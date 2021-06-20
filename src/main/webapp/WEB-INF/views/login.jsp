<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="/login-process">
		id <input type="text"
                   id="username"
                   name="username"
                   class="form-control"
                   required autofocus> </br>
		password <input type="password"
                   id="password"
                   name="password"
                   class="form-control"
                   required> </br>
		<button type="submit">login</button>
		<c:if test="${error eq true}"> <br>login error.</c:if>
	</form>
</body>
</html>