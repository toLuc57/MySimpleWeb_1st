<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<style>
	.pagination{
		list-style-type: none;
		display: inline-block;
		padding: 0;
		margin: 8px 0;
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
	if (request.getAttribute("totalRow") == null){
		return;
	}
    int totalPages = (int) request.getAttribute("totalRow")/10;
 	int indexPage;
 	try{
 		indexPage = (int) request.getAttribute("page");
 		//System.out.println("Index page(_pagination.jsp): "+ indexPage);
 		
 	}catch(NumberFormatException e){
 		indexPage = 0;
 	}
 	String queryString = (String) request.getAttribute("queryStringInUrl");
 	//System.out.println(queryString);
 	if(queryString == null || queryString.isEmpty() || queryString.length() == 0){
 		queryString = "?page=";
 	}
 	else if(queryString.equals("?")){
 		queryString = queryString.concat("page=");
 	}
 	else {
 		queryString = queryString.concat("&page=");
 	}
 	
%>
<div style="text-align:right">Total pages: <%=totalPages %></div>
<div style="text-align:right;margin: 10px">
	<form method="get">
		Number of page:			
		<input type="number" name="page" min="0" max="<%= totalPages %>>" size="3" step="1"
			value="<%=indexPage %>" >
		<input type="submit" value="Search" />
	</form>
</div>
<div style="text-align:right">
  <ul class="pagination">
	<li>
	  <a href="<%=queryString.concat("0") %>" > &laquo;</a>
    <li>
		<%
			if(indexPage < 4){
				for(int i = 0; i < 5; ++i){
		    	   if (indexPage == i){
		    		%>
	   		 			<li><a class="active"><%=i%></a></li>
			 		<%
		    	   }
		    	   else {
	 		   		%>
	    		 		<li><a href="<%=queryString.concat(String.valueOf(i))%>"><%=i%></a></li>
	 		 		<%
		    	   }
				}
	     	} 
			else {
				for(int i = indexPage - 2; i < indexPage; ++i){
		   		%>
	  		 		<li>
	  		 		  <a href="<%=queryString.concat(String.valueOf(i))%>"><%=i%></a>
	  		 		</li>
		 		<%
				}
				%> 
					<li><a class="active"><%=indexPage%></a></li>
				<%
				for(int i = indexPage; i < totalPages && i < indexPage + 2 ; ++i){
			   		%>
		  		 		<li>
		  		 		  <a href="<%=queryString.concat(String.valueOf(i))%>"><%=i%></a>
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
	