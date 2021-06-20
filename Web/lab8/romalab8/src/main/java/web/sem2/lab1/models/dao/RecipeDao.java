package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.RecipeElement;
import web.sem2.lab1.models.entities.RecipeElement_;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Stateless
@Path("/recipe")
public class RecipeDao extends Dao<RecipeElement, Integer> implements RecipeDaoInterface {
    public RecipeDao() throws DaoException {
        super(RecipeElement.class, "drinkId");
    }

    @Override
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecipeElement> getAll() throws DaoException {
        return super.getAll();
    }

    @PUT
    @Path("{drink_id}/{ingredient_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("drink_id") int drinkId, @PathParam("ingredient_id") int ingredientId, RecipeElement object) throws DaoException {
        super.create(object);
    }

    @POST
    @Path("{drink_id}/{ingredient_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("drink_id") int drinkId, @PathParam("ingredient_id") int ingredientId, RecipeElement object) throws DaoException {
        super.update(object);
    }

    @DELETE
    @Path("{drink_id}/{ingredient_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("drink_id") int drinkId, @PathParam("ingredient_id") int ingredientId, RecipeElement object) throws DaoException {
        super.delete(object);
    }

    @Override
    public RecipeElement get(Integer key) throws DaoException {
        throw new DaoException("can't get a single recipe because of composite primary key");
    }

    @GET
    @Path("byDrink/{drink}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecipeElement> getByDrink(@PathParam("drink") Integer drinkId) throws DaoException {
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
