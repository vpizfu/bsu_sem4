package web.sem2.lab1.models.dao;

import web.sem2.lab1.controllers.jdbc.JdbcConnectionPool;
import web.sem2.lab1.controllers.jdbc.JdbcConnector;
import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.exceptions.JdbcConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Dao<T, PK> implements AutoCloseable, DaoInterface<T, PK> {
    private static final String TABLE_NAME_PLACEHOLDER = "__tableName__";
    private static final String PK_NAME_PLACEHOLDER = "__pkName__";

    private static final String GET_ALL = "SELECT * FROM " + TABLE_NAME_PLACEHOLDER + ";";
    private static final String GET_BY_PK = "SELECT * FROM " + TABLE_NAME_PLACEHOLDER + " WHERE " +
            PK_NAME_PLACEHOLDER + " = ?;";
    private static final String DELETE = "DELETE FROM " + TABLE_NAME_PLACEHOLDER + " WHERE " +
            PK_NAME_PLACEHOLDER + " = ?;";

    protected final PreparedStatement GET_ALL_STMT;
    protected final PreparedStatement GET_BY_PK_STMT;
    protected final PreparedStatement DELETE_STMT;

    protected Connection conn;

    protected String instantiateTemplateQuery(String template, String tableName, String pkName) {
        return template.replaceAll(TABLE_NAME_PLACEHOLDER, tableName).replaceAll(PK_NAME_PLACEHOLDER, pkName);
    }

    protected List<T> getSome(PreparedStatement getStmt) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (var result = getStmt.executeQuery()) {
            while (result.next()) {
                entities.add(mapRowToObj(result));
            }
        } catch (SQLException e) {
            throw new DaoException("error while obtaining all entities", e);
        }
        return entities;
    }

    private void modify(PreparedStatement modifyStmt) throws DaoException {
        try {
            if (modifyStmt.executeUpdate() != 1) {
                throw new DaoException("the entity wasn't modified");
            }
        } catch (SQLException e) {
            throw new DaoException("error while modifying the entity", e);
        }
    }

    protected abstract T mapRowToObj(ResultSet set) throws SQLException;

    protected abstract PK getKeyFromObj(T obj);

    protected abstract PreparedStatement createPreparedStmtForObj(T obj) throws SQLException;

    protected abstract PreparedStatement updatePreparedStmtForObj(T obj) throws SQLException;

    protected PreparedStatement deletePreparedStmtForObj(T obj) throws SQLException {
        DELETE_STMT.setObject(1, getKeyFromObj(obj));
        return DELETE_STMT;
    }

    protected Dao(String tableName, String pkName) throws DaoException {
        try {
            conn = JdbcConnectionPool.getInstance().getConnection();
        } catch (JdbcConnectionException e) {
            throw new DaoException("can't connect to the database", e);
        }

        try {
            GET_ALL_STMT = conn.prepareStatement(instantiateTemplateQuery(GET_ALL, tableName, pkName));
            GET_BY_PK_STMT = conn.prepareStatement(instantiateTemplateQuery(GET_BY_PK, tableName, pkName));
            DELETE_STMT = conn.prepareStatement(instantiateTemplateQuery(DELETE, tableName, pkName));
        } catch (SQLException e) {
            throw new DaoException("can't compile the statements", e);
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        return getSome(GET_ALL_STMT);
    }

    @Override
    public Optional<T> get(PK key) throws DaoException {
        try {
            GET_BY_PK_STMT.setObject(1, key);
        } catch (SQLException e) {
            throw new DaoException("can't form a query to get an entity", e);
        }
        var result = getSome(GET_BY_PK_STMT);
        return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public void create(T object) throws DaoException {
        try {
            modify(createPreparedStmtForObj(object));
        } catch (SQLException e) {
            throw new DaoException("can't create the INSERT query", e);
        }
    }

    @Override
    public void update(T object) throws DaoException {
        try {
            modify(updatePreparedStmtForObj(object));
        } catch (SQLException e) {
            throw new DaoException("can't create the INSERT query", e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        try {
            modify(deletePreparedStmtForObj(object));
        } catch (SQLException e) {
            throw new DaoException("can't create the INSERT query", e);
        }
    }

    @Override
    public void close() throws Exception {
        JdbcConnectionPool.getInstance().releaseConnection(conn);
    }
}
