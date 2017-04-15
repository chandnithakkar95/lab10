package controller.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;

public class ActionNewItem  implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
    	//System.out.
            request.setAttribute("cartitem", null);
          //  request.setAttribute("update_action", "insert");     
            System.out.println("hiii");
            return "cartpro.jsp";
    }
}
