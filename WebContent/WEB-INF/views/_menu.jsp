<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<style>
.dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #f1f1f1}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
  display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
  background-color: #3e8e41;
}
</style>
<div style="padding: 5px;" >

   <a href="${pageContext.request.contextPath}/">Home</a>
   |
   <a href="${pageContext.request.contextPath}/userinfo">My Account Info</a>
   |
   <a href="${pageContext.request.contextPath}/login">Login</a>
   |
   <div class="dropdown">
	   <a href="">Dropdown</a>
	   <div class="dropdown-content">
	       <a href="${pageContext.request.contextPath}/teacherList">Teacher List</a>
		   <a href="${pageContext.request.contextPath}/departmentList">Department List</a>
		   <a href="${pageContext.request.contextPath}/studentList">Student List</a>
		   <a href="${pageContext.request.contextPath}/subjectList">Subject List</a>
		   <a href="${pageContext.request.contextPath}/courseList">Course List</a>
		   <a href="${pageContext.request.contextPath}/learningOutcomesList">Learning Outcomes List</a>
	   </div>
   </div>
   
   
</div>  