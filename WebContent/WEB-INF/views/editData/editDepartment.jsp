<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Department</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
	<jsp:include page="..//_menu.jsp"></jsp:include>
      
      <h3>Edit Department</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/department/edit">
         <input type="hidden" name="id" value="${department.id}" />
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${department.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" value="${department.telephone}" /></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address" value="${department.address}" /></td>
            </tr>

            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/departmentList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>