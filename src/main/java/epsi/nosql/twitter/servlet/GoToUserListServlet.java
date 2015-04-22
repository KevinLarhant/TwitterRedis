package epsi.nosql.twitter.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoToUserListServlet", urlPatterns={"/gotoUserList"})
public class GoToUserListServlet extends HttpServlet {

    Logger log = Logger.getLogger(GoToUserListServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET");

        request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(request,response);
    }
}
