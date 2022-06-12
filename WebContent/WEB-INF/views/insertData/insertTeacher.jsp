<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Teacher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertTeacher</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSForm.css">
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <jsp:include page="..//_menu.jsp"></jsp:include>
      
      <h3>Insert Teacher</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/teacher/insert">
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${teacher.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" value="${teacher.teplephone}" /></td>
            </tr>
            <tr>
               <td>Degree</td>
               <td><input type="text" name="degress" value="${teacher.degree}" /></td>
            </tr>
            <tr>
               <td>ID Department</td>
               <td>
	               <%
	               Teacher insertTeacher = (Teacher) request.getAttribute("student");
	               List<String> list = (List<String>) request.getAttribute("departmentList");
	               if(list != null && list.size() != 0 && !list.isEmpty()){
	            	   %>
	            	   <select name="idDepartment">
	            	   <%
		               for(String i : list){
		            	   if(insertTeacher == null || insertTeacher.getIdDepartment() != i){
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