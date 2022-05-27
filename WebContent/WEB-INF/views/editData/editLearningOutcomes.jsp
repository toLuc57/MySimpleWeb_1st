<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Learning Outcomes</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
           
      <p style="color: red;">${errorString}</p>
      
      <h3>Edit Learning outcomes</h3>
      
      <form method="POST" action="${pageContext.request.contextPath}/learningOutcomes/edit">
         <input type="hidden" name="idStudent" value="${learningOutcomes.idStudent}" />
         <input type="hidden" name="idCourse" value="${learningOutcomes.idCourse}" />
         <input type="hidden" name="numberOfTest" value="${learningOutcomes.idCourse}" />
         <table border="0">
            <tr>
               <td>Point:</td>
               <td><input type="text" name="name" placeholder="${learningOutcomes.point}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/learningOutcomesList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>