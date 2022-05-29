<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.hm.SimpleWeb.utils.MyUtils" %>
<style>
.top-header {
	display: block;
    padding: 8.5px 0;
    border-bottom: 1px solid #e5e5e5;
    width: 100%;
}
</style>

<header class="top-header">
<div style="background: #E0E0E0; height: 65px; padding: 5px;">
  <div style="float:left">
 	 <a href="/SimpleWeb">
  	   <img src="${pageContext.request.contextPath}/images/logo-home.jpg" 
  	   alt="Trang chu" width="200" height="65">
  	 </a>
  	 <span style="font-size:40px">My Site</span>
  </div>
  <div style="float: right; padding: 10px; text-align: right;">
     <!-- User store in session with attribute: loginedUser -->
     Hello <b>${loginedUser.userName}</b>
   	<br/>
     Search <input name="search" placeholder="Search">
     <br/>
     <%
	 if(MyUtils.getLoginedUser(request.getSession()) != null){
	 %>
	 	<a href="${pageContext.request.contextPath}/logout">Logout</a>
     <% 
     }
	 else {
	 %>
	 	<a href="${pageContext.request.contextPath}/login">Login</a>
	 <%
	 }
     %>
  </div>
</div>
</header>
