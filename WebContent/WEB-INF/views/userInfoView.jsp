<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    </br>
    <table border="1" cellpadding="5" cellspacing="1" >
	    <tr>
	    	<th>Name</th>
	    	<th>IsStudent</th>
	    </tr>
	    <tr>
	    	<td>${user.userName}</td>
	    	<td>${user.student}</td>
	    </tr>
	 </table>
    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>