<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Learning Outcomes</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
           
      <p style="color: red;">${errorString}</p>
      
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
       List<Course> list = (List<Course>) request.getAttribute("courseList");	
       for(Course i : list){    	   
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
         		<td><output name="id">${learningOutcomes.idStudent}</output></td>
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