package web.sem2.lab1.controllers.jdbc;

import web.sem2.lab1.controllers.DbConfigurationManager;
import web.sem2.lab1.exceptions.JdbcConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector implements AutoCloseable {
    private Connection conn;
    private DbConfigurationManager manager;

    public JdbcConnector() {
        manager = new DbConfigurationManager();
    }

    public Connection getConn() throws JdbcConnectionException {
        if (conn == null) {
            try {
                Class.forName(manager.getDriver());
            } catch (Exception e) {
                throw new JdbcConnectionException("the driver couldn't be loaded", e);
            }

            try {
                conn = DriverManager.getConnection(manager.getUrl(), manager.getUser(), manager.getPassword());
            } catch (SQLException e) {
                throw new JdbcConnectionException("can't connect to database", e);
            }

            if (conn == null) {
                throw new JdbcConnectionException("driver type isn't correct here: " + manager.getUrl());
            }
        }

        return conn;
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new JdbcConnectionException("can't close connection", e);
            }
        }
    }
}
