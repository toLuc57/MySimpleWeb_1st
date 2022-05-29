<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.util.List, org.hm.SimpleWeb.beans.UserAccount,
 org.hm.SimpleWeb.beans.ResultOfStudentsView"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    <br/>
    <label>User Name: </label>
    <output>${user.userName}</output>
    <br/>
    <%
    	UserAccount user = (UserAccount) request.getAttribute("user");
    	if(user.getIsStudent()){
    		List<ResultOfStudentsView> listInfoStudent = 
    				(List<ResultOfStudentsView>) request.getAttribute("listInfoStudent");
    	%>
    	<p>Id Student: ${user.getID()}</p>
    	<table border="1" cellpadding="5" cellspacing="1" >
	    <tr>
	    	<th>ID Subject</th>
	    	<th>Subject Name</th>
	    	<th>Theory Lesson</th>
	    	<th>Practice Lesson</th>
	    	<th>Number of test</th>
	    </tr>
	    	<%
	    	if(listInfoStudent != null && !listInfoStudent.isEmpty()){
	    		for(ResultOfStudentsView i : listInfoStudent){
	    	    	%>
	    	    		<tr>
	    	    		  <td><%=i.getIdSubject() %></td>
	    	    		  <td><%=i.getSubjectName() %></td>
	    	    		  <td><%=i.getNumberOfTheoryLesson() %></td>
	    	    		  <td><%=i.getNumberOfPracticeLesson() %></td>
	    	    		  <td><%=i.getNumberOfTest() %></td>
	    	    		</tr>
	    	    	<%	
	    	    	} 	
	    	}
	    	%>
	 </table>
    	<%
    	}
    	else{
    	%>
    		<p>Id Teacher: ${user.getID()}</p>
    	<%
    	}
    %>    
    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>