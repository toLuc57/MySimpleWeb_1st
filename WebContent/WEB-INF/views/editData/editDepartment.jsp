<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Department</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      
      <h3>Edit Department</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/editDepartment">
         <input type="hidden" name="id" value="${department.id}" />
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" placeholder="${department.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" placeholder="${department.telephone}" /></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address" placeholder="${department.address}" /></td>
            </tr>

            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="departmentList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>