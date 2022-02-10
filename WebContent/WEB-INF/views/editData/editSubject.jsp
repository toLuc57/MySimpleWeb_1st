<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Subject</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      
      <h3>Edit Subject</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/editSubject">
         <input type="hidden" name="id" value="${subject.id}" />
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" placeholder="${subject.name}" /></td>
            </tr>
            <tr>
               <td>Practice Lesson</td>
               <td><input type="text" name="practiceLesson" placeholder="${subject.practiceLesson}" /></td>
            </tr>
            <tr>
               <td>Theory Lesson</td>
               <td><input type="text" name="theoryLesson" placeholder="${subject.theoryLesson}" /></td>
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