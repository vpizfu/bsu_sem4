package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Drink;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface DrinkDaoInterface {
    Collection<Drink> getAll() throws DaoException;
    Drink get(Integer key) throws DaoException;
    void create(Drink object) throws DaoException;
    void update(Drink object) throws DaoException;
    void delete(Drink object) throws DaoException;
}
