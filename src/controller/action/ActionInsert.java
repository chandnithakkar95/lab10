package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;
import controller.Action;
import dao.InventoryDAO;
import util.DAOException;
import util.ItemExists;

public class ActionInsert implements Action
{
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("item_code");
		String title = request.getParameter("name");
		int qty =Integer.parseInt(request.getParameter("qty"));
		int min_qty= Integer.parseInt(request.getParameter("min_qty"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		String tempcate []= request.getParameterValues("cate_id");
		
		int cate_id=Integer.parseInt(tempcate[0]);
		InventoryDAO items = null;
		try {
			items = new InventoryDAO();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InventoryItem item = new InventoryItem(Integer.parseInt(isbn), title, qty, min_qty, price, cate_id);
		
		InventoryItem[] tmpitem=null ;
		try {
			//
			items.InsertItem(item);
		
			tmpitem = items.getItems();
			
		} catch (ItemExists | DAOException e) {
			e.printStackTrace();
		}
		request.setAttribute("items", tmpitem);
		return "catalogue_view.jsp";        
	}
}