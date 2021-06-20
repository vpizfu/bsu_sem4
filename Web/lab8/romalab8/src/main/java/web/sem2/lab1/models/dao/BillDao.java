package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/bill")
public class BillDao extends Dao<Bill, Integer> {
    public BillDao() throws DaoException {
        super(Bill.class, "id");
    }

    @Override
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bill> getAll() throws DaoException {
        return super.getAll();
    }

    @Override
    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bill get(@PathParam("key") Integer key) throws DaoException {
        return super.get(key);
    }

    @PUT
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("key") int key, Bill object) throws DaoException {
        super.create(object);
    }

    @POST
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("key") int key, Bill object) throws DaoException {
        super.update(object);
    }

    @DELETE
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("key") int key, Bill object) throws DaoException {
        super.delete(object);
    }
}
