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
 * @author lisa
 *
 */

@WebServlet("/AdminController")
public class AdminController extends HttpServlet
{

	private static final long serialVersionUID = -236750258045868778L;

	@EJB
	private UsersManager usersManager;

	public AdminController()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{				
		if (request.getParameter("adminops") != null)
			handleAdminOps(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}


	/**
	 * @author lisa
	 * Handles admin actions : create and delete users, display users lists by group
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	
	private void handleAdminOps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		switch(request.getParameter("adminops"))
    	{	
    	case "browseStudents":	// display students list
    		List<User> sList = usersManager.getUsers("student");
    		request.setAttribute("students", sList);
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "browseTeachers":	// display teachers list
    		List<User> tList = usersManager.getUsers("teacher");
    		request.setAttribute("teachers", tList);
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "delete":	// delete user 
    		String login = (String) request.getParameter("login");
    		usersManager.deleteUser(login);
    		request.removeAttribute("login");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "getForm":	// get account form
    		request.setAttribute("accountForm", "1");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;	
    		
    	case "submitForm":	// register new account 
    		
    		String name 	= 	request.getParameter("name_form");
    		String surname 	= 	request.getParameter("surname_form");
    		String mail 	= 	request.getParameter("mail_form");
    		String group 	= 	request.getParameter("grp_form");
    		String log 		= 	request.getParameter("login_form");
    		String password = 	request.getParameter("password_form");
    		
    		request.setAttribute("accountForm", "1");
    		
    		if( name.equals("") || surname.equals("") || mail.equals("") || group.equals("") || log.equals("") 	|| password.equals("") 	)
    		{
    			System.out.println(">>>>>>>> equals name" + name);
    			request.setAttribute("errorForm", "Veuillez renseigner tous les champs svp");
    		}
    		else
    		{
    			if( usersManager.createUser(name, surname, mail, group, log, password) )
    			{	// this login is not used
    				request.removeAttribute("errorForm");
        			request.setAttribute("accountForm", null);
        			request.setAttribute("userCreated", true);
    			}
    			else
    				request.setAttribute("errorForm", "Cet identifiant est déja utilisé, veuillez en choisir un autre");
    		}
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;	
    	default:
    		break;
    	}		
	}
}
