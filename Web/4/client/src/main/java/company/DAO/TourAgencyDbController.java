package company.DAO;

//import org.apache.log4j.Logger;

import company.models.Client;
import company.models.Tour;

import javax.ejb.Remote;
import java.util.ArrayList;


/**
 * Determines operations with database.
 */
@Remote
public interface TourAgencyDbController {

    /**
     * Gets info about all tours saved in db.
     *
     * @return
     */
    public abstract ArrayList<Tour> getAllTours();

    /**
     * Gets info about all tours saved in db for specified client.
     *
     * @return
     */
    public abstract ArrayList<Tour> getAllTours(int clientId);

    /**
     * Gets info about all hot tours of specified type saved in db.
     *
     * @return
     */
    public abstract ArrayList<Tour> getHotTourInfo(Tour.type type);

    /**
     * Gets info about all clients saved in db.
     *
     * @return
     */
    public abstract ArrayList<Client> getAllClients();

    /**
     * Books a tour for specified client.
     *
     * @return true if operation succeeds, false over wise.
     */
    public abstract boolean bookTour(int tourId, int clientId);

    /**
     * Sets discount value for all clients regarding the amount of orders they did.
     *
     * @return true if operation succeeds, false over wise.
     */
    public abstract boolean setDiscount(int ordersCount, int discountSize);

}
