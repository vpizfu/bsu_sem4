package web.sem2.lab1.controllers;

import web.sem2.lab1.models.dao.*;
import web.sem2.lab1.models.entities.*;
import web.sem2.lab1.models.output.BillElementModel;
import web.sem2.lab1.models.output.BillOutputModel;
import web.sem2.lab1.models.output.RecipeElementModel;

import javax.ejb.EJB;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LogicImpl {
    @EJB
    private BillDaoInterface billDao;
    @EJB
    private BillElementDaoInterface billElementDao;
    @EJB
    private DrinkDaoInterface drinkDao;
    @EJB
    private IngredientDaoInterface ingredientDao;
    @EJB
    private RecipeDaoInterface recipeDao;
    @EJB
    private UserDaoInterface userDao;

    private LogicImpl() {}

    private static class Holder {
         public static final LogicImpl INSTANCE = new LogicImpl();
    }

    public static LogicImpl getInstance() {
        return Holder.INSTANCE;
    }

    public void setBillDao(BillDaoInterface billDao) {
        this.billDao = billDao;
    }

    public void setBillElementDao(BillElementDaoInterface billElementDao) {
        this.billElementDao = billElementDao;
    }

    public void setDrinkDao(DrinkDaoInterface drinkDao) {
        this.drinkDao = drinkDao;
    }

    public void setIngredientDao(IngredientDaoInterface ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setRecipeDao(RecipeDaoInterface recipeDao) {
        this.recipeDao = recipeDao;
    }

    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    private Optional<Bill> findBillOfUser(User user) throws Exception {
        return billDao.getAll().stream()
                    .filter(bill -> bill.getUserLogin().equals(user.getLogin()) && !bill.isPaid())
                    .findFirst();
    }

    public Optional<User> getUserByLogin(String login) throws Exception {
        return Optional.ofNullable(userDao.get(login));
    }

    public Collection<Drink> getDrinks() throws Exception {
        return drinkDao.getAll();
    }

    public Collection<Ingredient> getIngredients() throws Exception {
        return ingredientDao.getAll();
    }

    public Optional<BillOutputModel> getBillInfoByUser(User user) throws Exception {
        Optional<Bill> userBill = findBillOfUser(user);
        if (!userBill.isPresent()) {
            return Optional.empty();
        }
        BillOutputModel outputModel = new BillOutputModel();
        outputModel.setBill(userBill.get());
        List<BillElementModel> billElements = billElementDao.getByBill(userBill.get().getId()).stream()
                .map(billElement -> {
                    BillElementModel model = new BillElementModel();
                    model.setBillElement(billElement);
                    try {
                        model.setDrink(Optional.ofNullable(drinkDao.get(billElement.getDrinkId())).get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return model;
                })
                .collect(Collectors.toList());
        outputModel.setBillElements(billElements);
        return Optional.of(outputModel);
    }

    public void fillIngredient(int ingredientNo, double delta) throws Exception {
        Ingredient ingredient = Optional.ofNullable(ingredientDao.get(ingredientNo)).get();
        ingredient.setUnits(ingredient.getUnits() + delta);
        ingredientDao.update(ingredient);
    }

    public void buyDrink(User user, int drinkNo, int amount) throws Exception {
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
        Optional<Bill> userBill = findBillOfUser(user);
        if (!userBill.isPresent()) {
            Bill bill = new Bill();
            bill.setUserLogin(user.getLogin());
            bill.setPaid(false);
            billDao.create(bill);
            userBill = findBillOfUser(user);
        }
        BillElement billElement = new BillElement();
        billElement.setBillId(userBill.get().getId());
        billElement.setDrinkId(drinkNo);
        billElement.setDrinkAmount(amount);
        billElementDao.create(billElement);
    }
}
