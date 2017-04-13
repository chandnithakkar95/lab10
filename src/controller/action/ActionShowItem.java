package controller.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;
import controller.Action;
import dao.InventoryDAO;
import util.DAOException;
import util.ItemNotFound;

public class ActionShowItem implements Action {

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		String item_id = request.getParameter("itmCode");
		try {
			InventoryDAO itemStore =new InventoryDAO();
			InventoryItem item = null;
			item = itemStore.getItem(Integer.parseInt(item_id));
			request.setAttribute("item", item);
		} catch (NumberFormatException | ItemNotFound | DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "item_show.jsp";
	}
}
