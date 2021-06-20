package web.sem2.lab1.models.dao;

import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.Ingredient;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;

public class IngredientDaoConsumer extends DaoConsumer<Ingredient, Integer> implements IngredientDaoInterface {
    @Override
    protected Integer getKey(Ingredient object) {
        return object.getId();
    }

    public IngredientDaoConsumer() {
        super("ingredient", Ingredient.class, new GenericType<ArrayList<Ingredient>>() {});
    }
}
