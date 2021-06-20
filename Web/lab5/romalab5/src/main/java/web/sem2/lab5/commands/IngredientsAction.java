package web.sem2.lab5.commands;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.dao.DrinkDaoInterface;
import web.sem2.lab1.models.dao.IngredientDaoInterface;
import web.sem2.lab1.models.entities.Ingredient;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IngredientsAction implements Action {
    @Override
    public String getName() {
        return ActionNames.INGREDIENTS;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        IngredientDaoInterface ingredientDao = (IngredientDaoInterface) ctx.getAttribute("ingredientDao");
        try {
            req.setAttribute("ingredients", ingredientDao.getAll());
        } catch (DaoException e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher(JspNavigation.INGREDIENTS_PAGE).forward(req, resp);
    }
}
