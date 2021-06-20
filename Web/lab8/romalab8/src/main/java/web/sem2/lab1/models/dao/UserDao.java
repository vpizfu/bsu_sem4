package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.User;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/user")
public class UserDao extends Dao<User, String> implements UserDaoInterface {
    public UserDao() throws DaoException {
        super(User.class, "login");
    }

    @Override
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() throws DaoException {
        return super.getAll();
    }

    @Override
    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("key") String key) throws DaoException {
        return super.get(key);
    }

    @PUT
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("key") String key, User object) throws DaoException {
        super.create(object);
    }

    @POST
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("key") String key, User object) throws DaoException {
        super.update(object);
    }

    @DELETE
    @Path("{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("key") String key, User object) throws DaoException {
        super.delete(object);
    }
}
