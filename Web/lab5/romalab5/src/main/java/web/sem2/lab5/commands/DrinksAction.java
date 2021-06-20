package web.sem2.lab5.commands;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.dao.DrinkDao;
import web.sem2.lab1.models.dao.DrinkDaoInterface;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DrinksAction implements Action {
    @Override
    public String getName() {
        return ActionNames.DRINKS;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        DrinkDaoInterface drinkDao = (DrinkDaoInterface) ctx.getAttribute("drinkDao");
        try {
            req.setAttribute("drinks", drinkDao.getAll());
        } catch (DaoException e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher(JspNavigation.DRINKS_PAGE).forward(req, resp);
    }
}
