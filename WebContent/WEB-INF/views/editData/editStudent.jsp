<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.sql.Date,org.hm.SimpleWeb.beans.Department" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
	<h3>Department List</h3>
      
      <p style="color: red;">${errorString}</p>

      <table border="1" cellpadding="5" cellspacing="1">
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
      <h3>Edit Student</h3>
      
      <form method="POST" action="${pageContext.request.contextPath}/editStudent">
         <input type="hidden" name="id" value="${student.id}" />
         <table border="0">
         	<tr>
         		<td>ID: </td>
         		<td><output name="id">${student.id}</output></td>
         	</tr>
            <tr>
               <td>Last Name</td>
               <td><input type="text" name="lastName" placeholder="${student.firstName}" /></td>
            </tr>
            <tr>
               <td>First Name</td>
               <td><input type="text" name="firstName" placeholder="${student.lastName}" /></td>
            </tr>
            <tr>
               	<td>Birthday:</td>
               	<td>
               	<input type="number" name="day" min="1" max="31" size="3" 
               	placeholder="${day}" />
               	/<input type="number" name="month" min="1" max="12" size="3" 
               	placeholder="${month}"/>
               	/<input type="number" name="year" size="3" placeholder="${year}"
               	pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
            	<td>Sex</td>
            	<td><output name="sex">${student.sex}</output></td>
           	</tr>
            <tr>
               <td rowspan="3">Sex</td>
               <td><input type="radio" name="sex" value="Male" />Male</td>
            </tr>
            <tr>
               <td><input type="radio" name="sex" value="Female" />Female</td>
            </tr>
            <tr>
               <td><input type="radio" name="sex" value="Other" />Other</td>
            </tr>
            <tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" placeholder="${student.telephone}" /></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address" placeholder="${student.address}" /></td>
            </tr>
            <tr>
               <td>Id Department</td>
               <td><input type="text" name="idDepartment" placeholder="${student.idDepartment}" required/></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="studentList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>