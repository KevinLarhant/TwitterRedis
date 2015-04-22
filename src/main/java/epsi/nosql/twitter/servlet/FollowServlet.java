package epsi.nosql.twitter.servlet;

import epsi.nosql.twitter.dao.FollowDao;
import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FollowServlet", urlPatterns = {"/follow"})
public class FollowServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(FollowServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST");

        String userToFollow = request.getParameter("user");

        String loginUser = (String) request.getSession().getAttribute(Constantes.LOGIN);

        FollowDao.addFollow(loginUser,userToFollow);
        FollowDao.addFollower(userToFollow,loginUser);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET");

        String query = request.getParameter(Constantes.QUERY);

        log.info("query = " + query);

        String loginUser = (String) request.getSession().getAttribute(Constantes.LOGIN);

        List<String> listRes;

        if(query.equals("follower")) {
            listRes = FollowDao.getFollower(loginUser);
        } else {
            listRes = FollowDao.getFollowing(loginUser);
        }

        response.getWriter().print(listRes);
    }
}
