<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Department" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Department List</title>
</head>
<body>
<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>

    <h3>Department List (<%=request.getAttribute("page")%>)</h3>

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
       List<Department> list = (List<Department>) request.getAttribute("departmentList");	
       for(Department i : list){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName()%></td>
			   <td><%= i.getAddress()%></td>
			   <td><%= i.getTelephone()%></td>
			   <td>
		          <a href="department/edit?id=<%=i.getId()%>">Edit</a>
		       </td>
		       <td>
		      		<a href="department/delete?id=<%=i.getId()%>">Delete</a>
		       </td>
	       </tr>
       <% 
       } 
       %>
    </table>

    <a href="insertDepartment" >Insert Department</a>
	<br/>
	<jsp:include page="..//_pagination.jsp"></jsp:include>
	<br/>
    <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>