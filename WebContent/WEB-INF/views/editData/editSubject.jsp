<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Subject</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/CSSForm.css">
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <jsp:include page="..//_menu.jsp"></jsp:include>
      <h3>Edit Subject</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/subject/edit">
         <input type="hidden" name="id" value="${subject.id}" />
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${subject.name}" /></td>
            </tr>
            <tr>
               <td>Practice Lesson</td>
               <td><input type="text" name="practiceLesson" value="${subject.getNumberOfPracticeLesson()}" /></td>
            </tr>
            <tr>
               <td>Theory Lesson</td>
               <td><input type="text" name="theoryLesson" value="${subject.getNumberOfTheoryLesson()}" /></td>
            </tr>

            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="${pageContext.request.contextPath}/subjectList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>