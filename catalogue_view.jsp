<%@page import="Bean.Category"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="Bean.InventoryItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" type="text/css" href="main.css">
        <title>Inventory Items</title>
    </head>
    <body>
        <h3>Items</h3>
       <form action=Controller method="post">
       <input type="hidden" name="action" value="itemcat"/>
       <select name="cateSelct"> 
		<option value="all">All</option>
		<%CategoryDAO catdao=new CategoryDAO();
		Category categories[]=catdao.getCategories();
		for(int i=0;i<categories.length;i++){
		%> <option value="<%= categories[i].getCatId() %>"><%=categories[i].getCategory() %></option>
		<%}
		%>
	</select>
	<button type="submit">Search</button>
	</form>
       <table class="gridtable">
            <tr><th class="gridtable">Item Code</th><th>Item Name</th><th>Qty</th><th>Price</th><th>category</th><th></th><th></th></tr>
        <%
        try{
            InventoryItem[] items =(InventoryItem[]) request.getAttribute("items");
                for(int i=0;i<items.length;i++) { 
                    int itmCode = items[i].getCode();
            %>
            <tr>
                <td><%= itmCode %></a></td>
                <td><%= items[i].getDescription() %></td>
                <td><%= items[i].getStock() %></td>
                <td><%= items[i].getCost() %></td>
                <%
               
                Category category=catdao.getcategory(items[i].getCateId());
                %>
                <td><%= category.getCategory() %></td>
				<td><a href="<%=application.getContextPath() %>/Controller?action=newitem&itmCode=<%= itmCode %>">add to cart</a></td>
            </tr>                
            <%}
        }catch(NullPointerException e){
            out.println("<tr><td colspan=\"6\">No Items Found</td></tr>");	
        }
        %>
       </table>
    </body>
</html>
