package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Ingredient;

public class IngredientDao extends Dao<Ingredient, Integer> {
    public IngredientDao() throws DaoException {
        super(Ingredient.class);
    }
}
