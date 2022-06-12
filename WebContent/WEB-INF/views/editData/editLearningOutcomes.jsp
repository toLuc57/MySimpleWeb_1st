<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Learning Outcomes</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSForm.css">
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
	<jsp:include page="..//_menu.jsp"></jsp:include>
	
	<h3>Edit Learning outcomes</h3>
      <p style="color: red;">${errorString}</p>

      <form method="POST" action="${pageContext.request.contextPath}/learningOutcomes/edit">
         <input type="hidden" name="idStudent" value="${learningOutcomes.idStudent}" />
         <input type="hidden" name="idCourse" value="${learningOutcomes.idCourse}" />
         <input type="hidden" name="numberOfTest" value="${learningOutcomes.numberOfTest}" />
         <table border="0">
            <tr>
               <td>Point:</td>
               <td><input type="number" name="point" step="0.25" min="0" max="20"
               value="${learningOutcomes.point}" /></td>
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