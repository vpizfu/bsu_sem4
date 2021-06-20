package web.sem2.lab1.controllers.jdbc;

import web.sem2.lab1.controllers.DbConfigurationManager;
import web.sem2.lab1.exceptions.JdbcConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class JdbcConnectionPool {
    private static class PoolHolder {
        public static final JdbcConnectionPool INSTANCE = new JdbcConnectionPool();
    }

    public static JdbcConnectionPool getInstance() {
        return PoolHolder.INSTANCE;
    }

    private static final int START_CONNECTIONS = 4;

    private Queue<Connection> pool;
    private DbConfigurationManager manager;

    private JdbcConnectionPool() {
        pool = new LinkedList<>();
        manager = new DbConfigurationManager();

        try {
            Class.forName(manager.getDriver());
        } catch (Exception ex) {
            throw new RuntimeException("the driver couldn't be loaded", ex);
        }

        for (int i = 0; i < START_CONNECTIONS; ++i) {
            try {
                openAndAddNewConnection();
            } catch (JdbcConnectionException ex) {
                throw new RuntimeException("can't create connections", ex);
            }
        }
    }

    private void openAndAddNewConnection() throws JdbcConnectionException {
        Connection conn;

        try {
            conn = DriverManager.getConnection(manager.getUrl(), manager.getUser(), manager.getPassword());
        } catch (SQLException ex) {
            throw new JdbcConnectionException("can't connect to database", ex);
        }

        if (conn == null) {
            throw new JdbcConnectionException("driver type isn't correct here: " + manager.getUrl());
        }

        pool.add(conn);
    }

    public synchronized Connection getConnection() throws JdbcConnectionException {
        if (pool.isEmpty())
            openAndAddNewConnection();
        return pool.poll();
    }

    public synchronized void releaseConnection(Connection conn) throws JdbcConnectionException {
        try {
            if (conn == null || conn.isClosed())
                openAndAddNewConnection();
            else
                pool.add(conn);
        } catch (SQLException ex) {
            throw new JdbcConnectionException("can't return the connection", ex);
        }
    }

    public synchronized void closeAllConnections() throws JdbcConnectionException {
        for (Connection conn : pool) {
            try {
                conn.close();
            } catch (Exception ex) {
                throw new JdbcConnectionException("can't close connections", ex);
            }
        }
    }
}

