package web.sem2.lab6.filters;

import web.sem2.lab1.models.entities.User;
import web.sem2.lab5.ActionNames;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter", urlPatterns = "/main")
public class LoginFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession sess = httpReq.getSession();

        User user = (User) sess.getAttribute("user");
        String action = req.getParameter("action");

        if (user == null && !(ActionNames.INDEX_NO_LOGIN.equals(action) ||
                              ActionNames.LOGIN.equals(action) ||
                              ActionNames.SIGN_UP.equals(action))) {
            httpResp.sendRedirect(httpReq.getContextPath() + "/main?action=" + ActionNames.INDEX_NO_LOGIN);
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
