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
import entities.Answer;
import entities.Chapter;
import entities.Module;
import entities.QCM;
import entities.Question;
import entities.Subscription;
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
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	private void handleTeacherOps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{			
		List<Module> mList 	= (List<Module>)request.getSession().getAttribute("mList"); 
		User user 			= (User) 		request.getSession().getAttribute("user"); 
		Module module 		= (Module) 		request.getSession().getAttribute("module"); 
		Chapter chapter 	= (Chapter) 	request.getSession().getAttribute("chapter"); 
		QCM qcm 			= (QCM) 		request.getSession().getAttribute("qcm"); 
		Question question	= (Question) 	request.getSession().getAttribute("question"); 	
		
		if( mList == null && user != null )
		{
			mList = modsManager.getModules( user );
			request.getSession().setAttribute("modules", mList);
		}
		
		// update current module
		if( request.getParameter( "module" ) != null )
		{
			int mId;
			mId = Integer.parseInt( request.getParameter("module") );
			if( module == null || module.getId() != mId )
			{
				module = modsManager.findModuleByPK(mList, mId);
				request.getSession().setAttribute("module", module);
			}
		}
		
		// update current chapter
		if( request.getParameter( "chapter" ) != null )
		{
			int cId; 
			cId = Integer.parseInt( request.getParameter("chapter") );
			if( chapter == null || chapter.getId() != cId )
			{
				chapter = modsManager.getChapter( cId );
				request.getSession().setAttribute("chapter", chapter);
			}
		}
		
		// update current qcm
		if( request.getParameter( "qcm" ) != null )
		{
			int qId; 
			qId = Integer.parseInt( request.getParameter("qcm") );
			qcm = qcmManager.findQCMByPK( qId );
			request.getSession().setAttribute("qcm", qcm);
		}
		
		// update current question
		if( request.getParameter( "questionID" ) != null )
		{
			int questId;
			questId = Integer.parseInt( request.getParameter("questionID") );
			if( question == null || question.getId() != questId || ! question.getQcm().equals(qcm) )	
			{
				question = qcmManager.findQuestionByPK( questId );
				request.getSession().setAttribute("question", question);
			}
		}
		
		switch(request.getParameter("teacherops"))
    	{	
			// display modules list
    	case "browseModules":	
			request.getSession().setAttribute("viewPage", "./includes/" + user.getGrp() + ".jsp");
    		break;
    		
    		// display module 
    	case "viewModule":		 
    		loadChapters( request );
    		loadSubscriptions( request );
    		request.getSession().setAttribute("module", module);
    		request.getSession().setAttribute("viewPage", "./includes/teacher_module.jsp");
    		break;
    	
    		// display chapter text
    	case "viewChapter":
    		request.getSession().setAttribute("chapter", chapter);
    		request.getSession().setAttribute("viewPage", "./includes/teacher_chapter_view.jsp");
    		break;
    		
    		// display QCM 
    	case "viewQCM": 
    		QCM m_qcm = qcmManager.getQCMByChapter( chapter );
    		List<Question> m_qList = qcmManager.getQuestions( m_qcm ); 
    		request.getSession().setAttribute("questionsv", m_qList);
			request.getSession().setAttribute("viewPage", "./includes/teacher_questions.jsp");
    		break; 
    		
    		// display subscriptions and statisctics
    	case "viewSubscriptions":
    		request.getSession().setAttribute("viewPage", "./includes/teacher_subscriptions.jsp");
    		break;
    		
    		// get chapter form 
    	case "getChapterForm":	
    			request.getSession().setAttribute("viewPage", "./includes/teacher_chapter.jsp");
    		break;
    	
    		// get QCM form 	
    	case "getQCMForm":		   		
    		request.getSession().setAttribute("module", module);
    		request.getSession().setAttribute("chapter", chapter);
    		request.getSession().setAttribute("isQCMCreated", "false");
    		request.getSession().setAttribute("viewPage", "./includes/teacher_qcm.jsp");
    		break;
		
	    	// post chapter
			// create chapter
    	case "addChapter":	
    		String chapText = request.getParameter( "text" );
    		String chapTitle = request.getParameter( "title" );
    		modsManager.createChapter(chapTitle, chapText, module);
    		loadChapters( request );
    		request.getSession().setAttribute("viewPage", "./includes/teacher_module.jsp");
    		break;	

			// post QCM 
			// create QCM 	
    	case "addQCM":		
    		if( request.getParameter( "isQCMCreated" ).equals( "false" ) )
    		{	
    			String qcmTitle = "QCM " + chapter.getTitle();
    			boolean showAnswers = ( request.getParameter( "showAnswers" ).equals( "yes" ) ); 
    			Date creation = new Date();
    			Date expiration = new Date( request.getParameter("expiration") );
    			System.out.println(">>>>>>>>>>>< " + expiration );
    			if( creation.after(expiration) )
    				request.setAttribute("errorForm", "Veuillez choisir une date d'expiration ultérieure à la date du jour svp");
	    		else
	    		{	
	    			qcm = qcmManager.createQCM( qcmTitle, creation, expiration, showAnswers, chapter );
	    			request.setAttribute("qcm", qcm);
	    			request.getSession().setAttribute("viewPage", "./includes/teacher_qcm.jsp");
	    		}
    		}
    		break;
    		
			// post question
			// create question    		
    	case "addQuestion":

    		String questionText = request.getParameter("question");
    		if( questionText.isEmpty() )
    		{
				request.setAttribute("errorForm", "Veuillez renseigner une question svp.");
    		}
    		else 	
    		{	
    			int points = Integer.parseInt( request.getParameter("points") );
	    		qcmManager.createQuestions( points, questionText, qcm );
	    		List<Question> qList = qcmManager.getQuestions( qcm );
	    		request.setAttribute("questions", qList);
	    		request.getSession().setAttribute("qcm", qcm);
    		}
    		request.getSession().setAttribute("viewPage", "./includes/teacher_qcm.jsp");
			break;
			
			// post answer
			// create answer -> add it in question entity    	
    	case "addAnswer":	 	
    		String answerText = request.getParameter("answer");
    		if( answerText.isEmpty() )
    		{
				request.setAttribute("errorForm", "Veuillez renseigner une réponse svp.");
    		}
    		else 	
    		{	
	    		boolean valid = ( request.getParameter( "valid" ).equals( "yes" ) );
	    		Answer answer = qcmManager.createAnswer( answerText , valid, question );
	    		qcmManager.setAnswerToQuestion( question, answer );
	    		List<Question> upQList = qcmManager.getQuestions( qcm );
	    		request.getSession().setAttribute("questions", upQList);
	    		request.getSession().setAttribute("viewPage", "./includes/teacher_qcm.jsp");    		
	    	}
    		break;
    		
    	default:
    		break;
    	}
		request.getRequestDispatcher("home.jsp").forward(request, response);

	}

	private void loadChapters( HttpServletRequest request )
	{
		List<Chapter> cList = modsManager.getChapters( (Module) request.getSession().getAttribute( "module" ) );
		request.getSession().setAttribute("chapters", cList);
	}
	
	private void loadSubscriptions( HttpServletRequest request )
	{
		List<Subscription> sList = modsManager
				.getSubscriptionsByModule( (Module) request.getSession().getAttribute( "module" ) );
		request.getSession().setAttribute("subscriptions", sList);
	}
}
