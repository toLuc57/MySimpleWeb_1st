<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course List</title>
</head>
<body>
<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>

    <h3>Course List</h3>

    <p style="color: red;">${errorString}</p>

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Address</th>
          <th>Telephone</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <%
       List<Course> list = (List<Course>) request.getAttribute("courseList");	
       for(Course i : list){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getIdCourse() %></td>
			   <td><%= i.getIdSubject() %></td>
			   <td><%= i.getIdTeacher() %></td>
			   <td><%= i.getFromDate() %></td>
			   <td><%= i.getToDate() %></td>
			   <td>
		          <a href="editCourse?id=<%=i.getIdCourse()%>">Edit</a>
		       </td>
		       <td>
		      		<a href="deleteCourse?id=<%=i.getIdCourse()%>">Delete</a>
		       </td>
	       </tr>
       <% 
       } 
       %>
    </table>

    <a href="insertCourse" >Insert Course</a>

    <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>