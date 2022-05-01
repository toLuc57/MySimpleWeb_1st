<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.util.List, org.hm.SimpleWeb.beans.UserAccount"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    <br/>
    <label>User Name: </label>
    <output>${user.userName}</output>
    <br/>
    <%
    	UserAccount user = (UserAccount) request.getAttribute("user");
    	String job;
    	if(user.isStudent()){
    		job = "Student";
    	}
    	else{
    		job = "Teacher";
    	}
    %>
	<label>Job</label>
	<output><%=job%></output>
	<br/>
    <table border="1" cellpadding="5" cellspacing="1" >
	    <tr>
	    	<th>Roles</th>
	    </tr>
	    	<%
	    	for(String i : user.getRoles()){
	    	%>
	    		<tr>
	    		  <td><%=i%></td>
	    		</tr>
	    	<%	
	    	}
	    	%>
	 </table>
    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>