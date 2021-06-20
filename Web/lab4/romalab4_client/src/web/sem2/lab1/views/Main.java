package web.sem2.lab1.views;

import web.sem2.lab1.controllers.LogicImpl;
import web.sem2.lab1.models.dao.*;
import web.sem2.lab1.models.entities.Drink;
import web.sem2.lab1.models.entities.Ingredient;
import web.sem2.lab1.models.entities.User;
import web.sem2.lab1.models.output.BillOutputModel;

import javax.ejb.EJB;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    @EJB
    private static BillDaoInterface billDao;
    @EJB
    private static BillElementDaoInterface billElementDao;
    @EJB
    private static DrinkDaoInterface drinkDao;
    @EJB
    private static IngredientDaoInterface ingredientDao;
    @EJB
    private static RecipeDaoInterface recipeDao;
    @EJB
    private static UserDaoInterface userDao;

    public static void main(String[] args) {
        LogicImpl.getInstance().setBillDao(billDao);
        LogicImpl.getInstance().setBillElementDao(billElementDao);
        LogicImpl.getInstance().setDrinkDao(drinkDao);
        LogicImpl.getInstance().setIngredientDao(ingredientDao);
        LogicImpl.getInstance().setRecipeDao(recipeDao);
        LogicImpl.getInstance().setUserDao(userDao);

        User user = null;
        Scanner scanner = new Scanner(System.in);

        while (user == null) {
            String login, password;
            System.out.println("Login: ");
            login = scanner.nextLine();
            System.out.println("Password: ");
            password = scanner.nextLine();

            try {
                Optional<User> optUser = LogicImpl.getInstance().getUserByLogin(login);
                if (!optUser.isPresent()) {
                    System.out.println("No such user");
                    continue;
                }
                if (!optUser.get().getPassword().equals(password)) {
                    System.out.println("The passwords do not match");
                    continue;
                }
                user = optUser.get();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("An error has occurred: " + e.getMessage());
            }
        }

        for (;;) {
            System.out.println("0 - exit");
            System.out.println("1 - show all drinks");
            System.out.println("2 - show all ingredients");
            System.out.println("3 - show the bill of a user");
            System.out.println("4 - order a drink");
            if (user.isAdmin()) {
                System.out.println("5 - fill the ingredient");
            }

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 5 || (choice == 5 && !user.isAdmin())) {
                    System.err.println("Incorrect choice");
                }
            } catch (NumberFormatException e) {
                System.err.println("Incorrect choice");
                continue;
            }

            try {
                switch (choice) {
                    case 0:
                        System.exit(0);
                    case 1:
                        for (Drink drink : LogicImpl.getInstance().getDrinks())
                            System.out.println(drink);
                        break;
                    case 2:
                        for (Ingredient ingredient : LogicImpl.getInstance().getIngredients())
                            System.out.println(ingredient);
                        break;
                    case 3: {
                        String login;
                        User chosenUser;
                        Optional<BillOutputModel> model;
                        if (user.isAdmin()) {
                            System.out.println("Login: ");
                            login = scanner.nextLine();
                            chosenUser = LogicImpl.getInstance().getUserByLogin(login).get();
                        } else {
                            chosenUser = user;
                        }
                        model = LogicImpl.getInstance().getBillInfoByUser(chosenUser);
                        if (!model.isPresent()) {
                            System.out.println("No pending bill");
                            continue;
                        }
                        System.out.println(model.get());
                        break;
                    }
                    case 4: {
                        System.out.println("Drink number: ");
                        int drinkNo = Integer.parseInt(scanner.nextLine());
                        System.out.println("Amount: ");
                        int amount = Integer.parseInt(scanner.nextLine());
                        LogicImpl.getInstance().buyDrink(user, drinkNo, amount);
                        break;
                    }
                    case 5:
                        System.out.println("Ingredient number: ");
                        int ingredientNo = Integer.parseInt(scanner.nextLine());
                        System.out.println("Delta: ");
                        double delta = Double.parseDouble(scanner.nextLine());
                        LogicImpl.getInstance().fillIngredient(ingredientNo, delta);
                        break;
                }
            } catch (Exception e) {
                System.err.println("An error has occurred: " + e);
            }
        }
    }
}
