package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InventoryItem;
import controller.Action;
import dao.InventoryDAO;
import util.DAOException;

public class ActionItemCategory implements Action {

	public String perform(HttpServletRequest req, HttpServletResponse res) {

		String view = "catalogue_view.jsp";
		String[] temp=req.getParameterValues("cateSelct");
		//try {
		InventoryDAO book_db;
		try {
			book_db = new InventoryDAO();
			InventoryItem[] items = book_db.getItems();
			System.out.println(temp[0]);
			if(temp[0].compareTo("all")==0) {
				req.setAttribute("items", items);
			}
			else {
				ArrayList<InventoryItem> tmps = new ArrayList<InventoryItem>();
				for(int i=0;i<items.length;i++) {
					if(items[i].getCateId()==Integer.parseInt(temp[0])) {
						tmps.add(items[i]);
					}
					else {
					}
				}
				InventoryItem[] tmp=new InventoryItem[0];
				tmp=tmps.toArray(tmp);
				req.setAttribute("items", tmp);	
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//}
		//catch(Exception e) {
		//  req.setAttribute("error", e.toString() + ";[ShowCat.java]");
		// return "error.jsp";
		//}
		return view;
	}

}
