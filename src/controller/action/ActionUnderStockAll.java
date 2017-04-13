package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;
import controller.Action;
import dao.InventoryDAO;

public class ActionUnderStockAll implements Action{
	public String perform(HttpServletRequest req, HttpServletResponse res) {

        String view = "items_underStock.jsp";
        
        try {
            InventoryDAO book_db = new InventoryDAO();
            InventoryItem[] items = book_db.itemsUnderStock();
            req.setAttribute("items", items);
        }
        catch(Exception e) {
            req.setAttribute("error", e.toString() + ";[ShowCat.java]");
            return "error.jsp";
        }

        return view;

    }
    
}
