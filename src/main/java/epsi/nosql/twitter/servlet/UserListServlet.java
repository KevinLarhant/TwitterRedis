package epsi.nosql.twitter.servlet;

import epsi.nosql.twitter.dao.UserDao;
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

@WebServlet(name = "UserListServlet", urlPatterns = {"/userList"})
public class UserListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UserListServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET");

        String query = request.getParameter(Constantes.QUERY);

        List<String> listUsers = UserDao.getAllUsers();

        if (query != null) {
            List<String> userFiltered = new ArrayList<>();
            for(String user : listUsers) {
                if(user.contains(query)){
                    userFiltered.add(user);
                }
            }
            listUsers = userFiltered;
        }

        response.getWriter().print(listUsers);
    }
}
