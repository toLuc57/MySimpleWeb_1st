<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Department" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EditDepartment</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <jsp:include page="..//_menu.jsp"></jsp:include>
      
      <h3>Insert Teacher</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Address</th>
          <th>Telephone</th>

       </tr>
       <%
       List<Department> list = (List<Department>) request.getAttribute("departmentList");	
       for(Department i : list){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName() %></td>
			   <td><%= i.getAddress() %></td>
			   <td><%= i.getTelephone() %></td>
	       </tr>
       <% 
       } 
       %>
  	</table>
      
      </br>
      
      <form method="POST" action="${pageContext.request.contextPath}/editTeacher">
         <input type="hidden" name="id" value="${teacher.id}" />
         <table border="0">
         	<tr>
         		<td>ID: </td>
         		<td><output name="id">${teacher.id}</output></td>
         	</tr>
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${teacher.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" value="${teacher.telephone}" /></td>
            </tr>
            <tr>
               <td>Degree</td>
               <td><input type="text" name="degree" value="${teacher.degree}" /></td>
            </tr>
            <tr>
               <td>Id Department</td>
               <td><input type="text" name="idDepartment" value="${teacher.idDepartment}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="teacherList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>