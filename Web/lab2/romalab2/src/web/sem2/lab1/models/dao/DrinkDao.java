package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Drink;

public class DrinkDao extends Dao<Drink, Integer> {
    public DrinkDao() throws DaoException {
        super(Drink.class);
    }
}
