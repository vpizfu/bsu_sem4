package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.BillElement_;

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
@Path("/billElement")
public class BillElementDao extends Dao<BillElement, Integer> implements BillElementDaoInterface {
    public BillElementDao() throws DaoException {
        super(BillElement.class, "billId");
    }

    @Override
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BillElement> getAll() throws DaoException {
        return super.getAll();
    }

    @PUT
    @Path("{bill_id}/{drink_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("bill_id") int billId, @PathParam("drink_id") int drinkId, BillElement object) throws DaoException {
        super.create(object);
    }

    @POST
    @Path("{bill_id}/{drink_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("bill_id") int billId, @PathParam("drink_id") int drinkId, BillElement object) throws DaoException {
        super.update(object);
    }

    @DELETE
    @Path("{bill_id}/{drink_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("bill_id") int billId, @PathParam("drink_id") int drinkId, BillElement object) throws DaoException {
        super.delete(object);
    }

    @Override
    public BillElement get(Integer key) throws DaoException {
        throw new DaoException("can't get one element with composite primary key");
    }

    @GET
    @Path("{bill_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BillElement> getByBill(@PathParam("bill_id") Integer billId) throws DaoException {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BillElement> getAllCriteriaQuery = criteriaBuilder.createQuery(clazz);
            Root<BillElement> root = getAllCriteriaQuery.from(clazz);
            getAllCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get(BillElement_.billId), billId));
            TypedQuery<BillElement> getAllQuery = entityManager.createQuery(getAllCriteriaQuery);
            return getAllQuery.getResultList();
        } catch (Exception e) {
            throw new DaoException("can't get all bill elements with the specified number", e);
        }
    }
}
