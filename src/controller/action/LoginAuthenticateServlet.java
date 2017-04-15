package controller.action;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.UserDAO;
import Bean.Cart;
import Bean.User;
import util.DAOException;
import util.ItemExists;
import util.UserNotFound;
import util.UserExist;


public class LoginAuthenticateServlet implements Action{
	//x		@Override
	//protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//super.service(arg0, arg1);
	//	System.out.println("service Started");
	//}
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(",,,sdjh");

		String page="catalogue_view.jsp";
		String uname=request.getParameter("email");
		String pwd=request.getParameter("psw");
		String submit=request.getParameter("submit");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UserDAO dao;
		User us;

		if(submit.matches("login")) {
			//System.out.println("if");
			try {
				dao=new UserDAO();
				us=UserDAO.getUser(uname,pwd);
			} 
			catch (NullPointerException e) {
				out.write("null pointer");
			}
			catch(UserNotFound e) {
				
				out.write("user name or password is incorrect");
				return "LOGIN.jsp";
				
			} catch(ClassNotFoundException|DAOException e) {
				// TODO Auto-generated catch block
				out.write("Connection problem");
			}
			HttpSession session=request.getSession();
			session.setAttribute("username", uname);
			CartDAO cartdao = null;
			CartItemDAO cartitemdao;
			Cart cart = null;
			try {
				cartdao=new CartDAO();
				
				//System.out.println("doit man");
				cart = new Cart(1+cartdao.getLastShopping(),(String) session.getAttribute("username"));
			cartdao.addCart(cart);
			} catch (DAOException | ItemExists e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("cart",cart);
		}
		if(submit.matches("signup")) {
			//System.out.println("else");
			String usrname=request.getParameter("email");
			String paswd=request.getParameter("psw");
			String cpwd=request.getParameter("cpswd");
			String email=request.getParameter("emailID");
			try {
				dao=new UserDAO();
				if(paswd.equals(cpwd)) {					
					dao.createUser(new User(usrname,paswd, email));
				}
			} catch (ClassNotFoundException | DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HttpSession session=request.getSession();
			session.setAttribute("uname", usrname);
			try {
				response.sendRedirect("menu.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return page;
	}
}
