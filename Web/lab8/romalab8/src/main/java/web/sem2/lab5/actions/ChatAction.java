package web.sem2.lab5.actions;

import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatAction implements Action {
    @Override
    public String getName() {
        return ActionNames.CHAT;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        req.getRequestDispatcher(JspNavigation.CHAT_PAGE).forward(req, resp);
    }
}
