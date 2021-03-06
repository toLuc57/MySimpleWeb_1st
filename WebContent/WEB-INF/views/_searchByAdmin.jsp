<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.Map"%>

   <%
   	List<String> listColumn = (List<String>) request.getAttribute("listColumnName");
	Map<String,String> mapColumn = (Map<String,String>) request.getAttribute("mapColumn");
	int count = 0;
	if(listColumn != null && !listColumn.isEmpty()){
	%>
	  <form method="get" action="${nameURI}">
      <table>
	<%
		for(String i : listColumn)
		{
	%>
		<tr>
			<td>
			  <label for="<%=i%>">Column <%=++count %></label>
			</td>
			<%
			System.out.println(mapColumn.get(i));
			if(mapColumn.get(i) == "VARCHAR" || mapColumn.get(i) == "CHAR" ){
				String text = request.getParameter(i);
				if(text == null){
				%>
					<td>
					  Pattern:
					  <input type="text" name="<%=i%>" placeholder="Pattern">
					</td>
				<%
				}
				else {
				%>
					<td>
					  Pattern:
					  <input type="text" name="<%=i%>" placeholder="Pattern" value="<%=text%>">
					</td>
				<%
				}
			} 
			else if(mapColumn.get(i) == "INT" || mapColumn.get(i) == "DOUBLE") {
				String fromValue = request.getParameter(i.concat("_start"));
				String toValue = request.getParameter(i.concat("_end"));
				if (fromValue == null){
				%>
				<td>
				  From 
				  <input type="number" name="<%=i%>_start" 
				  	placeholder="Number">
				</td>
				<%
				}
				else {
				%>
				<td>
				  From 
				  <input type="number" name="<%=i%>_start" 
				  	placeholder="Number" value=<%=fromValue%>>
				</td>
				<%
				}
				if (toValue == null){
				%>
					<td>
					  To 
					  <input type="number" name="<%=i%>_end" 
					  placeholder="Number">
					</td>
				<%
				} 
				else {
					%>
					<td>
					  To 
					  <input type="number" name="<%=i%>_end" 
					  placeholder="Number" value=<%=toValue %>>
					</td>
				<%
				}
			}
			else if(mapColumn.get(i) == "DATE" || mapColumn.get(i) == "DATETIME") {
				String fromValue = request.getParameter(i.concat("_start"));
				String toValue = request.getParameter(i.concat("_end"));
				if (fromValue == null){
				%>
				<td>
				  From 
				  <input type="date" name="<%=i%>_start" 
				  	placeholder="Date (yyyy-mm-dd)">
				</td>
				<%
				}
				else {
				%>
				<td>
				  From 
				  <input type="date" name="<%=i%>_start" 
				  	placeholder="Date (yyyy-mm-dd)" value=<%=fromValue%>>
				</td>
				<%
				}
				if (toValue == null){
				%>
					<td>
					  To 
					  <input type="date" name="<%=i%>_end" 
					  placeholder="Date (yyyy-mm-dd)">
					</td>
				<%
				} 
				else {
					%>
					<td>
					  To 
					  <input type="date" name="<%=i%>_end" 
					  placeholder="Date (yyyy-mm-dd)" value=<%=toValue %>>
					</td>
				<%
				}
			}
			else {
			%>
				<td>
				  Underfined
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
	<%
	}
	%>			