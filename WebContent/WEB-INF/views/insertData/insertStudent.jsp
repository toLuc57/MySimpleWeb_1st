<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Department" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <jsp:include page="..//_menu.jsp"></jsp:include>
      
      <h3>Insert Student</h3>
      
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
      
      <form method="POST" action="${pageContext.request.contextPath}/insertStudent">
         <table border="0">
            <tr>
               <td>Last Name</td>
               <td><input type="text" name="lastName" value="${student.lastName}" /></td>
            </tr>
            <tr>
               <td>First Name</td>
               <td><input type="text" name="firstName" value="${student.firstName}" /></td>
            </tr>
            <tr>
               	<td>Birthday: ${student.birthday}</td>
               	<td>
               	<input type="number" name="day" min="1" max="31" size="3" placeholder="day"/>
               	/<input type="number" name="month" min="1" max="12" size="3" placeholder="month"/>
               	/<input type="number" name="year" size="3" placeholder="year"/>               	
               	</td>
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
               <td>Telephone</td>
               <td><input type="text" name="telephone" value="${student.telephone}" /></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address" value="${student.address}" /></td>
            </tr>
            <tr>
               <td>Id Department</td>
               <td><input type="text" name="idDepartment" value="${student.idDepartment}" required /></td>
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