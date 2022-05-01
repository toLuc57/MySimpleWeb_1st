<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Subject,org.hm.SimpleWeb.beans.Teacher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Course</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <p style="color: red;">${errorString}</p>      
      
      <h3>Subject List</h3>
      <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Address</th>
          <th>Telephone</th>

       </tr>
       <%
       List<Subject> list1 = (List<Subject>) request.getAttribute("subjectsList");	
       for(Subject i : list1){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName() %></td>
			   <td><%= i.getNumberOfPracticeLesson() %></td>
			   <td><%= i.getNumberOfTheoryLesson() %></td>
	       </tr>
       <% 
       } 
       %>
  	</table>
  	
  	<h3>Teacher List</h3>
      <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Degree</th>
          <th>Telephone</th>
          <th>ID Department</th>			
       </tr>
       <%
       List<Teacher> list2 = (List<Teacher>) request.getAttribute("teacherList");	
       for(Teacher i : list2){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName() %></td>
			   <td><%= i.getDegree() %></td>
			   <td><%= i.getTelephone() %></td>
			   <td><%= i.getIdDepartment() %></td>
	       </tr>
       <% 
       } 
       %>
  	</table>
      
      <h3>Insert Course</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/course/insert">
         <input type="hidden" name="id" value="${course.id}" />
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="id" value="${course.id}" /></td>
            </tr>
            <tr>
               <td>ID Subject</td>
               <td><input type="text" name="idSubject" value="${course.idSubject}" /></td>
            </tr>
            <tr>
               <td>ID Teacher</td>
               <td><input type="text" name="idTeacher" value="${course.idTeacher}" /></td>
            </tr>
			<tr>
               <td>From: </td>
               	<td>
               	<input type="number" name="fromDay" min="1" max="31" size="3" 
               	value="${fromDay}" placeholder="day"/>
               	/<input type="number" name="fromMonth" min="1" max="12" size="3" 
               	value="${fromMonth}" placeholder="month"/>
               	/<input type="number" name="fromYear" size="3" placeholder="year"
               	 value="${fromYear}" pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
               <td>To: </td>
               	<td>
               	<input type="number" name="toDay" min="1" max="31" size="3" placeholder="day"
               	value="${toDay}"/>
               	/<input type="number" name="toMonth" min="1" max="12" size="3" placeholder="month"
               	value="${toMonth}" />
               	/<input type="number" name="toYear" size="3" placeholder="year"
               	value="${toYear}" pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="courseList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>