<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Department" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Teacher</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
           
      <p style="color: red;">${errorString}</p>
      
      <h3>Department List</h3>
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
      
      <br/>
      
      <h3>Edit Teacher</h3>
      
      <form method="POST" action="${pageContext.request.contextPath}/editTeacher">
         <input type="hidden" name="id" value="${teacher.id}" />
         <table border="0">
         	<tr>
         		<td>ID: </td>
         		<td><output name="id">${teacher.id}</output></td>
         	</tr>
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" placeholder="${teacher.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" placeholder="${teacher.telephone}" /></td>
            </tr>
            <tr>
               <td>Degree</td>
               <td><input type="text" name="degree" placeholder="${teacher.degree}" /></td>
            </tr>
            <tr>
               <td>Id Department</td>
               <td><input type="text" name="idDepartment" placeholder="${teacher.idDepartment}" required/></td>
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