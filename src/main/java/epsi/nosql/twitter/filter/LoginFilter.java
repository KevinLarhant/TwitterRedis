package epsi.nosql.twitter.filter;

import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns={"/index","/index.jsp"})
public class LoginFilter implements Filter {

    Logger log = Logger.getLogger(LoginFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        log.info("Filtre : uri demand� :"+  ((HttpServletRequest)req).getRequestURI());
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession();

        Object login = session.getAttribute(Constantes.LOGIN);

        if (login == null) {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
