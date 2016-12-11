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
import entities.QCMInstance;
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
		/*
		 * Handle student operations
		 */
		if (request.getParameter("studentops") != null)
			handleStudentOps(request, response);
		/*
		 * Initialize HttpSession on first connexion
		 */
		else
		{
			User user = (User) request.getSession().getAttribute("user");
			List<Module> mList = modsManager.getModules( );
			List<Module> mSubsList = modsManager.getModules( user );
    		List<QCM> qList = getNextQCMs (user);
    		Map<Module, Integer> percentageMap = new HashMap<Module, Integer>();
    		
    		for (Module m : mSubsList)
    		{
    			float total = 0;
    			List<Chapter> cList = modsManager.getChapters(m);
    			if (!cList.isEmpty())
    			{
	    			for (Chapter c : cList)
	    			{
	    				QCM q = qcmManager.getQCMByChapter(c);
	    				QCMInstance cur = qcmManager.getQCMInstance(q, user);
	    				if (cur!=null)
	    					total += (float)cur.getNote() / (float)(q.getTotal() * cList.size());
	    			}

    			}
    			total *= 100.f;
    			percentageMap.put(m, (int)total);
    		}
    		
    		request.getSession().setAttribute("percentageMap", percentageMap);
			request.getSession().setAttribute("modules", mList);
     		request.getSession().setAttribute("subs", mSubsList);
       		request.getSession().setAttribute("menuShowModules", false);
       		request.getSession().setAttribute("menuShowSubs", true);
    		request.getSession().setAttribute("nextQCMs", qList);
       		request.getSession().setAttribute("actionPage", "student_home.jsp");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	private void handleStudentOps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/*
		 * Init/refresh contextual attributes
		 */
		User user = (User) request.getSession().getAttribute("user");

		List<Module> mList = modsManager.getModules( );
		request.getSession().setAttribute("modules", mList);


		/*
		 * Actual actions
		 */

		String action = request.getParameter("studentops");

		switch(action)
    	{

    	case "home":
    	{
			List<Module> mSubsList = modsManager.getModules( user );
    		List<QCM> qList = getNextQCMs (user);
    		Map<Module, Integer> percentageMap = new HashMap<Module, Integer>();
    		
    		for (Module m : mSubsList)
    		{
    			float total = 0;
    			List<Chapter> cList = modsManager.getChapters(m);
    			if (!cList.isEmpty())
    			{
	    			for (Chapter c : cList)
	    			{
	    				QCM q = qcmManager.getQCMByChapter(c);
	    				QCMInstance cur = qcmManager.getQCMInstance(q, user);
	    				if (cur!=null)
	    					total += (float)cur.getNote() / (float)(q.getTotal() * cList.size());
	    			}

    			}
    			total *= 100.f;
    			percentageMap.put(m, (int)total);
    		}
    		
     		request.getSession().setAttribute("subs", mSubsList);
    		request.getSession().setAttribute("percentageMap", percentageMap);
    		request.getSession().setAttribute("nextQCMs", qList);
    		request.getSession().setAttribute("actionPage", "student_home.jsp");
    		break;
    	}
    	
    	case "menuToggleModules":	// display modules list
       		request.getSession().setAttribute("menuShowModules",
       				(boolean) request.getSession().getAttribute("menuShowModules") == true ? false : true);
    		break;
    		
    	case "menuToggleSubs":	// display subscription list
       		request.getSession().setAttribute("menuShowSubs",
       				(boolean) request.getSession().getAttribute("menuShowSubs") == true ? false : true);
    		break;
    		
    	case "subscribe": // subscribe to a module
    		Module desired = modsManager.getModule(Integer.parseInt(request.getParameter("subMod")));
    		if (modsManager.getSubscriptionByStudentAndModule(user, desired) == null)
    		{
    			modsManager.studentSubscribe(user, desired);
        		openModule(request, response, user, desired.getId());
        		List<Module> mSubsList = modsManager.getModules( user );
         		request.getSession().setAttribute("subs", mSubsList);
        		request.setAttribute("pushModal", "successfullySubscribed");
    			break;
    		}
    		else
    		{
        		openModule(request, response, user, Integer.parseInt(request.getParameter("subMod")));
        		request.getSession().setAttribute("actionPage", "student_module.jsp");
        		break;
    		}
    		
    	case "openmodule": // open a module to view its chapters
    		Module trial = modsManager.getModule(Integer.parseInt(request.getParameter("openMod")));
    		if (modsManager.getSubscriptionByStudentAndModule(user, trial) == null)
    		{
        		request.setAttribute("pushModal", "notSubscribed");
        		break;
    		}
    		else
    		{
        		openModule(request, response, user, Integer.parseInt(request.getParameter("openMod")));
        		request.getSession().setAttribute("actionPage", "student_module.jsp");
        		break;
    		}
    		
    	case "readchapter":
    		Chapter readChapter = modsManager.getChapter(Integer.parseInt(request.getParameter("openChapter")));
    		if (modsManager.getSubscriptionByStudentAndModule(user, readChapter.getModule()) == null)
    		{
        		request.setAttribute("pushModal", "notSubscribed");
        		break;
    		}
    		else
    		{
        		request.getSession().setAttribute("chapter", readChapter);
        		request.getSession().setAttribute("module", readChapter.getModule());
        		request.getSession().setAttribute("actionPage", "student_chapter.jsp");
        		break;
    		}

    		
    	case "doqcm":
    		Chapter openChapter = modsManager.getChapter(Integer.parseInt(request.getParameter("openChapter")));
    		if (modsManager.getSubscriptionByStudentAndModule(user, openChapter.getModule()) == null)
    		{
        		request.setAttribute("pushModal", "notSubscribed");
        		break;
    		}
    		else
    		{
        		request.getSession().setAttribute("module", openChapter.getModule());
        		request.getSession().setAttribute("chapter", openChapter);
        		QCM qcm = qcmManager.getQCMByChapter(openChapter);
        		request.getSession().setAttribute("qcm", qcm);
        		List<Question> qList = qcmManager.getQuestions( qcm ); 
        		request.getSession().setAttribute("qList", qList);
        		request.getSession().setAttribute("actionPage", "student_qcm.jsp");
        		break;
    		}

    		
    	case "validateQCM":
    		validateQCM(request, response, user);
    		request.getSession().setAttribute("actionPage", "student_qcm_result.jsp");
    		break;
    		
    	default:
    		break;
    	}
		
		request.getRequestDispatcher("home.jsp").forward(request, response);

	}

	
	private void validateQCM (HttpServletRequest request, HttpServletResponse response, User user) throws IOException
	{
		
		Chapter openChapter = modsManager.getChapter(Integer.parseInt(request.getParameter("chapterId")));
		Module module = modsManager.getModule(Integer.parseInt(request.getParameter("moduleId")));
		QCM qcm = qcmManager.findQCMByPK(Integer.parseInt(request.getParameter("qcmId")));
		request.getSession().setAttribute("chapter", openChapter);
		request.getSession().setAttribute("module", module);
		request.getSession().setAttribute("qcm", qcm);
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
		Subscription sub = modsManager.getSubscriptionByStudentAndModule(user, module);
		if (sub == null)
		{
    		request.setAttribute("pushModal", "notSubscribed");
			return;
		}
		qcmManager.updateQCMInstance(qcm, user, done, total, sub);
		if (done) modsManager.incrementSubscription(user, module);
		
		request.getSession().setAttribute("qcmSuccess", done);
		request.getSession().setAttribute("qcmUserNote", total);
		request.getSession().setAttribute("qcmMinimum", qcm.getMinimum());
		request.getSession().setAttribute("qcmNote", qcm.getTotal());
		request.getSession().setAttribute("showRightAnswers", qcm.getAnswersShown());
		
		List<Question> qList = qcmManager.getQuestions( qcm ); 
		request.getSession().setAttribute("qList", qList);				
	}

	
	private void openModule(HttpServletRequest request, HttpServletResponse response, User user, int modId) throws IOException
	{
		Module module = modsManager.getModule(modId);
		List<Chapter> cList = modsManager.getChapters( module );
		request.getSession().setAttribute("module", module); 
		Subscription sub = (Subscription) modsManager.getSubscriptionByStudentAndModule(user, module);
		Map<Chapter, Boolean> accessList = new HashMap<Chapter, Boolean>();
		int i = 0;
		for (Chapter c : cList)
		{
			accessList.put(c, (i <= sub.getProgress()));
			i++;
		}
		
		request.getSession().setAttribute("chapters", cList);
		request.getSession().setAttribute("accessList", accessList);		

	}

	private List<QCM> getNextQCMs ( User user )
	{
		List<Module> mSubsList = modsManager.getModules( user );
		List<QCM> qList = new ArrayList<QCM>();
		for (Module m : mSubsList)
		{
			List<Chapter> cList = modsManager.getChapters(m);
			for (Chapter c : cList)
			{
				QCM q = qcmManager.getQCMByChapter(c);
				if (qcmManager.getQCMInstance(q, user) == null)
				{
					qList.add(q);
					break;
				}
			}
		}
		return qList;
	}
	
}


