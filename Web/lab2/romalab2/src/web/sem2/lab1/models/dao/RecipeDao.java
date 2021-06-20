package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.RecipeElement;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RecipeDao extends Dao<RecipeElement, Integer> {
    public RecipeDao() throws DaoException {
        super(RecipeElement.class);
    }

    @Override
    public Optional<RecipeElement> get(Integer key) throws DaoException {
        throw new DaoException("can't get a single recipe because of composite primary key");
    }
    
    public List<RecipeElement> getByDrink(Integer drinkId) throws DaoException {
        try {
            TypedQuery<RecipeElement> query = entityManager.createNamedQuery(clazz.getSimpleName() + ".getByPK", clazz);
            query.setParameter("id", drinkId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException("can't get all recipe elements with the specified number", e);
        }
    }
}
