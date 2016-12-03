package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsersManager;
import entities.User;

/**
 * 
 * @author dibi
 *
 */

@WebServlet("/GlobalController")
public class GlobalController extends HttpServlet
{

	private static final long serialVersionUID = -236750258045868778L;

	@EJB
	private UsersManager usersManager;

	public GlobalController()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		if(request.getSession().getAttribute("trials") == null)
			request.getSession().setAttribute("trials", 0);
			
		if (request.getParameter("userops") != null )
			handleUserOps(request, response);
		
		else
			request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}


	/**
	 * @author dibi
	 * Handles login & logout on pages
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	
	private void handleUserOps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String a_login;
		String a_password;
		
		switch(request.getParameter("userops"))
    	{	
    	case "connect":
    		a_login = request.getParameter("login");
    		a_password = request.getParameter("password");
    		if(a_login != null && a_password != null && usersManager.check(a_login, a_password))
    		{
    			User user = usersManager.getUserByLogin(a_login);
    			request.getSession().setAttribute("user", user);
    			request.getSession().setAttribute("trials", 0);
    			request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    	    	request.getRequestDispatcher("home.jsp").forward(request, response);
    		}
    		else
    		{
    			request.getSession().setAttribute("trials", (int)request.getSession().getAttribute("trials") + 1);
    			request.getRequestDispatcher("index.jsp").forward(request, response);
    		}
    		break;
    	case "disconnect":
			request.getSession().setAttribute("user", null);
			request.getRequestDispatcher("index.jsp").forward(request, response);
    		break;
    	case "infos":
    		request.setAttribute("infos", true);
			request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	default:
    		break;
    	}		
	}
}

