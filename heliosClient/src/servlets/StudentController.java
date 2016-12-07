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
import beans.QCMsManager;
import entities.Chapter;
import entities.Module;
import entities.QCM;
import entities.QCMInstance;
import entities.Question;
import entities.User;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	private ModulesManager modsManager;
	@EJB
	private QCMsManager qcmManager;
	
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
				
		switch(request.getParameter("studentops"))
    	{	
    	case "browseModules":	// display modules list
    		List<Module> mList = modsManager.getModules( );
    		request.setAttribute("modules", mList);
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "listSubscriptions":	// display subscription list
    		List<Module> mSubsList = modsManager.getModules( user );
    		request.setAttribute("subs", mSubsList);
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "subscribe": // subscribe to a module
    		modsManager.studentSubscribe(user, modsManager.getModule(Integer.parseInt(request.getParameter("subMod"))));
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "openmodule": // open a module to view its chapters
    		Module openMod = modsManager.getModule(Integer.parseInt(request.getParameter("openMod")));
    		List<Chapter> cList = modsManager.getChapters( openMod );
    		request.setAttribute("module", openMod); 
    		request.setAttribute("chapters", cList);
    		request.getSession().setAttribute("viewPage", "./includes/student_module.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "readchapter":
    		request.setAttribute("module", modsManager.getModule(Integer.parseInt(request.getParameter("openMod"))));
    		request.setAttribute("chapter", modsManager.getChapter(Integer.parseInt(request.getParameter("openChapter"))));
    		request.getSession().setAttribute("viewPage", "./includes/student_chapter.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "doqcm":
    		Chapter openChapter = modsManager.getChapter(Integer.parseInt(request.getParameter("openChapter")));
    		request.setAttribute("module", modsManager.getModule(Integer.parseInt(request.getParameter("openMod"))));
    		request.setAttribute("chapter", openChapter);
    		QCM qcm = qcmManager.getQCMByChapter(openChapter);
    		request.setAttribute("qcm", qcm);
    		List<Question> qList = qcmManager.getQuestions( qcm ); 
    		request.setAttribute("qList", qList);

    		//TODO:Display old qcm
    		//QCMInstance qcmInst = qcmManager.getQCMInstance(qcm, user);
    		//request.setAttribute("qcmInst", qcmInst);
    		
    		request.getSession().setAttribute("viewPage", "./includes/student_qcm.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	default:
    		break;
    	}		
	}

}
