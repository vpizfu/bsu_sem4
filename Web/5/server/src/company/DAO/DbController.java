package company.DAO;


import company.models.Client;
import company.models.Client_;
import company.models.Tour;
import company.models.Tour_;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * Implements operations with db.
 */
@Stateless
public class DbController implements TourAgencyDbController {

    @PersistenceContext
    public EntityManager manager;

    public DbController() throws SQLException {
    }


    @Override
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

    @Override
    public ArrayList<Tour> getAllTours(int clientId) {

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

    @Override
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

    @Override
    public ArrayList<Client> getAllClients() {

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

        return new ArrayList<Client>(result);

    }

    @Override
    public boolean bookTour(int tourId, int clientId) {

//        logger.info("Booking tour " + tourId + " for client " + clientId);


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

    @Override
    public boolean setDiscount(int ordersCount, int discountSize) {

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



        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        finally {
            em.close();
        }


        return true;

    }

}
