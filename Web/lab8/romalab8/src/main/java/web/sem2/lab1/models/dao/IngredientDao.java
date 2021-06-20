package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.Ingredient;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/ingredient")
public class IngredientDao extends Dao<Ingredient, Integer> implements IngredientDaoInterface {
    public IngredientDao() throws DaoException {
        super(Ingredient.class, "id");
    }

    @Override
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingredient> getAll() throws DaoException {
        return super.getAll();
    }

    @Override
    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient get(@PathParam("key") Integer key) throws DaoException {
        return super.get(key);
    }

    @PUT
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("key") int key, Ingredient object) throws DaoException {
        super.create(object);
    }

    @POST
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("key") int key, Ingredient object) throws DaoException {
        super.update(object);
    }

    @DELETE
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("key") int key, Ingredient object) throws DaoException {
        super.delete(object);
    }
}
