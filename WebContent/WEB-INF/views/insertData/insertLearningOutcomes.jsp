<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.LearningOutcomes" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Learning Outcomes</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSForm.css">
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
	<jsp:include page="..//_menu.jsp"></jsp:include>
           
      <h3>Insert Learning outcomes</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/learningOutcomes/insert">
         <table border="0">
         	<tr>
         		<td>ID student:</td>
         		<td>
         		
	               <%
	               List<String> list1 = (List<String>) request.getAttribute("studentList");
	               LearningOutcomes insertLearningOutcomes = 
	            		   (LearningOutcomes) request.getAttribute("learningOutcomes");
	               if(list1 != null && list1.size() != 0 && !list1.isEmpty()){
	            	   %>
            		   <select name="idStudent">
            		   <%
	            	   for(String i : list1){
		            	   if(insertLearningOutcomes == null || 
		            			   insertLearningOutcomes.getIdStudent() != i){
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
	               List<String> list2 = (List<String>) request.getAttribute("courseList");
	               if(list2 != null && list2.size() != 0 && !list2.isEmpty()){
	            	   %>
	            	   <select name="idCourse">
	            	   <%
	            	   for(String i : list2){
	            		   if(insertLearningOutcomes == null || 
		            			   insertLearningOutcomes.getIdCourse()!= i){
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
               <td><input type="number" name="point" min="0" max="20" step="0.25"/></td>
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