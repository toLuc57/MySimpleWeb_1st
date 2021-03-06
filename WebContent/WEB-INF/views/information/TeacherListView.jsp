<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Teacher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSTable.css">
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>

    <h3>Teacher List</h3>

    <p style="color: red;">${errorString}</p>
	<br/>
	<jsp:include page="..//_search.jsp"></jsp:include>
	<br/>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Degree</th>
          <th>Telephone</th>
          <th>IdDepartment</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <%
       List<Teacher> list = (List<Teacher>) request.getAttribute("teacherList");
       for(Teacher i : list){    	   
	   	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName() %></td>
			   <td><%= i.getDegree() %></td>
			   <td><%= i.getTelephone() %></td>			   
			   <td><%= i.getIdDepartment() %></td>
			   <td>
		          <a href="teacher/edit?id=<%=i.getId()%>">Edit</a>
		       </td>
		       <td>
		      		<a href="teacher/delete?id=<%=i.getId()%>">Delete</a>
		       </td>
	       </tr>
       <%
       }
       %>
    </table>

    <a href="teacher/insert" >Insert Teacher</a>
	<br/>
	<jsp:include page="..//_pagination.jsp"></jsp:include>
	<br/>
    <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>