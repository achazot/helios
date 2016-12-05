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
import entities.Chapter;
import entities.Module;
import entities.QCM;
import entities.Question;
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
		// current user & module list 
		User user = (User) request.getSession().getAttribute("user"); 
		List<Module> mList = modsManager.getModules( user );
		
		// current module & chapter 
		int mId;
		int cId;
		int qId; 
		
		Module module = null;
		Chapter chapter = null; 
		QCM qcm = null; 
		
		// get module object
		if( request.getParameter( "module" ) != null )
		{
			mId  = Integer.parseInt( request.getParameter("module") );
			module = modsManager.findModuleByPK(mList, mId);
		}
		
		// get chapter object 
		if( request.getParameter( "chapter" ) != null )
		{
			cId = Integer.parseInt( request.getParameter("chapter") );
			chapter = modsManager.getChapter( cId );
		}
		
		// get qcm object 
		if( request.getParameter( "qcm" ) != null )
		{
			qId = Integer.parseInt( request.getParameter("qcm") );
			qcm = qcmManager.findQCMByPK( qId );
		}
		
		switch(request.getParameter("teacherops"))
    	{	
    	case "browseModules":	// display modules list
    		request.setAttribute("modules", mList); 
			request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "viewModule":		// display chapters list in selected module)
    		List<Chapter> cList = modsManager.getChapters( module );
    		request.getSession().setAttribute("chapters", cList);
    		request.getSession().setAttribute("module", module);
    		request.getSession().setAttribute("viewPage", "./includes/module.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "getChapterForm":	// get Chapter creation form TODO
    			request.getSession().setAttribute("viewPage", "./includes/chapter.jsp");
    		break;
    	case "getQCMForm":		// get QCM creation form    		
    		request.getSession().setAttribute("module", module);
    		request.getSession().setAttribute("chapter", chapter);
    		request.getSession().setAttribute("isQCMCreated", "false");
    		request.getSession().setAttribute("viewPage", "./includes/qcm.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "createQCM":		// create & edit QCM
    		if( request.getParameter( "isQCMCreated" ).equals( "false" ) )
    		{	
    			String title = "QCM " + chapter.getTitle();
    			boolean b = ( request.getParameter( "showAnswers" ).equals( "yes" ) ); 
    			int total = 0; 
    			Date creation = new Date();
    			Date expiration = new Date( request.getParameter("expiration") );
    			if( creation.after(expiration) )
    				request.setAttribute("errorForm", "Veuillez choisir une date d'expiration ultérieure à la date du jour svp");
	    		else
	    		{	
	    			qcm = qcmManager.createQCM( title, total, creation, expiration, b, chapter );
	    			request.getSession().setAttribute("qcm", qcm);
	    			request.getSession().setAttribute("viewPage", "./includes/qcm.jsp");
	    		}
    		}
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "addQuestion":
    		String text = request.getParameter("question");
    		int points = Integer.parseInt( request.getParameter("points") );
    		qcmManager.createQuestions(points, text, qcm);
    		List<Question> qList = qcmManager.getQuestions( qcm );
    		request.getSession().setAttribute("questions", qList);
    		request.getSession().setAttribute("viewPage", "./includes/qcm.jsp");
			request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    	case "viewQCM": 	// view QCM questions 
    		QCM m_qcm = qcmManager.getQCMByChapter( chapter );
    		List<Question> m_qList = qcmManager.getQuestions( m_qcm ); 
    		request.getSession().setAttribute("questions", m_qList);
			request.getSession().setAttribute("viewPage", "./includes/questions.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break; 
    	default:
    		break;
    	}		
	}


}
