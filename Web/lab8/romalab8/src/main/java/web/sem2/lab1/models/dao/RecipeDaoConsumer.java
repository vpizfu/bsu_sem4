package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.RecipeElement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class RecipeDaoConsumer extends DaoConsumer<RecipeElement, Integer> implements RecipeDaoInterface {
    @Override
    protected Integer getKey(RecipeElement object) {
        return object.getDrinkId();
    }

    public RecipeDaoConsumer() {
        super("recipe", RecipeElement.class, new GenericType<ArrayList<RecipeElement>>() {});
    }

    @Override
    public void create(RecipeElement object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(object.getDrinkId()))
                    .path(String.valueOf(object.getIngredientId()))
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(object, MediaType.APPLICATION_JSON), Response.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new Exception("the entity wasn't created");
            }
        } catch (Exception e) {
            throw new DaoException("can't create the entity", e);
        }
    }

    @Override
    public void update(RecipeElement object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(object.getDrinkId()))
                    .path(String.valueOf(object.getIngredientId()))
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(object, MediaType.APPLICATION_JSON), Response.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new Exception("the entity wasn't created");
            }
        } catch (Exception e) {
            throw new DaoException("can't create the entity", e);
        }
    }

    @Override
    public void delete(RecipeElement object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(object.getDrinkId()))
                    .path(String.valueOf(object.getIngredientId()))
                    .request(MediaType.APPLICATION_JSON)
                    .delete(Response.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new Exception("the entity wasn't created");
            }
        } catch (Exception e) {
            throw new DaoException("can't create the entity", e);
        }
    }

    @Override
    public List<RecipeElement> getByDrink(Integer drinkId) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(targetUri)
                    .path(String.valueOf(drinkId))
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<ArrayList<RecipeElement>>() {});
        } catch (Exception e) {
            throw new DaoException("can't get the entity", e);
        }
    }
}
