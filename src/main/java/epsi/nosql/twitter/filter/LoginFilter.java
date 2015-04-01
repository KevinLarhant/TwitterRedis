package epsi.nosql.twitter.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Akto on 31/03/2015.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns={"/twitter/*"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession();

        Object login = session.getAttribute("login");

        if (login == null) {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
