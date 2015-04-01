package epsi.nosql.twitter.servlet;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class NewUserServlet
 */
@WebServlet(name="NewUserServlet", urlPatterns={"/newUser"})
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(NewUserServlet.class);   
	
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("processing GET on /newUser");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/NewUser.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("processing POST on /newUser");
		
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd1");
		
//		DaoUser.newUser(login, pwd);
		log.info("User "+login+" créé");
		
		response.sendRedirect("login");
	}
}
