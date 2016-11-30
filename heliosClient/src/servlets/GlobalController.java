package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		
		if (request.getParameter("userops") != null)
			handleUserOps(request, response);
		
    	request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}


	/**
	 * @author dibi
	 * Handles login & logout on pages
	 * 
	 */
	
	private void handleUserOps(HttpServletRequest request, HttpServletResponse response)
	{
		String a_mail;
		String a_password;
		
		switch(request.getParameter("userops"))
    	{	
    	case "connect":
    		a_mail = request.getParameter("mail");
    		a_password = request.getParameter("password");
    		if(a_mail != null && a_password != null && usersManager.check(a_mail, a_password))
    		{
    			request.getSession().setAttribute("mail", a_mail);
    		}
    		break;
    	case "disconnect":
			request.getSession().setAttribute("mail", null);
    		
    		break;
    	default:
    		break;
    	}		
	}
}

