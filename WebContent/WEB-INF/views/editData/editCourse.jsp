<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Course</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSForm.css">
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <jsp:include page="..//_menu.jsp"></jsp:include>
      
      <h3>Edit Course</h3>
      
      <p style="color: red;">${errorString}</p>      
            
      <form method="POST" action="${pageContext.request.contextPath}/course/edit">
         <input type="hidden" name="id" value="${course.getIdCourse() }" />
         <table border="0">
            <tr>
               <td>ID Subject</td>
               <td>
	               <%
	               Course editCourse = (Course) request.getAttribute("course");
	               List<String> list1 = (List<String>) request.getAttribute("subjectsList");
	               if(list1 != null && list1.size() != 0){
	            	   %>
	            	   <select name="idSubject">
	            	   <%
		               for(String i : list1){
		            	   if(editCourse == null || editCourse.getIdSubject() != i){
		            		   %>
				                 <option value="<%=i%>"><%=i%></option>
				               <%
		            	   }
		            	   else {
		            		   %>
				                 <option value="<%=i%>" selected><%=i%></option>
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
	               List<String> list2 = (List<String>) request.getAttribute("teacherList");
	               if(list2 != null && list2.size() != 0){
	            	   %>
	            	   <select name="idTeacher">
	            	   <%
		               for(String i : list2){
		            	   if(editCourse == null || editCourse.getIdTeacher() != i){
		            		   %>
				                 <option value="<%=i%>"><%=i%></option>
				               <%
		            	   }
		            	   else {
		            		   %>
				                 <option value="<%=i%>" selected><%=i%></option>
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
               	value="${fromDay}"/>
               	/<input type="number" name="fromMonth" min="1" max="12" size="3" 
               	value="${fromMonth}"/>
               	/<input type="number" name="fromYear" size="3"
               	 value="${fromYear}" pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
               <td>To (dd/MM/yyyy): </td>
               	<td>
               	<input type="number" name="toDay" min="1" max="31" size="3" value="${toDay}"/>
               	/<input type="number" name="toMonth" min="1" max="12" size="3" value="${toMonth}"/>
               	/<input type="number" name="toYear" size="3"
               	value="${toYear}" pattern="[1-2][0-9]{3}" required/>
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