package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.BillElement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class BillElementDaoConsumer extends DaoConsumer<BillElement, Integer> implements BillElementDaoInterface {
    @Override
    protected Integer getKey(BillElement object) {
        return object.getBillId();
    }

    public BillElementDaoConsumer() {
        super("billElement", BillElement.class, new GenericType<ArrayList<BillElement>>() {});
    }

    @Override
    public void create(BillElement object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(object.getBillId()))
                    .path(String.valueOf(object.getDrinkId()))
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
    public void update(BillElement object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(object.getBillId()))
                    .path(String.valueOf(object.getDrinkId()))
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
    public void delete(BillElement object) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(targetUri)
                    .path(String.valueOf(object.getBillId()))
                    .path(String.valueOf(object.getDrinkId()))
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
    public List<BillElement> getByBill(Integer billId) throws DaoException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(targetUri)
                    .path(String.valueOf(billId))
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<ArrayList<BillElement>>() {});
        } catch (Exception e) {
            throw new DaoException("can't get the entity", e);
        }
    }
}
