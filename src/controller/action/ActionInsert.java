package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Cart;
import Bean.CartItem;
import Bean.InventoryItem;
import controller.Action;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.InventoryDAO;
import util.DAOException;
import util.ItemExists;
import util.ItemNotFound;

public class ActionInsert implements Action
{
	private CartDAO cartdao;
	private InventoryItem item;

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("itmCode");
		
		InventoryDAO items = null;
		cartdao = null;
		CartItemDAO cartitemdao;
		CartItem[] cia=new CartItem[0];
		Cart c;
		//ArrayList<CartItem> cial=new ArrayList<CartItem>();
		HttpSession session=request.getSession();
		try {
			cartitemdao=new CartItemDAO();
			cartdao=new CartDAO();
			items = new InventoryDAO();
			item=items.getItem(Integer.parseInt(isbn));
			c=(Cart)session.getAttribute("cart");
			//cartdao.addCartItem(/*cartitemdao.getCartItem(item.getCode())*/);
			cartitemdao.addCartItem(new CartItem(cartitemdao.getLastShoppingItems()+1, Integer.parseInt(isbn),c.getCart()));
			//items.InsertItem(item);
			cia=cartitemdao.getCartItems(c.getCart());
			session.setAttribute("cartitems",cia);
		} catch (DAOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}catch (NumberFormatException | ItemNotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ItemExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//request.setAttribute("items", tmpitem);
		return "cart_view.jsp";        
	}
}