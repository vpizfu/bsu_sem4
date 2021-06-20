package web.sem2.lab1.controllers;

import web.sem2.lab1.models.dao.*;
import web.sem2.lab1.models.entities.*;
import web.sem2.lab1.models.output.BillElementModel;
import web.sem2.lab1.models.output.BillOutputModel;
import web.sem2.lab1.models.output.RecipeElementModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LogicImpl {
//    private static Optional<Bill> findBillOfUser(User user) throws Exception {
//        try (BillDao billDao = new BillDao()) {
//            return billDao.getAll().stream()
//                    .filter(bill -> bill.getUserLogin().equals(user.getLogin()) && !bill.isPaid())
//                    .findFirst();
//        }
//    }
//
//    public static List<Drink> getDrinks() throws Exception {
//        try (DrinkDao drinkDao = new DrinkDao()) {
//            return drinkDao.getAll();
//        }
//    }
//
//    public static List<Ingredient> getIngredients() throws Exception {
//        try (IngredientDao ingredientDao = new IngredientDao()) {
//            return ingredientDao.getAll();
//        }
//    }
//
//    public static Optional<BillOutputModel> getBillInfoByUser(User user) throws Exception {
//        try (BillDao billDao = new BillDao(); BillElementDao billElementDao = new BillElementDao();
//             DrinkDao drinkDao = new DrinkDao()) {
//            Optional<Bill> userBill = findBillOfUser(user);
//            if (userBill.isEmpty()) {
//                return Optional.empty();
//            }
//            BillOutputModel outputModel = new BillOutputModel();
//            outputModel.setBill(userBill.get());
//            List<BillElementModel> billElements = billElementDao.getByBill(userBill.get().getId()).stream()
//                    .map(billElement -> {
//                        BillElementModel model = new BillElementModel();
//                        model.setBillElement(billElement);
//                        try {
//                            model.setDrink(drinkDao.get(billElement.getDrinkId()).orElseThrow());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        return model;
//                    })
//                    .collect(Collectors.toList());
//            outputModel.setBillElements(billElements);
//            return Optional.of(outputModel);
//        }
//    }
//
//    public static void fillIngredient(int ingredientNo, double delta) throws Exception {
//        try (IngredientDao ingredientDao = new IngredientDao()) {
//            Ingredient ingredient = ingredientDao.get(ingredientNo).orElseThrow();
//            ingredient.setUnits(ingredient.getUnits() + delta);
//            ingredientDao.update(ingredient);
//        }
//    }
//
//    public static void buyDrink(User user, int drinkNo, int amount) throws Exception {
//        try (BillDao billDao = new BillDao(); BillElementDao billElementDao = new BillElementDao();
//             IngredientDao ingredientDao = new IngredientDao(); RecipeDao recipeDao = new RecipeDao()) {
//            List<RecipeElementModel> recipe = recipeDao.getByDrink(drinkNo).stream()
//                    .map(recipeElement -> {
//                        RecipeElementModel recipeElementModel = new RecipeElementModel();
//                        recipeElementModel.setRecipeElement(recipeElement);
//                        try {
//                            recipeElementModel.setIngredient(ingredientDao.get(recipeElement.getIngredientId()).orElseThrow());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        return recipeElementModel;
//                    })
//                    .collect(Collectors.toList());
//            for (RecipeElementModel recipeElementModel : recipe) {
//                if (recipeElementModel.getIngredient().getUnits() < amount * recipeElementModel.getRecipeElement().getIngredientAmount()) {
//                    throw new Exception("can't create the requested drink");
//                }
//                recipeElementModel.getIngredient().setUnits(recipeElementModel.getIngredient().getUnits() -
//                        amount * recipeElementModel.getRecipeElement().getIngredientAmount());
//            }
//            Optional<Bill> userBill = findBillOfUser(user);
//            if (userBill.isEmpty()) {
//                Bill bill = new Bill();
//                bill.setUserLogin(user.getLogin());
//                bill.setPaid(false);
//                billDao.create(bill);
//                userBill = findBillOfUser(user);
//            }
//            BillElement billElement = new BillElement();
//            billElement.setBillId(userBill.get().getId());
//            billElement.setDrinkId(drinkNo);
//            billElement.setDrinkAmount(amount);
//            billElementDao.create(billElement);
//        }
//    }
}
