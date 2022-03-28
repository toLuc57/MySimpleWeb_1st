<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,org.hm.SimpleWeb.beans.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student List</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>

    <h3>Student List</h3>

    <p style="color: red;">${errorString}</p>

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Last Name</th>
          <th>First Name</th>
          <th>Birthday</th>
          <th>Sex</th>
          <th>Telephone</th>
          <th>Address</th>
          <th>IdDepartment</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <%
       List<Student> list = (List<Student>) request.getAttribute("studentList");
       for(Student i : list){    	   
	   	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
		   	   <td><%= i.getLastName() %></td>
			   <td><%= i.getFirstName() %></td>
			   <td><%= i.getBirthday() %></td>
			   <td><%= i.getSex() %> </td>
			   <td><%= i.getTelephone() %></td>
			   <td><%= i.getAddress() %></td>
			   <td><%= i.getIdDepartment() %></td>
			   <td>
		          <a href="editStudent?id=<%=i.getId()%>">Edit</a>
		       </td>
		       <td>
		      		<a href="deleteStudent?id=<%=i.getId()%>">Delete</a>
		       </td>
	       </tr>
       <%
       }
       %>
    </table>

    <a href="insertStudent" >Insert Student</a>
	<br/>
	<jsp:include page="..//_pagination.jsp"></jsp:include>
	<br/>
    <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>