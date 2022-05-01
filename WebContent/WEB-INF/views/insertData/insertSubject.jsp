<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Subject</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      
      <h3>Insert Subject</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/subject/insert">
         <input type="hidden" name="id" value="${subject.id}" />
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${subject.name}" /></td>
            </tr>
            <tr>
               <td>Practice Lesson</td>
               <td><input type="text" name="practiceLesson" value="${subject.practiceLesson}" /></td>
            </tr>
            <tr>
               <td>Theory Lesson</td>
               <td><input type="text" name="theoryLesson" value="${subject.theoryLesson}" /></td>
            </tr>

            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="subjectList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>