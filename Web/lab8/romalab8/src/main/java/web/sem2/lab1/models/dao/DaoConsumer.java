package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class DaoConsumer<T extends Serializable, PK extends Serializable> implements DaoInterface<T, PK> {
    protected String targetUri = "http://localhost:8080/romalab5-1.0-SNAPSHOT/api/";
    protected Class<T> clazz;
    protected GenericType<ArrayList<T>> genericType;

    protected DaoConsumer(String path, Class<T> clazz, GenericType<ArrayList<T>> genericType) {
        targetUri += path;
        this.clazz = clazz;
        this.genericType = genericType;
    }

    protected abstract PK getKey(T object);

    @Override
    public Collection<T> getAll() throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(targetUri)
                    .path("all")
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(genericType);
        } catch (Exception e) {
            throw new DaoException("can't get all entities", e);
        }
    }

    @Override
    public T get(PK key) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(targetUri)
                    .path(String.valueOf(key))
                    .request(MediaType.APPLICATION_JSON)
                    .get(clazz);
        } catch (Exception e) {
            throw new DaoException("can't get the entity", e);
        }
    }

    @Override
    public void create(T object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(getKey(object)))
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
    public void update(T object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(getKey(object)))
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(object, MediaType.APPLICATION_JSON), Response.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new Exception("the entity wasn't updated");
            }
        } catch (Exception e) {
            throw new DaoException("can't update the entity", e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(getKey(object)))
                    .request(MediaType.APPLICATION_JSON)
                    .delete(Response.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new Exception("the entity wasn't deleted");
            }
        } catch (Exception e) {
            throw new DaoException("can't delete the entity", e);
        }
    }
}
