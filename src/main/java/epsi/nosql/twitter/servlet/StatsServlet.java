package epsi.nosql.twitter.servlet;

import epsi.nosql.twitter.dao.FollowDao;
import epsi.nosql.twitter.dao.TweetDao;
import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StatsServlet", urlPatterns = {"/stats"})
public class StatsServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(StatsServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET");
        List<Integer> listStat = new ArrayList<>();

        String loginUser = (String) request.getSession().getAttribute(Constantes.LOGIN);

        listStat.add(TweetDao.getAllTweets(loginUser).size());
        listStat.add(TweetDao.getAllTweetsToDisplay(loginUser).size());
        listStat.add(FollowDao.getFollower(loginUser).size());
        listStat.add(FollowDao.getFollowing(loginUser).size());

        log.info("Stats : " + listStat);

        response.getWriter().print(listStat);
    }
}
