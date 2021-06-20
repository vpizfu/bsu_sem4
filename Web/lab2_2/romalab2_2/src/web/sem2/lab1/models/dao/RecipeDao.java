package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.RecipeElement;
import web.sem2.lab1.models.entities.RecipeElement_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class RecipeDao extends Dao<RecipeElement, Integer> {
    public RecipeDao() throws DaoException {
        super(RecipeElement.class, "drinkId");
    }

    @Override
    public Optional<RecipeElement> get(Integer key) throws DaoException {
        throw new DaoException("can't get a single recipe because of composite primary key");
    }
    
    public List<RecipeElement> getByDrink(Integer drinkId) throws DaoException {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RecipeElement> getAllCriteriaQuery = criteriaBuilder.createQuery(clazz);
            Root<RecipeElement> root = getAllCriteriaQuery.from(clazz);
            getAllCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get(RecipeElement_.drinkId), drinkId));
            TypedQuery<RecipeElement> getAllQuery = entityManager.createQuery(getAllCriteriaQuery);
            return getAllQuery.getResultList();
        } catch (Exception e) {
            throw new DaoException("can't get all recipe elements with the specified number", e);
        }
    }
}
