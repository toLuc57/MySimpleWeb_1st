<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.hm.SimpleWeb.utils.MyUtils, org.hm.SimpleWeb.beans.UserAccount" %>
<h3> Search </h3>
 <%
 UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());
 if(loginedUser == null){
	 %>
	 <jsp:include page="_searchOnlyIDAndName.jsp"></jsp:include>
	 <%
 } 
 else if (loginedUser.getIsStudent() || loginedUser.getIsTeacher()){
	 %>
	 <jsp:include page="_searchOnlyIDAndName.jsp"></jsp:include>
	 <%
 }
 else {
	 %>
	 <jsp:include page="_searchByAdmin.jsp"></jsp:include>
	 <%
 }
 %>