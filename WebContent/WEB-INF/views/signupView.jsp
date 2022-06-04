<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Set, org.hm.SimpleWeb.utils.UserAccountDBUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Sign Up (Only Admin)!</h3>
	<%
	Set<String> listUserName = UserAccountDBUtils.getAllUserName();
	if(listUserName != null){
		for(String i : listUserName){
	%>
		<input type="hidden" class="listUserName" value="<%=i%>">
	<%
		}
	}
	%>
	<form method="POST" action="${pageContext.request.contextPath}/signup">
         <table border="0">
            <tr>
               <td>User Name</td>
               <td>
               	<input type="text" id="userName" name="userName" 
               		oninput="checkUserName()" value="${userName}" /> 
              	<br/>
               	<span id="errorUserName" style="color:red;"></span>
               	<br/>
               </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" id="password" name="password" value= "${password}" /> </td>
            </tr>
            <tr>
               <td>Confirm Password</td>
               <td>
               	<input type="text" id="confirmPassword" name="confirmPassword" 
               	oninput="checkPassword()" /> 
               	<br/>
               	<span id="errorNotEqualsPassword" style="color:red;"></span>
               	<br/>
               </td>
            </tr>
            <tr>
         		<td> ID: </td>
         		<td> 
         			<input type="text" name="id" value="${id }">
         		</td>
         	</tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value="Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
     <jsp:include page="_footer.jsp"></jsp:include>
     <script>
    const listUserName = new Set();
    var x = document.getElementsByClassName("listUserName");
    for(var i = 0; i < x.length; ++i)
    {
    	listUserName.add(x[i].value);
    }

	function checkUserName(){
		 var username = document.getElementById("userName");
		  if(listUserName.has(username.value))
		  {
		    document.getElementById("errorUserName").innerHTML = "Account already exists";
		  } 
		  else {
		    document.getElementById("errorUserName").innerHTML = "";
		  }
	}
	function checkPassword(){
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		  if(password == confirmPassword)
		  {
		    document.getElementById("errorNotEqualsPassword").innerHTML = "";
		  } 
		  else {
		    document.getElementById("errorNotEqualsPassword").innerHTML = 
		    	"Confirm password does not match the password";
		  }
	}
     </script>
</body>
</html>