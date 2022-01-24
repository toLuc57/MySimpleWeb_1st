<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
    <jsp:include page="..//_menu.jsp"></jsp:include>
    
	<form method="POST" action="${pageContext.request.contextPath}/insertDepartment">
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${k.name}" /></td>
            </tr>
            <tr>
               <td>Telephone</td>
               <td><input type="text" name="telephone" value="${k.telephone}" /></td>
            </tr>
            <tr>
               <td>HocVi</td>
               <td><input type="text" name=degress value="${k.degress}" /></td>
            </tr>
            <tr>
               <td>IDKhoa</td>
               <td><input type="text" name="idDepartment" value="${k.idDepartment}" /></td>
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