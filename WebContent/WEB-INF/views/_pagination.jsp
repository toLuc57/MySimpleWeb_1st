<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<style>
	.pagination{
		list-style-type: none;
		float: right;
	}
	.pagination li{
		display: inline-block;
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
	<form href="post">
		Number of page:			
		<input type="number" name="inputPage" min="0" max="<%= totalPages %>>" size="3" 
			placeholder="<%=indexPage %>" >
		<input type="submit" value="Search" />
	</form>
</div>
<br/>
<div>
  <ul class="pagination">
	<li>
	  <a href="#?page=0" > &laquo;</a>
    <li>
		<%
			if(indexPage <= 5){
				for(int i = 0; i < 5; ++i){
		    	   if (indexPage == i){
		    		%>
	   		 			<li><a class="active"><%=i%></a></li>
			 		<%
		    	   }
		    	   else {
	 		   		%>
	    		 		<li><a href="?page=<%=i%>"><%=i%></a></li>
	 		 		<%
		    	   }
				}
	     	} 
			else {
				for(int i = indexPage - 2; i < indexPage; ++i){
		   		%>
	  		 		<li>
	  		 		  <a href="?page=<%=i%>"><%=i%></a>
	  		 		</li>
		 		<%
				}
				%> 
					<li><a class="active"><%=indexPage%></a></li>
				<%
				for(int i = indexPage; i < totalPages && i < indexPage + 2 ; ++i){
			   		%>
		  		 		<li>
		  		 		  <a href="?page=<%=i%>"><%=i%></a>
		  		 		</li>
			 		<%
				}
	       }
	 	%>
		<li>
		  <a href="?page=<%=totalPages%>">&raquo;</a>
    </li>
  </ul>
</div>
	