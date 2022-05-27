<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.LearningOutcomes" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Learning Outcomes</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
           
      <p style="color: red;">${errorString}</p>
      
      <h3>Insert Learning outcomes</h3>
      
      <form method="POST" action="${pageContext.request.contextPath}/learningOutcomes/insert">
         <table border="0">
         	<tr>
         		<td>ID student:</td>
         		<td>
         		
	               <%
	               List<String> list = (List<String>) request.getAttribute("studentList");
	               LearningOutcomes insertLearningOutcomes = 
	            		   (LearningOutcomes) request.getAttribute("learningOutcomes");
	               if(list != null && list.size() != 0 && !list.isEmpty()){
	            	   for(String i : list){
	            		   %>
	            		   <select name="idStudent">
	            		   <%
		            	   if(insertLearningOutcomes == null || 
		            			   !insertLearningOutcomes.getIdStudent().equals(i)){
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
               <td>ID course:</td>
               <td>
               
	               <%
	               list.clear();
	               list = (List<String>) request.getAttribute("courseList");
	               if(list != null && list.size() != 0 && !list.isEmpty()){
	            	   %>
	            	   <select name="idCourse">
	            	   <%
	            	   for(String i : list){
	            		   if(insertLearningOutcomes == null || 
		            			   !insertLearningOutcomes.getIdCourse().equals(i)){
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
               <td>Point:</td>
               <td><input type="number" name="point" min="0" max="10" step="0.25"/></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/learningOutcomesList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>