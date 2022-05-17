<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Subject" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subject List</title>
</head>
<body>
<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>

    <h3>Subject List</h3>

    <p style="color: red;">${errorString}</p>
	<br/>
	<jsp:include page="..//_search.jsp"></jsp:include>
	<br/>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Practice lesson</th>
          <th>Theory lesson</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <%
       List<Subject> list = (List<Subject>) request.getAttribute("subjectsList");	
       for(Subject i : list){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName()%></td>
			   <td><%= i.getNumberOfPracticeLesson() %></td>
			   <td><%= i.getNumberOfTheoryLesson()%></td>
			   <td>
		          <a href="editSubject?id=<%=i.getId()%>">Edit</a>
		       </td>
		       <td>
		      		<a href="deleteSubject?id=<%=i.getId()%>">Delete</a>
		       </td>
	       </tr>
       <% 
       } 
       %>
    </table>

    <a href="insertSubject" >Insert Subject</a>
	<br/>
	<jsp:include page="..//_pagination.jsp"></jsp:include>
	<br/>
    <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>