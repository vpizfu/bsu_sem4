package web.sem2.lab1.models.dao;

import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.Drink;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;

public class DrinkDaoConsumer extends DaoConsumer<Drink, Integer> implements DrinkDaoInterface {
    @Override
    protected Integer getKey(Drink object) {
        return object.getId();
    }

    public DrinkDaoConsumer() {
        super("drink", Drink.class, new GenericType<ArrayList<Drink>>() {});
    }
}
