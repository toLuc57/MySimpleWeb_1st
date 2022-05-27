<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Course</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <p style="color: red;">${errorString}</p>      
      
      <h3>Edit Course</h3>
            
      <form method="POST" action="${pageContext.request.contextPath}/course/edit">
         <input type="hidden" name="id" value="${course.id}" />
         <table border="0">
            <tr>
               <td>ID Subject</td>
               <td>
               <select name="idSubject">
	               <%
	               Course editCourse = (Course) request.getAttribute("course");
	               List<String> list = (List<String>) request.getAttribute("subjectsList");
	               if(list != null && list.size() != 0){
		               for(String i : list){
		            	   if(editCourse.getIdSubject().equals(i)){
		            		   %>
				                 <option value="<%=i%>" selected><%=i%></option>
				               <%
		            	   }
		            	   else {
		            		   %>
				                 <option value="<%=i%>"><%=i%></option>
				               <%
		            	   }
		               }
	               } 
	               else {
	               %>
               		Không có mã 
					<%
				   }
					%>
               </select>
               </td>
            </tr>
            <tr>
               <td>ID Teacher</td>
               <td>
	               <%
	               list.clear();
	               list = (List<String>) request.getAttribute("teacherList");
	               if(list != null && list.size() != 0){
	            	   %>
	            	   <select name="idTeacher">
	            	   <%
		               for(String i : list){
		            	   if(editCourse.getIdTeacher().equals(i)){
		            		   %>
				                 <option value="<%=i%>" selected><%=i%></option>
				               <%
		            	   }
		            	   else {
		            		   %>
				                 <option value="<%=i%>"><%=i%></option>
				               <%
		            	   }
		               }
	            	   %>
	            	   </select>
	            	   <%
	               } 
	               else {
	               %>
               		Không có mã
					<%
				   }
					%>
               </td>
            </tr>
			<tr>
               <td>From (dd/MM/yyyy): </td>
               	<td>
               	<input type="number" name="fromDay" min="1" max="31" size="3" 
               	placeholder="${fromDay}"/>
               	/<input type="number" name="fromMonth" min="1" max="12" size="3" 
               	placeholder="${fromMonth}"/>
               	/<input type="number" name="fromYear" size="3"
               	 placeholder="${fromYear}" pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
               <td>To (dd/MM/yyyy): </td>
               	<td>
               	<input type="number" name="toDay" min="1" max="31" size="3" placeholder="${toDay}"/>
               	/<input type="number" name="toMonth" min="1" max="12" size="3" placeholder="${toMonth}"/>
               	/<input type="number" name="toYear" size="3"
               	placeholder="${toYear}" pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/courseList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>