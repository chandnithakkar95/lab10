<%@page import="Bean.Cart"%>
<%@page import="dao.CartItemDAO"%>
<%@page import="dao.InventoryDAO"%>
<%@page import="Bean.CartItem"%>
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
        <h3>Cart Items</h3>
       <form action="Controller" method="post">
       <table class="gridtable">
            <tr><th class="gridtable">Item Code</th><th>Item Name</th><th>Qty</th><th>Price</th><th>category</th><th></th><th></th></tr>
        <%
        CartItemDAO crtdao=null;
        try{
			 crtdao=new CartItemDAO();
        	InventoryDAO invdao=new InventoryDAO();
            CartItem[] items =(CartItem[]) request.getAttribute("cartitems");
                for(int i=0;i<items.length;i++) { 
                	InventoryItem item=invdao.getItem(items[i].getCartItemCode());
            %>
            <tr>
                <td><%= item.getCode() %></a></td>
                <td><%= item.getDescription() %></td>
                <td><input type="text" value="1" %></td>
                <td><%= item.getCost() %></td>
                <td><a href="<%=application.getContextPath() %>/Controller?action=update&itmCode=<%= item.getCode() %>">update Cart</a></td>
            </tr>                
            <%}
        }catch(NullPointerException e){
            out.println("<tr><td colspan=\"6\">No Items Found</td></tr>");	
        }
        %>
       </table>
       
       <% Cart crt=(Cart)session.getAttribute("cart");%>
       <%=crtdao.totalInventoryCost(crt.getCart()) %>
       <input type="hidden" name="action" value="payment">
       <button type="submit" > Make Payment</button>
       </form>
    </body>
</html>
