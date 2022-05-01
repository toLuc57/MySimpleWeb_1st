<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.hm.SimpleWeb.utils.MyUtils" %>

<div style="background: #E0E0E0; height: 65px; padding: 5px;">
  <div style="float: left">
     <h1>My Site</h1>
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
    	<a href="logout">Logout</a>
     <% 
     }
     %>
     
  </div>
</div>