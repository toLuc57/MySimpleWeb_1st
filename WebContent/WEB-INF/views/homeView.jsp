<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTP-8">
<title>Home page</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Home page!</h3>
	This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
    <b>It includes the following functions:</b>
    <ul>
       <li>Login</li>
       <li>Storing user information in cookies</li>
     </ul>
     
     <jsp:include page="_footer.jsp"></jsp:include>
     
</body>
</html>