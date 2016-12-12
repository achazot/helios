package servlets;

import java.io.IOException;
import java.util.List;

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
		if (!((User)request.getSession().getAttribute("user")).getGrp().equals("admin"))
		{
			request.getSession().invalidate();
			request.getRequestDispatcher("GlobalController").forward(request, response);
		}
			
		if (request.getParameter("adminops") != null)
			handleAdminOps(request, response);
		else
		{
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
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
		User user = (User) request.getSession().getAttribute("user");
		request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");

		switch(request.getParameter("adminops"))
    	{	
    	case "browseStudents":	// display students list
    		List<User> sList = usersManager.getUsers("student");
    		request.setAttribute("students", sList);
    		break;
    		
    	case "browseTeachers":	// display teachers list
    		List<User> tList = usersManager.getUsers("teacher");
    		request.setAttribute("teachers", tList);
    		break;
    		
    	case "delete":	// delete user 
    		String login = (String) request.getParameter("login");
    		usersManager.deleteUser(login);
    		request.removeAttribute("login");
    		break;
    		
    	case "getForm":	// get account form
    		request.setAttribute("accountForm", "1");
    		break;	
    		
    	case "submitForm":	// register new account 
    		
    		String name 	= 	request.getParameter("name_form");
    		String surname 	= 	request.getParameter("surname_form");
    		String mail 	= 	request.getParameter("mail_form");
    		String group 	= 	request.getParameter("grp_form");
    		String log 		= 	request.getParameter("login_form");
    		String password = 	request.getParameter("password_form");
    		
    		request.setAttribute("accountForm", "1");
    		
    		// empty fields
    		if( name.isEmpty() || surname.isEmpty() || mail.isEmpty() || group.isEmpty() || log.isEmpty() || password.isEmpty() )
    		{
    			request.setAttribute("errorForm", "Veuillez renseigner tous les champs svp");
    		}
    		else
    		{
    			// control inputs 
    			String err = ""; 
	    		err += ! tools.InputControl.checkOnlyAlphabetic(name) ? "Le champs prénom doit être composé uniquement de caractères alphabétiques <br>" : "";
	    		err += ! tools.InputControl.checkOnlyAlphabetic(surname) ? "Le champs nom doit être composé uniquement de caractères alphabétiques <br>" : "";
	    		err += ! tools.InputControl.checkNoSQL(log) ? "Le champs identifiant ne doit pas comporter de <a href=\"https://docs.oracle.com/database/121/SQLRF/ap_keywd001.htm#SQLRF55621\">mots réservés au langage SQL</a> <br>" :"";
	    		err += ! tools.InputControl.checkNoSQL(password) ? "Le champs mot de passe ne doit pas comporter de <a href=\"https://docs.oracle.com/database/121/SQLRF/ap_keywd001.htm#SQLRF55621\">mots réservés au langage SQL</a> <br>" :"";
	    		err += ! tools.InputControl.checkMail(mail) ? "Le champs mail doit être de la forme <[a-zA-z-.]>@helios.fr" : ""; 
	    		
	    		if( ! err.isEmpty() )	  
    				request.setAttribute("errorForm", err);
	    		
	    		// account created 
	    		else if( usersManager.createUser(name, surname, mail, group, log, password) )
    			{	
    				request.removeAttribute("errorForm");
        			request.setAttribute("accountForm", null);
        			request.setAttribute("userCreated", true);
    			}
	    		
	    		// login already used
    			else
    				request.setAttribute("errorForm", "Cet identifiant est déja utilisé, veuillez en choisir un autre");
    		}
    		break;	
    	default:
    		break;
    	}		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
