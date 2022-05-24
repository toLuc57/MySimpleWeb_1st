<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Course</title>
</head>
<body>
	<jsp:include page="..//_header.jsp"></jsp:include>
      <p style="color: red;">${errorString}</p>      
      
      <h3>Insert Course</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/course/insert">
         <table border="0">
            <tr>
               <td>ID</td>
               <td><input type="text" name="id"  /></td>
            </tr>
            <tr>
               <td>ID Subject</td>
               <td>
               <select name="idSubject">
	               <%
	               List<String> list = (List<String>) request.getAttribute("subjectsList");
	               if(list != null && list.size() != 0){
		               for(String i : list){
		               %>
		                 <option value="<%=i%>"><%=i%></option>
		               <%
		               }
	               } 
	               else {
	               %>
               		Không có mã 
					<%
				   }
					%>
               </select>
               </td>
            </tr>
            <tr>
               <td>ID Teacher</td>
               <td>
               <select name="idTeacher">
	               <%
	               list.clear();
	               list = (List<String>) request.getAttribute("teacherList");
	               if(list != null && list.size() != 0){
		               for(String i : list){
		               %>
		                 <option value="<%=i%>"><%=i%></option>
		               <%
		               }
	               } 
	               else {
	               %>
               		Không có mã
					<%
				   }
					%>
               </select>
               </td>
            </tr>
			<tr>
               <td>From: </td>
               	<td>
               	<input type="number" name="fromDay" min="1" max="31" size="3" 
               	value="${fromDay}" placeholder="day"/>
               	/<input type="number" name="fromMonth" min="1" max="12" size="3" 
               	value="${fromMonth}" placeholder="month"/>
               	/<input type="number" name="fromYear" size="3" placeholder="year"
               	 value="${fromYear}" pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
               <td>To: </td>
               	<td>
               	<input type="number" name="toDay" min="1" max="31" size="3" placeholder="day"
               	value="${toDay}"/>
               	/<input type="number" name="toMonth" min="1" max="12" size="3" placeholder="month"
               	value="${toMonth}" />
               	/<input type="number" name="toYear" size="3" placeholder="year"
               	value="${toYear}" pattern="[1-2][0-9]{3}" required/>
               	</td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="courseList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="..//_footer.jsp"></jsp:include>

</body>
</html>