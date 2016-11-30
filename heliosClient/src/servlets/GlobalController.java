package servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GlobalController")
public class GlobalController extends HttpServlet
{

	private static final long serialVersionUID = -236750258045868778L;

	
	public GlobalController()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		Map<String,String[]> parameters = request.getParameterMap();
		
		if (parameters == null || parameters.isEmpty())
		{		
			response.getWriter().append("En construction de ouf");
			this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
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
