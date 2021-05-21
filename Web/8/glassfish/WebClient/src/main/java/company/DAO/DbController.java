package company.DAO;


import company.models.Client;
import company.models.Tour;
import company.models.Tour_;
import javafx.util.Pair;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Implements operations with db.
 */
@Stateless
public class DbController {

    //    @PersistenceContext
    public EntityManager manager;


    javax.ws.rs.client.Client client;

    public DbController() throws SQLException {

        client = ClientBuilder.newClient();
    }


    public ArrayList<Tour> getAllTours() {

//        logger.info("Get all tours request");

        ArrayList<Tour> tours =
                client.target("http://localhost:8080/ServerBean/webapi/allTours")
                        .request(MediaType.APPLICATION_XML)
                        .get(ArrayList.class);

        return tours;
    }


    public List<Tour> getAllTours(int clientId) {

//        logger.info("Get all client tours request");

        List<Tour> tours =
                client.target("http://localhost:8080/RestGlassfishHelloWorld-1.0-SNAPSHOT/webapi/DAOController/allTours/{clientId}")
                        .resolveTemplate("clientId", clientId).request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<Tour>>() {
                        });

        return tours;
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


    public List<Client> getAllClients() {

//        logger.info("Get all clients request");

        List<Client> clients =
                client.target("http://localhost:8080/RestGlassfishHelloWorld-1.0-SNAPSHOT/webapi/DAOController/allClients")
                        .request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<Client>>() {
                        });

        return clients;

    }


    public void bookTour(int tourId, int clientId) {

//        logger.info("Booking tour " + tourId + " for client " + clientId);


        client.target("http://localhost:8080/RestGlassfishHelloWorld-1.0-SNAPSHOT/webapi/DAOController/bookTour/{tourId}/{clientId}")
                .resolveTemplate("tourId", tourId)
                .resolveTemplate("clientId", clientId)
                .request(MediaType.APPLICATION_JSON)
                .post(null);


    }


    public void setDiscount(int ordersCount, int discountSize) {

        
//        logger.info("Setting discount " + discountSize + " for client having more than " + ordersCount + " orders.");

        client.target("http://localhost:8080/RestGlassfishHelloWorld-1.0-SNAPSHOT/webapi/DAOController/setDiscount/{ordersCount}/{discountSize}")
                .resolveTemplate("ordersCount", ordersCount)
                .resolveTemplate("discountSize", discountSize)
                .request(MediaType.APPLICATION_JSON)
                .post(null);

    }

}
