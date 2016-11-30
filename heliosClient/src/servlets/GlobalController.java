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

@WebServlet("/GlobalController")
public class GlobalController extends HttpServlet
{

	private static final long serialVersionUID = -236750258045868778L;

	@EJB
	private UsersManager usersManager;
	
	public GlobalController()
	{
		super();		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		Map<String,String[]> parameters = request.getParameterMap();
		
		
		usersManager.register("dibi", "dibi", "dibi", "dibi", "admin");

		if (parameters == null || parameters.isEmpty())
		{		
			response.getWriter().append("En construction de ouf\nUsers: ");
			List<User> users = usersManager.getUsers();
			for (User u : users)
			{
				response.getWriter().append(u.getName()).append("\n");
			}
			
			//this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
		}
		else
		{
			response.getWriter().append("Parameters: \n");	
			for (String s : parameters.keySet())
			{
				response.getWriter().append("    ").append(s).append(" : \n");
				for (String p : parameters.get(s))
				{
					response.getWriter().append("        ").append(p).append("\n");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
