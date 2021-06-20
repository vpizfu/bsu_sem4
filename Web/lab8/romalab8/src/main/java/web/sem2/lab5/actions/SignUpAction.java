package web.sem2.lab5.actions;

import web.sem2.lab1.models.dao.UserDaoInterface;
import web.sem2.lab1.models.entities.User;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpAction implements Action {
    @Override
    public String getName() {
        return ActionNames.SIGN_UP;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        RequestDispatcher dispatcher = ctx.getRequestDispatcher(JspNavigation.SIGN_UP_PAGE);

        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        if (login != null && password != null) {
            UserDaoInterface userDao = (UserDaoInterface) ctx.getAttribute("userDao");
            try {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setAdmin(false);
                userDao.create(user);
                dispatcher = ctx.getRequestDispatcher(JspNavigation.LOGIN_PAGE);
                dispatcher.forward(req, resp);
                return;
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        dispatcher.forward(req, resp);
    }
}
