<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Khoa" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <jsp:include page="..//_menu.jsp"></jsp:include>
      
      <h3>Insert Teacher</h3>
      
      <p style="color: red;">${errorString}</p>
      
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Address</th>
          <th>Telephone</th>

       </tr>
       <%
       List<Khoa> list = (List<Khoa>) request.getAttribute("departmentList");	
       for(Khoa i : list){    	   
	   %>
	   	   <tr>
		   	   <td><%= i.getId() %></td>
			   <td><%= i.getName() %></td>
			   <td><%= i.getAddress() %></td>
			   <td><%= i.getTelephone() %></td>
	       </tr>
       <% 
       } 
       %>
  	</table>
      
      </br>
      
      <form method="POST" action="${pageContext.request.contextPath}/insertTeacher">
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${k.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" value="${k.teplephone}" /></td>
            </tr>
            <tr>
               <td>HocVi</td>
               <td><input type="text" name="degress" value="${k.degree}" /></td>
            </tr>
            <tr>
               <td>IDKhoa</td>
               <td><input type="text" name="idDepartment" value="${k.idDepartment}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="teacherList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>