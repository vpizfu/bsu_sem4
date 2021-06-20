package web.sem2.lab5.actions;

import web.sem2.lab1.models.dao.DrinkDao;
import web.sem2.lab1.models.dao.DrinkDaoInterface;
import web.sem2.lab1.models.entities.Drink;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditDrinkAction implements Action {
    @Override
    public String getName() {
        return ActionNames.EDIT_DRINK;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher(JspNavigation.EDIT_DRINK_PAGE);

        String drinkNoStr = req.getParameter("DrinkNo");
        if (drinkNoStr == null) {
            throw new ServletException("drinkNo was null");
        }
        int drinkNo = Integer.parseInt(drinkNoStr);
        DrinkDaoInterface drinkDao = (DrinkDaoInterface) ctx.getAttribute("drinkDao");

        try {
            Drink drink = drinkDao.get(drinkNo);

            String drinkNameStr = req.getParameter("DrinkName");
            String drinkCostStr = req.getParameter("DrinkCost");

            if (drinkNameStr != null && drinkCostStr != null) {
                drink.setName(drinkNameStr);
                drink.setCost(Double.parseDouble(drinkCostStr));
                drinkDao.update(drink);
            }
            req.setAttribute("drink", drink);
            disp.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
