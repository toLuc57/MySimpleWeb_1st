<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course,org.hm.SimpleWeb.beans.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Learning Outcomes</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
           
      <p style="color: red;">${errorString}</p>
      <h3>Student List</h3>
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
       </tr>
       <%
       List<Student> list1 = (List<Student>) request.getAttribute("studentList");	
       for(Student i : list1){    	   
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
	       </tr>
       <% 
       } 
       %>
  	</table>
      
      <br/>
      <h3>Course List</h3>
      <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>ID Subject</th>
          <th>ID Teacher</th>
          <th>From Date</th>
          <th>To Date</th>

       </tr>
       <%
       List<Course> list2 = (List<Course>) request.getAttribute("courseList");	
       for(Course i : list2){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getIdCourse() %></td>
			   <td><%= i.getIdSubject() %></td>
			   <td><%= i.getIdTeacher() %></td>
			   <td><%= i.getFromDate() %></td>
			   <td><%= i.getToDate() %></td>
	       </tr>
       <% 
       } 
       %>
  	</table>
      
      <br/>
      
      <h3>Insert Learning outcomes</h3>
      
      <form method="POST" action="${pageContext.request.contextPath}/insertLearningOutcomes">
         <table border="0">
         	<tr>
         		<td>ID student:</td>
         		<td><input type="text" name="idStudent" value="${learningOutcomes.idStudent}" /></td>
         	</tr>
            <tr>
               <td>ID course:</td>
               <td><input type="text" name="idCourse" value="${learningOutcomes.idCourse}" /></td>
            </tr>
            <tr>
               <td>Number of test:</td>
               <td><input type="text" name="numberOfTest" value="${learningOutcomes.numberOfTest}" /></td>
            </tr>
            <tr>
               <td>Point:</td>
               <td><input type="text" name="point" value="${learningOutcomes.point}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="learningOutcomesList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>