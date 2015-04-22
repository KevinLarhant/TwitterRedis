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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login","/index"})
public class LoginServlet extends HttpServlet {

	private final static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("GET");
		RequestDispatcher requestDispatcher;

		if (request.getSession().getAttribute(Constantes.LOGIN) != null) {
			response.sendRedirect("index.jsp");
		} else {
			requestDispatcher = request.getRequestDispatcher("/login.jsp");
			requestDispatcher.include(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("POST");

		String login = request.getParameter(Constantes.LOGIN);
		String pwd = request.getParameter("pwd");

		if (UserDao.checkCredentials(login, pwd) != null) {
			request.getSession().setAttribute(Constantes.LOGIN, login);
			request.getSession().setAttribute("idUser", UserDao.checkCredentials(login, pwd));

			log.info(login + " se connecte");
		}

		response.sendRedirect("index");
	}
}
