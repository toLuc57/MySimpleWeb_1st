<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
	<jsp:include page="..//_menu.jsp"></jsp:include>
      
      

      <h3>Edit Student</h3>
      <p style="color: red;">${errorString}</p>
      <form method="POST" action="${pageContext.request.contextPath}/student/edit">
         <input type="hidden" name="id" value="${student.id}" />
         <table border="0">
         	<tr>
         		<td>ID: </td>
         		<td><output name="id">${student.id}</output></td>
         	</tr>
            <tr>
               <td>Last Name</td>
               <td><input type="text" name="lastName" placeholder="${student.firstName}" /></td>
            </tr>
            <tr>
               <td>First Name</td>
               <td><input type="text" name="firstName" placeholder="${student.lastName}" /></td>
            </tr>
            <tr>
               	<td>Birthday:</td>
               	<td>
               	<input type="number" name="day" min="1" max="31" size="3" 
               	placeholder="${day}" />
               	/<input type="number" name="month" min="1" max="12" size="3" 
               	placeholder="${month}"/>
               	/<input type="number" name="year" size="3" placeholder="${year}"
               	pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>           
            <%
            	Student obj = (Student) request.getAttribute("student");
            	if (obj.getSex().equals("Nam")){
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
              	else if (obj.getSex().equals("Nữ")){
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
            
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" placeholder="${student.telephone}" /></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address" placeholder="${student.address}" /></td>
            </tr>
            <tr>
               <td>Id Department</td>
               <td>
	               <%
	               Student insertStudent= (Student) request.getAttribute("student");
	               List<String> list = (List<String>) request.getAttribute("departmentList");
	               if(list != null && list.size() != 0){
	            	   %>
	            	   <select name="idDepartment">
	            	   <%
		               for(String i : list){
		            	   if(insertStudent == null || !insertStudent.getIdDepartment().equals(i)){
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