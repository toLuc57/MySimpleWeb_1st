<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}">
    	<table>
	    <%
	    	List<String> listColumn = (List<String>) request.getAttribute("listColumnName");
			Map<String,String> mapColumn = (Map<String,String>) request.getAttribute("mapColumn");
			int count = 0;
			for(String i : listColumn)
			{
		%>
			<tr>
				<td>
				  <input type="checkBox" id="<%=i%>" name="example" value="<%=i%>">
				  <label for="<%=i%>"><%=++count %></label>
				</td>
				<%
				if(mapColumn.get(i) == "VARCHAR" || mapColumn.get(i) == "CHAR"){
				%>
					<td>
					  <label>Pattern: </label>
					  <input type="text" name="<%=i%>" placeholder="Pattern">
					</td>
				<%
				} 
				else if(mapColumn.get(i) == "INT" || mapColumn.get(i) == "DOUBLE") {
				%>
					<td>
					  <input type="number" name="<%=i%>_start" placeholder="Number">
					</td>
					<td>
					  <input type="number" name="<%=i%>_end" placeholder="Number">
					</td>
				<%
				} else {
				%>
					<td>
					  <input type="text" name="<%=i%>" placeholder="Underfine">
					</td>
				<%	
				}
				%>
			</tr>
		
		<%	
			}
		%>
		</table>
		<input type="submit" value="Submit" />
    </form>
	
	<p>Write something in the text field to trigger a function.</p>
	
	<input type="number" id="myInput" oninput="myFunction()">
	<p id="demo"></p>
	<br/>
	<input type="number" id="myOutput" oninput="myFunction1()">
	<p id="demo1" style="color:red"></p>
	
	<script>
	function myFunction() {
	  var x = document.getElementById("myInput").value;
	  document.getElementById("demo").innerHTML = "You wrote: " + x;
	  document.getElementById("myOutput").min = x;
	}
	function myFunction1() {
	  var x = document.getElementById("myOutput").value;
	  var y = document.getElementById("myOutput").min;
	  if(x < y){
	    document.getElementById("demo1").innerHTML = "You wrote: " + x;
	  }
	  else {
	    document.getElementById('demo1').style.display='none';
	  }
	}
	</script>
</body>
</html>