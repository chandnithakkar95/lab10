package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;

public class ActionLoginView implements Action{

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "LOGIN.jsp";
	}
	  
	
}
