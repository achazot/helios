package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ModulesManager;
import entities.Module;
import entities.User;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	private ModulesManager modsManager;

	public StudentController()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getParameter("studentops") != null)
			handleStudentOps(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	private void handleStudentOps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user = (User) request.getSession().getAttribute("user");
		List<Module> mList = modsManager.getModules( );
		List<Module> mSubsList = modsManager.getModules( user );
		switch(request.getParameter("studentops"))
    	{	
    	case "browseModules":	// display modules list
    		request.setAttribute("modules", mList); 
			request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "listSubscriptions":	// display subscription list
    		request.setAttribute("subs", mSubsList); 
			request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "subscribe":	// display subscription list
    		Module module = modsManager.findModuleByPK(mList, Integer.parseInt(request.getParameter("module")));
    		modsManager.studentSubscribe(user, module);
    		request.removeAttribute("module");
			request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	default:
    		break;
    	}		
	}

}
