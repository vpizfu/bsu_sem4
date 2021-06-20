package web.sem2.lab5.actions;

import web.sem2.lab1.models.dao.*;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.User;
import web.sem2.lab1.models.output.RecipeElementModel;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BuyDrinkAction implements Action {
    @Override
    public String getName() {
        return ActionNames.BUY_DRINK;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher(JspNavigation.BUY_DRINK_PAGE);
        String drinkNoStr = req.getParameter("DrinkNo");
        String amountStr = req.getParameter("DrinkPortions");
        if (drinkNoStr == null || amountStr == null) {
            disp.forward(req, resp);
            return;
        }

        int drinkNo = Integer.parseInt(drinkNoStr);
        int amount = Integer.parseInt(amountStr);

        User user = (User) req.getSession().getAttribute("user");
        BillDaoInterface billDao = (BillDaoInterface) ctx.getAttribute("billDao");
        BillElementDaoInterface billElementDao = (BillElementDaoInterface) ctx.getAttribute("billElementDao");
        IngredientDaoInterface ingredientDao = (IngredientDaoInterface) ctx.getAttribute("ingredientDao");
        RecipeDaoInterface recipeDao = (RecipeDaoInterface) ctx.getAttribute("recipeDao");

        try {
            List<RecipeElementModel> recipe = recipeDao.getByDrink(drinkNo).stream()
                    .map(recipeElement -> {
                        RecipeElementModel recipeElementModel = new RecipeElementModel();
                        recipeElementModel.setRecipeElement(recipeElement);
                        try {
                            recipeElementModel.setIngredient(Optional.ofNullable(ingredientDao.get(recipeElement.getIngredientId())).get());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return recipeElementModel;
                    })
                    .collect(Collectors.toList());
            for (RecipeElementModel recipeElementModel : recipe) {
                if (recipeElementModel.getIngredient().getUnits() < amount * recipeElementModel.getRecipeElement().getIngredientAmount()) {
                    throw new Exception("can't create the requested drink");
                }
                recipeElementModel.getIngredient().setUnits(recipeElementModel.getIngredient().getUnits() -
                        amount * recipeElementModel.getRecipeElement().getIngredientAmount());
            }
            Optional<Bill> userBill = billDao.getAll().stream()
                    .filter(b -> b.getUserLogin().equals(user.getLogin()) && !b.isPaid())
                    .findFirst();
            if (!userBill.isPresent()) {
                Bill bill = new Bill();
                bill.setUserLogin(user.getLogin());
                bill.setPaid(false);
                billDao.create(bill);
                userBill = billDao.getAll().stream()
                        .filter(b -> b.getUserLogin().equals(user.getLogin()) && !b.isPaid())
                        .findFirst();
            }
            BillElement billElement = new BillElement();
            billElement.setBillId(userBill.get().getId());
            billElement.setDrinkId(drinkNo);
            billElement.setDrinkAmount(amount);
            billElementDao.create(billElement);
            disp.forward(req, resp);
        } catch (Exception e) {
            disp.forward(req, resp);
        }
    }
}
