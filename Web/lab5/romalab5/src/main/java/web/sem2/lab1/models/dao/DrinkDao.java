package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Drink;

import javax.ejb.Stateless;

@Stateless
public class DrinkDao extends Dao<Drink, Integer> implements DrinkDaoInterface {
    public DrinkDao() throws DaoException {
        super(Drink.class, "id");
    }
}
