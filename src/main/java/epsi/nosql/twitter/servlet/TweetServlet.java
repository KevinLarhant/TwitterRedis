package epsi.nosql.twitter.servlet;

import epsi.nosql.twitter.dao.TweetDao;
import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TweetServlet", urlPatterns = {"/tweeting"})
public class TweetServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(TweetServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST");

        String tweetMsg = request.getParameter("tweetMsg");
        String loginUser = (String) request.getSession().getAttribute(Constantes.LOGIN);

        TweetDao.newTweet(loginUser, tweetMsg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET");

        String loginUser = (String) request.getSession().getAttribute(Constantes.LOGIN);

        List<String> tweets = TweetDao.getAllTweets(loginUser);

        response.getWriter().print(tweets);
    }
}
