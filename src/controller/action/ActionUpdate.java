package controller.action;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;
import controller.Action;
import dao.InventoryDAO;
import util.DAOException;
import util.ItemExists;
import util.ItemNotFound;

public class ActionUpdate implements Action {

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {

		String itmCode = request.getParameter("item_code");
		String title = request.getParameter("name");

		int qty =Integer.parseInt(request.getParameter("qty"));
		int min_qty= Integer.parseInt(request.getParameter("min_qty"));

		double price = Double.parseDouble(request.getParameter("price"));
		String tempcate []= request.getParameterValues("cate_id");
		int cate_id=Integer.parseInt(tempcate[0]);
		InventoryDAO items = null;

		try {
			items = new InventoryDAO();
		} catch (DAOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		InventoryItem item;  
		try {
			item = new InventoryItem(Integer.parseInt(itmCode), title, qty, min_qty, price, cate_id);
			items.updateItem(item);
		} catch (NumberFormatException e) {
			System.out.println("NUM");
		}catch (ItemNotFound e ) {
			item = new InventoryItem(Integer.parseInt(itmCode), title, price);
			try {
				items.InsertItem(item);
			} catch (ItemExists | DAOException e1) {

				e1.printStackTrace();
			}
		}catch(DAOException e) {
			System.out.println("DAO");
		}

		InventoryItem[] tmItem=null;
		try {
			tmItem = items.getItems();
			//	for(int i=0;i<tmItem.length;i++) {
			//	System.out.println(tmItem[i]);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("items", tmItem);
		return "catalogue_view.jsp";        
	}
}
