<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Course</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSForm.css">
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
	<jsp:include page="..//_menu.jsp"></jsp:include>
	      
      <h3>Insert Course</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/course/insert">
         <table border="0">
            <tr>
               <td>ID Subject</td>
               <td>
	               <%
	               Course insertCourse = (Course) request.getAttribute("course");
	               List<String> list1 = (List<String>) request.getAttribute("subjectsList");
	               if(list1 != null && list1.size() != 0 && !list1.isEmpty()){
	            	   %>
	            	   <select name="idSubject">
	            	   <%
		               for(String i : list1){
		            	   if(insertCourse == null || insertCourse.getIdSubject() != i){
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
               <select name="idTeacher">
	               <%
	               List<String> list2 = (List<String>) request.getAttribute("teacherList");
	               if(list2 != null && list2.size() != 0 && !list2.isEmpty()){
		               for(String i : list2){
		            	   if(insertCourse == null || insertCourse.getIdTeacher() != i){
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
                   <a href="${pageContext.request.contextPath}/courseList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>