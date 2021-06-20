package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Ingredient;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface IngredientDaoInterface {
    Collection<Ingredient> getAll() throws DaoException;
    Ingredient get(Integer key) throws DaoException;
    void create(Ingredient object) throws DaoException;
    void update(Ingredient object) throws DaoException;
    void delete(Ingredient object) throws DaoException;
}
