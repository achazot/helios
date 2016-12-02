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

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	//@EJB
	//private ModulesManager modsManager;

	public TeacherController()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{				
		if (request.getParameter("teacherops") != null)
			handleTeacherOps(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}


	/**
	 * @author lisa
	 * Handles teachers actions : create and edit modules
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	
	private void handleTeacherOps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		switch(request.getParameter("teacherops"))
    	{	
    	case "browseModules":	// display modules list
//    		List<Module> mList = modsManager.getModules();
//    		request.setAttribute("modules", mList);
    		System.out.println( "YOOOOOOO >>>>>> "+ (String)request.getParameter("teacherLogin") );
    		request.setAttribute("modules", request.getParameter("teacherLogin"));
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;

    	default:
    		break;
    	}		
	}


}
