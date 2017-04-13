package controller.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;
import controller.Action;
import dao.InventoryDAO;



public class ActionViewCat implements Action{

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse res) {

        String view = "catalogue_view.jsp";
        
        try {
            InventoryDAO book_db = new InventoryDAO();
            InventoryItem[] items = book_db.getItems();
            req.setAttribute("items", items);
        }
        catch(Exception e) {
            req.setAttribute("error", e.toString() + ";[ShowCat.java]");
            return "error.jsp";
        }

        return view;

    }
    
}
