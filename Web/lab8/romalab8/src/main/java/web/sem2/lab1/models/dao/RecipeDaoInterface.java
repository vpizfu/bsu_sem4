package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.RecipeElement;

import javax.ejb.Remote;
import java.util.Collection;
import java.util.List;

@Remote
public interface RecipeDaoInterface {
    Collection<RecipeElement> getAll() throws DaoException;
    RecipeElement get(Integer key) throws DaoException;
    void create(RecipeElement object) throws DaoException;
    void update(RecipeElement object) throws DaoException;
    void delete(RecipeElement object) throws DaoException;
    List<RecipeElement> getByDrink(Integer drinkId) throws DaoException;
}
