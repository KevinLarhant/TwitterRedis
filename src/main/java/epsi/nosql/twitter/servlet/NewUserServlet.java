package epsi.nosql.twitter.servlet;

import epsi.nosql.twitter.dao.UserDao;
import epsi.nosql.twitter.utils.Constantes;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("processing GET on /newUser");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("processing POST on /newUser");
		
		String login = request.getParameter(Constantes.LOGIN);
		String pwd = request.getParameter("pwd1");

		if(!UserDao.checkExists(login)) {
			UserDao.newUser(login, pwd);
			log.info("User "+login+" créé");
		}

		response.sendRedirect(Constantes.LOGIN);
	}
}
