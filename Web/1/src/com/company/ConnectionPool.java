package com.company;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;

/**
 * Stores unused connections to db. Provide synchronization.
 */
public class ConnectionPool {

    private ArrayDeque<Connection> poolConnection = new ArrayDeque<>();

    private static ConnectionPool instance;

    private Logger logger;

    private ConnectionPool() {
        logger = Logger.getLogger("PoolLogger");
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Gets new connection from pool.
     *
     * @return
     * @throws SQLException
     */
    public synchronized Connection getConnection() throws SQLException {
        var connection = poolConnection.poll();

        logger.info("New connection request");

        if (connection == null) {

            logger.info("No connections in the pool. Creating new one.");

            Properties props = new Properties();
            try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
                props.load(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            String url = props.getProperty("url");

            connection = DriverManager.getConnection(url);
        }

        return connection;

    }

    /**
     * Returns connection to pool.
     *
     * @param connection
     */
    public synchronized void closeConnection(Connection connection) {

        logger.info("Close connection request");
        poolConnection.add(connection);
    }


}
