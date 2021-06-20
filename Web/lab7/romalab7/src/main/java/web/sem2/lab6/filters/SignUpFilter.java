package web.sem2.lab6.filters;

import web.sem2.lab1.models.dao.UserDaoInterface;
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

@WebFilter(filterName = "signUpFilter", urlPatterns = "/main")
public class SignUpFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;

        String action = req.getParameter("action");

        if (ActionNames.SIGN_UP.equals(action)) {
            String login = req.getParameter("Login");
            String password = req.getParameter("Password");
            String confirmation = req.getParameter("Confirmation");
            if (login != null && password != null && confirmation != null) {
                UserDaoInterface userDao = (UserDaoInterface) req.getServletContext().getAttribute("userDao");
                try {
                    User user = userDao.get(login);
                    if (user != null || !password.equals(confirmation)) {
                        httpResp.sendRedirect(httpReq.getContextPath() + "/main?action=" + ActionNames.INDEX_NO_LOGIN);
                        return;
                    }
                } catch (Exception e) {
                    throw new ServletException(e);
                }
            }
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
