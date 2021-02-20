package com.company.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Implements operations with db.
 */
public class DAO extends DAOAbstract {

    private final String ALL_TOUR_REQUEST = "SELECT * FROM Tour";

    private final String ALL_TOUR_FOR_CLIENT_REQUEST =
            "SELECT location,price,tour_date_start, tour_date_end,Tour.ID,is_hot,type FROM Tour INNER JOIN client_orders " +
                    "ON Tour.ID = tour_id " +
                    "WHERE client_id = ?";
    private final String ALL_HOT_TOUR_FOR_TYPE_REQUEST =
            "SELECT * FROM Tour WHERE is_Hot = '1' AND type = ? ";

    private final String ALL_CLIENT_REQUEST = "SELECT * FROM client";

    private final String BOOK_TOUR_REQUEST = "INSERT INTO client_orders (client_id,tour_id) VALUES (?,?)";

    private final String SET_DISCOUNT_REQUEST =
            "UPDATE client\n" +
                    "SET discount = ?\n" +
                    "WHERE \n" +
                    " (SELECT ct FROM (SELECT DISTINCT client_id, COUNT()OVER(PARTITION BY client_id) as ct FROM client_orders WHERE client_id = client.ID)) > ?";

    public DAO() throws SQLException {
    }


    @Override
    public ArrayList<Tour> getAllTours() {

        logger.info("Get all tours request");

        var stmt = getPrepareStatement(ALL_TOUR_REQUEST);

        return getToursFromStatement(stmt);
    }

    @Override
    public ArrayList<Tour> getAllTours(int clientId) {

        logger.info("Get all client tours request");

        var stmt = getPrepareStatement(ALL_TOUR_FOR_CLIENT_REQUEST);

        try {
            stmt.setInt(1, clientId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
           return new ArrayList<Tour>();
        }
        return getToursFromStatement(stmt);
    }

    private ArrayList<Tour> getToursFromStatement(PreparedStatement stmt) {

        ArrayList<Tour> tours = new ArrayList<>();

        try {
            var result = stmt.executeQuery();

            while (result.next()) {

                tours.add(new Tour(result.getInt(5),
                        result.getString(1),
                        result.getFloat(2),
                        result.getString(3),
                        result.getString(4),
                        result.getBoolean(6),
                        Tour.type.valueOf(result.getString(7))));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closePrepareStatement(stmt);
        }

        return tours;
    }

    @Override
    public ArrayList<Tour> getHotTourInfo(Tour.type type) {

        logger.info("Get all hot tours request");

        var stmt = getPrepareStatement(ALL_HOT_TOUR_FOR_TYPE_REQUEST);

        try {
            stmt.setString(1, type.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return getToursFromStatement(stmt);
    }

    @Override
    public ArrayList<Client> getAllClients() {

        logger.info("Get all clients request");

        ArrayList<Client> clients = new ArrayList<>();

        var stmt = getPrepareStatement(ALL_CLIENT_REQUEST);


        try {
            var result = stmt.executeQuery();

            while (result.next()) {

                clients.add(new Client(result.getInt(1),
                        result.getString(2),
                        result.getInt(3)
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closePrepareStatement(stmt);
        }

        return clients;
    }

    @Override
    public boolean bookTour(int tourId, int clientId) {

        logger.info("Booking tour "+ tourId+" for client "+clientId);

        var stmt = getPrepareStatement(BOOK_TOUR_REQUEST);

        try {
            stmt.setInt(1, clientId);
            stmt.setInt(2, tourId);

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(stmt);
        }

        return true;
    }

    @Override
    public boolean setDiscount(int ordersCount, int discountSize) {

        logger.info("Setting discount "+ discountSize+" for client having more than "+ordersCount+" orders.");

        var stmt = getPrepareStatement(SET_DISCOUNT_REQUEST);

        try {
            stmt.setInt(1, discountSize);
            stmt.setInt(2, ordersCount);

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(stmt);
        }

        return true;
    }
}
