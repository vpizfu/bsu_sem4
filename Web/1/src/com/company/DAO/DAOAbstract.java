package com.company.DAO;

import com.company.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Determines operations with database.
 */
public abstract class DAOAbstract {

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

    protected Connection connection;

    protected Logger logger;

    public DAOAbstract() throws SQLException {
        connection = ConnectionPool.getInstance().getConnection();
        logger = Logger.getLogger("ControllerLogger");
    }

    /**
     * Gets prepared statement for specified sql request.
     *
     * @param sql request
     * @return prepared sql statement
     */
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    /**
     * Gets prepared statement for specified sql request.
     *
     * @param
     * @return prepared sql statement
     */
    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
