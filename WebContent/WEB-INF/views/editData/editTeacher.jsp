<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Teacher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Teacher</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
           
      <p style="color: red;">${errorString}</p>

      <h3>Edit Teacher</h3>
      
      <form method="POST" action="${pageContext.request.contextPath}/teacher/edit">
         <input type="hidden" name="id" value="${teacher.id}" />
         <table border="0">
         	<tr>
         		<td>ID: </td>
         		<td><output name="id">${teacher.id}</output></td>
         	</tr>
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" placeholder="${teacher.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" placeholder="${teacher.telephone}" /></td>
            </tr>
            <tr>
               <td>Degree</td>
               <td><input type="text" name="degree" placeholder="${teacher.degree}" /></td>
            </tr>
            <tr>
               <td>Id Department</td>
               <td>
               
	               <%
	               Teacher insertTeacher = (Teacher) request.getAttribute("student");
	               List<String> list = (List<String>) request.getAttribute("departmentList");
	               if(list != null && list.size() != 0){
	            	   %>
	            	   <select name="idDepartment">
	            	   <%
		               for(String i : list){
		            	   if(insertTeacher == null || !insertTeacher.getIdDepartment().equals(i)){
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
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/teacherList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>