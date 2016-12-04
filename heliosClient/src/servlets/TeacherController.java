package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ModulesManager;
import beans.QCMsManager;
import beans.UsersManager;
import entities.Module;
import entities.User;

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	private ModulesManager modsManager;
	@EJB 
	private QCMsManager qcmManager; 	
	
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
	
	@SuppressWarnings("deprecation")
	private void handleTeacherOps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user = (User) request.getSession().getAttribute("user");
		List<Module> mList = modsManager.getModules( user );
		
		switch(request.getParameter("teacherops"))
    	{	
    	case "browseModules":	// display modules list
    		request.setAttribute("modules", mList); 
			request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "viewModule":	
    		int id = Integer.parseInt( request.getParameter("module") );
    		Module module = modsManager.findModuleByPK(mList, (int)id);
    		request.getSession().setAttribute("module", module);
    		request.getSession().setAttribute("viewPage", "./includes/module.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "createQCM":	// create new QCM
    		String title = request.getParameter("title");
    		String chapter = request.getParameter("chapter");
    		int total = Integer.parseInt( request.getParameter("total") ); 
    		qcmManager.createQCM( title, total, new Date("01/01/2017"), true);
    		request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;	
    	default:
    		break;
    	}		
	}


}
