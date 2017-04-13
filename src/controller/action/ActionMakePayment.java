package controller.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;

public class ActionMakePayment  implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
    	//System.out.
            return "thankyou.jsp";
    }
}
