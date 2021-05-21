package company.DAO;


import company.models.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Implements operations with db.
 */
@Path("/DAOController")
public class DAO {

    @PersistenceContext
    public EntityManager manager;

    public DAO() throws SQLException {
    }


    @GET
    @Path("allTours")
    @Produces("text/xml")
    public ArrayList<Tour> getAllTours() {

//        logger.info("Get all tours request");

        EntityManagerFactory fabric = Persistence.createEntityManagerFactory("TestPersistence");
        EntityManager em = fabric.createEntityManager();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tour> cq = cb.createQuery(Tour.class);
        Root<Tour> tour = cq.from(Tour.class);
        cq.select(tour);
        TypedQuery<Tour> q = em.createQuery(cq);
        List<Tour> result = q.getResultList();

        em.close();

        return new ArrayList<Tour>(result);
    }


    @GET
    @Path("/allTours/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Tour> getAllTours(@PathParam("clientId") int clientId) {

//        logger.info("Get all client tours request");

        EntityManagerFactory fabric = Persistence.createEntityManagerFactory("TestPersistence");
        EntityManager em = fabric.createEntityManager();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tour> cq = cb.createQuery(Tour.class);
        Root<Tour> tour = cq.from(Tour.class);
        ListJoin<Tour, Client> join = tour.join(Tour_.clients);

        Expression<Integer> expr = join.get(Client_.id);

        cq.select(tour).where(cb.equal(expr, clientId));

        TypedQuery<Tour> q = em.createQuery(cq);
        List<Tour> result = q.getResultList();


        return new ArrayList<Tour>(result);
    }


    public ArrayList<Tour> getHotTourInfo(Tour.type type) {

//        logger.info("Get all hot tours request");

        EntityManagerFactory fabric = Persistence.createEntityManagerFactory("TestPersistence");
        EntityManager em = fabric.createEntityManager();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tour> cq = cb.createQuery(Tour.class);
        Root<Tour> tour = cq.from(Tour.class);


        cq.select(tour).where(cb.equal(tour.get(Tour_.isHot), true)).where(cb.equal(tour.get(Tour_.type), type.toString()));

        TypedQuery<Tour> q = em.createQuery(cq);
        List<Tour> result = q.getResultList();

        em.close();

        return new ArrayList<Tour>(result);
    }

    @GET
    @Path("/test")
    @Produces("text/xml")
    public String testFunc() {
        return "Hello world";
    }


    @GET
    @Path("/allClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Client> getAllClients() {

//        logger.info("Get all clients request");

        EntityManagerFactory fabric = Persistence.createEntityManagerFactory("TestPersistence");
        EntityManager em = fabric.createEntityManager();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> client = cq.from(Client.class);
        cq.select(client);
        TypedQuery<Client> q = em.createQuery(cq);
        List<Client> result = q.getResultList();

        em.close();

        return new HashSet<Client>(result);

    }


    @POST
    @Path("/bookTour/{tourId}/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean bookTour(@PathParam("tourId") int tourId, @PathParam("clientId") int clientId) {

//        logger.info("Booking tour " + tourId + " for client " + clientId);

        System.out.println("Booking tour " + tourId + " for client " + clientId);

        EntityManagerFactory fabric = Persistence.createEntityManagerFactory("TestPersistence");
        EntityManager em = fabric.createEntityManager();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tour> cq = cb.createQuery(Tour.class);
        Root<Tour> tour = cq.from(Tour.class);

        Expression<Integer> expr = tour.get(Tour_.id);

        cq.select(tour).where(cb.equal(expr, tourId));

        CriteriaQuery<Client> clientCriteriaQuery = cb.createQuery(Client.class);

        Root<Client> clientRoot = clientCriteriaQuery.from(Client.class);

        Expression<Integer> exprClient = clientRoot.get(Client_.id);

        clientCriteriaQuery.select(clientRoot).where(cb.equal(exprClient, clientId));

        Client clientToAdd = em.createQuery((clientCriteriaQuery)).getSingleResult();

        TypedQuery<Tour> q = em.createQuery(cq);

        try {

            Tour result = q.getSingleResult();

            result.clients.add(clientToAdd);


            em.persist(result);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            em.close();
        }

        return true;

    }


    @POST
    @Path("/setDiscount/{ordersCount}/{discountSize}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean setDiscount(@PathParam("ordersCount") int ordersCount,@PathParam("discountSize") int discountSize) {

//        logger.info("Setting discount " + discountSize + " for client having more than " + ordersCount + " orders.");

        EntityManagerFactory fabric = Persistence.createEntityManagerFactory("TestPersistence");
        EntityManager em = fabric.createEntityManager();


        CriteriaBuilder cb = em.getCriteriaBuilder();


        CriteriaQuery<Client> clientCriteriaQuery = cb.createQuery(Client.class);

        Root<Client> clientRoot = clientCriteriaQuery.from(Client.class);

        Expression<List<Tour>> exprClient = clientRoot.get(Client_.tours);

        clientCriteriaQuery.select(clientRoot).where(cb.greaterThan(cb.size(exprClient), ordersCount));

        try {
            List<Client> clientsAll = em.createQuery((clientCriteriaQuery)).getResultList();


            for (Client cl : clientsAll
            ) {
                cl.setDiscount(discountSize);

                em.persist(cl);
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            em.close();
        }


        return true;

    }


    public User getUser(String login, String password) {
        return null;
    }


    public boolean registerUser(String login, String password) {
        return false;
    }

}
