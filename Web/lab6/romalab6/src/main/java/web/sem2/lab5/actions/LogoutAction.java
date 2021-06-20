package web.sem2.lab5.actions;

import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutAction implements Action {

    @Override
    public String getName() {
        return ActionNames.LOGOUT;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        RequestDispatcher dispatcher = ctx.getRequestDispatcher(JspNavigation.INDEX_NO_LOGIN_PAGE);
        req.getSession().setAttribute("user", null);
        dispatcher.forward(req, resp);
    }
}
