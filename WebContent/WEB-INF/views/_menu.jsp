<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="org.hm.SimpleWeb.utils.MyUtils, org.hm.SimpleWeb.beans.UserAccount" %>
<style>
.navigation-bars {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: black;
}
.navigation-bars li {
  float: left;
}

.navigation-bars a, .dropbtn {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}
/* Change the background color of the dropdown button when the dropdown content is shown */
.navigation-bars a:hover, .dropdown:hover .dropbtn {
  background-color: #3e8e41;
}
/* The container <div> - needed to position the dropdown content */
li.dropdown {
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
  text-align: left;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #38444d; color: white;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
  display: block;
}
</style>
	<%
	UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());
	%>
   <ul class="navigation-bars">
     <li>
       <a href="${pageContext.request.contextPath}/">Home</a>
     </li>
     <li>
	   <a href="${pageContext.request.contextPath}/signup">Sign Up</a>
	 </li>
     <li class="dropdown" >
	   <a class="dropbtn">Drop down</a>
	   <div class="dropdown-content">
	       <a href="${pageContext.request.contextPath}/teacherList">Teacher List</a>
		   <a href="${pageContext.request.contextPath}/departmentList">Department List</a>
		   <a href="${pageContext.request.contextPath}/studentList">Student List</a>
		   <a href="${pageContext.request.contextPath}/subjectList">Subject List</a>
		   <a href="${pageContext.request.contextPath}/courseList">Course List</a>
		   <a href="${pageContext.request.contextPath}/learningOutcomesList">Learning Outcomes List</a>
	   </div>
   	</li>
     
     <%
     if(loginedUser != null){
    	 if(loginedUser.getIsStudent()){
    	     %>
    	    	<li>
    	       		<a href="${pageContext.request.contextPath}/CourseRegistration">Course Registration</a>
    	     	</li>
    	     <%
    	     }
    	     else if(!loginedUser.getIsTeacher()){
    	     %>
    	    	<li>
    	        	<a href="${pageContext.request.contextPath}/signup">Sign Up</a>
    	       	</li>
    	     <%
    	     }
    	     %>
    		<li class="dropdown" style="float:right;">
    		 	  <a class="dropbtn" title="Your personal page">Your personal page</a> 
    		 	  <div class="dropdown-content" style="right:0;">
    		 	    <a href="${pageContext.request.contextPath}/userInfo">Your Account Info</a>
    		  		<a href="${pageContext.request.contextPath}/logout">Logout</a>
    		 	  </div>
    		 </li> 
    <%
     }
    %>
   </ul>