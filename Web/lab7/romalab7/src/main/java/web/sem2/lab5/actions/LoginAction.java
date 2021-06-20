package web.sem2.lab5.actions;

import web.sem2.lab1.models.dao.UserDaoInterface;
import web.sem2.lab1.models.entities.User;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoginAction implements Action {
    @Override
    public String getName() {
        return ActionNames.LOGIN;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        RequestDispatcher dispatcher = ctx.getRequestDispatcher(JspNavigation.LOGIN_PAGE);
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        if (login != null && password != null) {
            UserDaoInterface userInterface = (UserDaoInterface) ctx.getAttribute("userDao");
            try {
                User user = userInterface.get(login);
                if (user != null && user.getPassword().equals(password)) {
                    req.getSession().setAttribute("user", user);
                    saveCookies(req, resp);
                    dispatcher = ctx.getRequestDispatcher(JspNavigation.INDEX_PAGE);
                }
                dispatcher.forward(req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        dispatcher.forward(req, resp);
    }

    private void saveCookies(HttpServletRequest req, HttpServletResponse resp) {

        Cookie lastEnterTime = new Cookie("lastEnterTime", LocalDateTime.now().toString());
        Cookie usageCount = new Cookie("usageCount", "1");

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usageCount")) {
                    int lastUsageCount = Integer.parseInt(cookie.getValue());
                    lastUsageCount += 1;
                    usageCount.setValue(Integer.toString(lastUsageCount));
                }
            }
        }

        resp.addCookie(lastEnterTime);
        resp.addCookie(usageCount);
    }
}
