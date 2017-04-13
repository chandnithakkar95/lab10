package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;
import controller.Action;
import dao.InventoryDAO;
import util.DAOException;
import util.ItemNotFound;

public class RemoveItem implements Action{

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {

        String view = "catalogue_view.jsp";
               
        String isbn = req.getParameter("itmCode");
        System.out.println(isbn);
        InventoryItem[] tmItem=null;
        try {
        	InventoryDAO book_db = new InventoryDAO();
				book_db.deleteItem(Integer.parseInt(isbn));
				tmItem = book_db.getItems();
			//	putStockRequest(req, resp);
			} catch (NumberFormatException e) {
				
			}
        	catch(ItemNotFound e) {
        	}
        	catch(	DAOException  e) {
				// TODO Auto-generated catch block
				req.setAttribute("error", e.toString() + "[RemoveBook.java]");
				return "error.jsp";
			}
        req.setAttribute("items", tmItem);

        return view;
        
    }
    
}
