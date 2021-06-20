package web.sem2.lab5.actions;

import web.sem2.lab1.models.dao.IngredientDaoInterface;
import web.sem2.lab1.models.entities.Ingredient;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FillAction implements Action {
    @Override
    public String getName() {
        return ActionNames.FILL;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher(JspNavigation.FILL_PAGE);
        IngredientDaoInterface ingredientDao = (IngredientDaoInterface) ctx.getAttribute("ingredientDao");
        String ingredientNoStr = req.getParameter("IngredientNo");
        String deltaStr = req.getParameter("FillAmount");
        if (ingredientNoStr == null || deltaStr == null) {
            disp.forward(req, resp);
            return;
        }

        int ingredientNo = Integer.parseInt(ingredientNoStr);
        double delta = Double.parseDouble(deltaStr);
        try {
            Ingredient ingredient = ingredientDao.get(ingredientNo);
            if (ingredient == null) {
                disp.forward(req, resp);
                return;
            }
            ingredient.setUnits(ingredient.getUnits() + delta);
            ingredientDao.update(ingredient);
            disp.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
