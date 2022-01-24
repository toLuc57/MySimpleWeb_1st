<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.GiaoVien" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher List</title>
</head>
<body>
<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>

    <h3>Product List</h3>

    <p style="color: red;">${errorString}</p>

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Telephone</th>
          <th>Degree</th>
          <th>IdDepartment</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <%
       List<GiaoVien> list = (List<GiaoVien>) request.getAttribute("teacherList");
       for(GiaoVien i : list){    	   
	   	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName() %></td>
			   <td><%= i.getTelephone() %></td>
			   <td><%= i.getDegree() %></td>
			   <td><%= i.getIdDepartment() %></td>
			   <td>
		          <a href="editTeacher?code=<%=i.getId()%>">Edit</a>
		       </td>
		       <td>
		      		<a href="deleteTeacher?code=<%=i.getId()%>">Delete</a>
		       </td>
	       </tr>
       <%
       }
       %>
    </table>

    <a href="insertTeacher" >Insert Teacher</a>

    <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>