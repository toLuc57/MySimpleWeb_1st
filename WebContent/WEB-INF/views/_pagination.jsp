<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.hm.SimpleWeb.beans.Course" %>
<style>
	.pagination{
		display: inline-block;
		float: right;
		padding: 8px 16px;
		margin: 10px;
	}
	.pagination a{
		color: black;
		float: left;
		padding: 8px 16px;
		margin: 0px 4px;
		text-decoration: none;
	}
	.pagination a.active{
		background-color: green;
		color: white;
	}
	.pagination a:hover:not(.active){
		background-color:gray;
		text-decoration: underline;
	}
</style>
 <br/>
<%
    int totalPages = (int) request.getAttribute("totalRow")/10;
 	int indexPage;
 	try{
 		indexPage = (int) request.getAttribute("page");
 		System.out.println("Index page(_pagination.jsp): "+ indexPage);
 		
 	}catch(NumberFormatException e){
 		indexPage = 0;
 	}
%>
<div style="float:right">Total pages: <%=totalPages %></div>
<br/>
<div style="float:right;margin: 10px">
	<form method="POST"">
		Number of page:			
		<input type="number" name="inputPage" min="0" max="<%= totalPages %>>" size="3" 
			placeholder="<%=indexPage %>" >
		<input type="submit" value="Search" />
	</form>
</div>
<br/>
<div>
	<div class="pagination">
		<a href="#">&laquo; </a>
		<%
			if(indexPage <= 5){
				for(int i = 0; i < 5; ++i){
		    	   if (indexPage == i){
		    		%>
	   		 			<a class="active"><%=i%></a>
			 		<%
		    	   }
		    	   else {
	 		   		%>
	    		 		<a href="?page=<%=i%>"><%=i%></a>
	 		 		<%
		    	   }
				}
	     	} 
			else {
				for(int i = 0; i < 5; ++i){
		   		%>
	  		 		<a href="?page=<%=i%>"><%=i%></a>
		 		<%
				}
				%> 
					<a class="active" href=""><%=indexPage%></a>
				<%
	       }
	 	%>
		<a href="#">&raquo;</a>
	</div>
</div>
