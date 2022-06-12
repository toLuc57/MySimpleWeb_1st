<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Student, 
org.hm.SimpleWeb.utils.StudentDBUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Student</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSForm.css">
</head>
<body>
	  <jsp:include page="..//_header.jsp"></jsp:include>
      <jsp:include page="..//_menu.jsp"></jsp:include>
      
      <h3>Insert Student</h3>
      
      <p style="color: red;">${errorString}</p>

      <form method="POST" action="${pageContext.request.contextPath}/student/insert">
         <table border="0">
         	<tr>
               <td>Grade: </td>
               <td>
                 <select name="grade">

                   <option value="<%=StudentDBUtils.getNameGrade8() %>">Grade 8 </option>
                   <option value="<%=StudentDBUtils.getNameGrade9() %>">Grade 9 </option>
                 </select>
               </td>
            </tr>
            <tr>
               <td>Last Name</td>
               <td><input type="text" name="lastName" value="${student.lastName}" /></td>
            </tr>
            <tr>
               <td>First Name</td>
               <td><input type="text" name="firstName" value="${student.firstName}" /></td>
            </tr>
            <tr>
               	<td>Birthday: ${student.birthday}</td>
               	<td>
               	<input type="number" name="day" min="1" max="31" 
               	value="${day}" size="3" placeholder="day" />
               	/<input type="number" name="month" min="1" max="12"
               	value="${month}" size="3" placeholder="month" />
               	/<input type="number" name="year" size="3" min="1990" max="2015" 
               	value="${year}" placeholder="year" />               	
               	</td>
            </tr>
            <tr>
               <%
            	Student obj = (Student) request.getAttribute("student");
                if(obj == null){
                	%>
                	<tr>
                  		<td rowspan="3">Sex</td>
                  		<td><input type="radio" name="sex" value="Male" checked />Male</td>
               		</tr>
               		<tr>
                  		<td><input type="radio" name="sex" value="Female" />Female</td>
               		</tr>
               		<tr>
                  		<td><input type="radio" name="sex" value="Other" />Other</td>
               		</tr>
                 <%
                }
         		else if (obj.getSex().equals("Nam") || obj.getSex().equals("Male")){
            %>
            	<tr>
              		<td rowspan="3">Sex</td>
              		<td><input type="radio" name="sex" value="Male" checked/>Male</td>
           		</tr>
           		<tr>
              		<td><input type="radio" name="sex" value="Female" />Female</td>
           		</tr>
           		<tr>
              		<td><input type="radio" name="sex" value="Other" />Other</td>
           		</tr>
             <%
             	}
              	else if (obj.getSex().equals("Nữ")  || obj.getSex().equals("Female")){
           	 %>
                <tr>
                	<td rowspan="3">Sex</td>
                  	<td><input type="radio" name="sex" value="Male"/>Male</td>
               	</tr>
               	<tr>
                  	<td><input type="radio" name="sex" value="Female" checked/>Female</td>
               	</tr>
               	<tr>
                  	<td><input type="radio" name="sex" value="Other" />Other</td>
               	</tr>
             <%
              	}
              	else {
              %>
                <tr>
                  	<td rowspan="3">Sex</td>
                  	<td><input type="radio" name="sex" value="Male"/>Male</td>
               	</tr>
               	<tr>
                  	<td><input type="radio" name="sex" value="Female"/>Female</td>
               	</tr>
               	<tr>
                  	<td><input type="radio" name="sex" value="Other" checked/>Other</td>
               	</tr>
              <%
              	}
              %>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" value="${student.telephone}" /></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address" value="${student.address}" /></td>
            </tr>
            <tr>
               <td>Id Department</td>
               <td>
               
	               <%
	               Student insertStudent= (Student) request.getAttribute("student");
	               List<String> list = (List<String>) request.getAttribute("departmentList");
	               if(list != null && list.size() != 0 && !list.isEmpty()){
	            	   %>
	            	   <select name="idDepartment">
	            	   <%
		               for(String i : list){
		            	   if(insertStudent == null || insertStudent.getIdDepartment() != i){
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
                   <a href="${pageContext.request.contextPath}/studentList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>
</body>
</html>