package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ModulesManager;
import beans.QCMsManager;
import entities.Answer;
import entities.Chapter;
import entities.Module;
import entities.QCM;
import entities.Question;
import entities.Subscription;
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
    		openModule(request, response, user);
    		request.getSession().setAttribute("viewPage", "./includes/student_module.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "readchapter": // local changes to the following files w
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
    		request.getSession().setAttribute("viewPage", "./includes/student_qcm.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	case "validateQCM":
    		validateQCM(request, response, user);
    		
    		request.getSession().setAttribute("viewPage", "./includes/student_qcm_result.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    		break;
    		
    	default: // TODO: redirect on home
    		break;
    	}		
	}

	private void validateQCM (HttpServletRequest request, HttpServletResponse response, User user) throws IOException
	{
		
		Chapter openChapter = modsManager.getChapter(Integer.parseInt(request.getParameter("chapterId")));
		Module module = modsManager.getModule(Integer.parseInt(request.getParameter("moduleId")));
		QCM qcm = qcmManager.findQCMByPK(Integer.parseInt(request.getParameter("qcmId")));
		request.setAttribute("chapter", openChapter);
		request.setAttribute("module", module);
		request.setAttribute("qcm", qcm);
		int total = 0;
		
		Map<String,String[]> parameters = request.getParameterMap();

		for (String s : parameters.keySet())
		{
			int qId; 
			try 
			{
				qId = Integer.parseInt(s);
			}
			catch (NumberFormatException e)
			{
				continue;
			}
			
			Question question = qcmManager.findQuestionByPK(qId);
			if (question == null) continue;
			boolean qGood = true;
			boolean hasAnswered = false;
			for (String p : parameters.get(s))
			{
				int ansId; 
				try 
				{
					ansId = Integer.parseInt(p);
				}
				catch (NumberFormatException e)
				{
					continue;
				}
				
				Answer answer = qcmManager.getAnswer(ansId);
				if ( answer != null ) hasAnswered = true;
				if ( answer.getValid() == false) qGood = false;
			}
			if (!hasAnswered) qGood = false;
			
			if (qGood) total += question.getPoints();

		}
		
		boolean done = (total >= qcm.getMinimum());
		qcmManager.updateQCMInstance(qcm, user, done, total, modsManager.getSubscriptionByStudentAndModule(user, module));
		if (done) modsManager.incrementSubscription(user, module);
		
		request.setAttribute("qcmSuccess", done);
		request.setAttribute("qcmUserNote", total);
		request.setAttribute("qcmMinimum", qcm.getMinimum());
		request.setAttribute("qcmNote", qcm.getTotal());
		request.setAttribute("showRightAnswers", qcm.getAnswersShown());
		
		List<Question> qList = qcmManager.getQuestions( qcm ); 
		request.setAttribute("qList", qList);				
	}

	private void openModule(HttpServletRequest request, HttpServletResponse response, User user) throws IOException
	{
		Module module = modsManager.getModule(Integer.parseInt(request.getParameter("openMod")));
		List<Chapter> cList = modsManager.getChapters( module );
		request.setAttribute("module", module); 
		Subscription sub = (Subscription) modsManager.getSubscriptionByStudentAndModule(user, module);
		Map<Chapter, Boolean> accessList = new HashMap<Chapter, Boolean>();
		int i = 0;
		for (Chapter c : cList)
		{
			accessList.put(c, (i <= sub.getProgress()));
			i++;
		}
		
		request.setAttribute("chapters", cList);
		request.setAttribute("accessList", accessList);		

	}
}


