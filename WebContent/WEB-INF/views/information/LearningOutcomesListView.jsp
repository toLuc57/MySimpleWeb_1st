<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.LearningOutcomes" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LearningOutcomes List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSTable.css">
</head>
<body>
<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>

    <h3>Learning Outcomes List</h3>

    <p style="color: red;">${errorString}</p>
	<br/>
	<jsp:include page="..//_search.jsp"></jsp:include>
	<br/>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id Student</th>
          <th>Id Course</th>
          <th>Number of test</th>
          <th>Point</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <%
       List<LearningOutcomes> list = (List<LearningOutcomes>) request.getAttribute("learningOutcomesList");	
       for(LearningOutcomes i : list){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getIdStudent() %></td>
			   <td><%= i.getIdCourse()%></td>
			   <td><%= i.getNumberOfTest() %></td>
			   <td><%= i.getPoint() %></td>
			   <td>
		          <a href="learningOutcomes/edit?idStudent=<%=i.getIdStudent()%>&idCourse=<%=i.getIdCourse()%>&numberOfTest=<%=i.getNumberOfTest()%>">
		          Edit</a>
		       </td>
		       <td>
		      	  <a href="learningOutcomes/delete?idStudent=<%=i.getIdStudent()%>&idCourse=<%=i.getIdCourse()%>&numberOfTest=<%=i.getNumberOfTest()%>">
		      	  Delete</a>
		       </td>
	       </tr>
       <% 
       } 
       %>
    </table>

    <a href="learningOutcomes/insert" >Insert LearningOutcomes</a>
	<br/>
	<jsp:include page="..//_pagination.jsp"></jsp:include>
	<br/>
    <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>