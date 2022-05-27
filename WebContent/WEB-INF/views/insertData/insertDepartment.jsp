<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Department</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
    <p style="color: red;">${errorString}</p> 
    <h3>Insert Department</h3>
	<form method="POST" action="${pageContext.request.contextPath}/department/insert">
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