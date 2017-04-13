package controller.action;




import controller.Action;
import dao.InventoryDAO;
import util.DAOException;
import util.ItemNotFound;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;



public class ActionEditItem implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
    	String isbn = request.getParameter("itmCode");
        InventoryDAO items;
        InventoryItem item = null;
		try {
			items = new InventoryDAO();
			item=items.getItem(Integer.parseInt(isbn));
			System.out.println(item);
		} catch (DAOException | NumberFormatException | ItemNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        request.setAttribute("item", item);
        request.setAttribute("update_action", "update");            
        return "book_entry.jsp";
     
    }
}
