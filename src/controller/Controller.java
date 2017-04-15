package controller;



import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Controller extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String theAction = request.getParameter("action");
		//PrintWriter out = response.getWriter();
		//out.println( this.getServletContext().getRealPath(".") );

		HttpSession session=request.getSession();
		String uname=(String) session.getAttribute("username");
		System.out.println(theAction);
		System.out.println(uname);
		if(uname!=null) {
			//theAction="viewlogin";
			if ( theAction == null)
				theAction="viewcat";			
		}
		else {
			System.out.println("else");
			if(!theAction.equals("authlogin")) {
				System.out.println("elseif");
				theAction="viewlogin";
			}
		}
		Action action = getActionFromConfig( theAction );
		String view = action.perform(request, response);

		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);    
		
	} 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("called");

		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
	private Action getActionFromConfig( String theAction ) 
			throws IOException {
		Properties map = new Properties();
		map.load( this.getClass().getClassLoader().getResourceAsStream( ACTION_MAPPING ));

		String action_class = map.getProperty(theAction.toLowerCase() );   
		System.out.println(action_class);
		Action action = null;
		try {

			action = (Action) Class.forName( action_class ).newInstance();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return action;
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	private final static String ACTION_MAPPING = "controller/ActionMapping.properties"; 

}
