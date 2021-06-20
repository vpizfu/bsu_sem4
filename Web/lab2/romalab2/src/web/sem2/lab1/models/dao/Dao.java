package web.sem2.lab1.models.dao;

import web.sem2.lab1.controllers.jdbc.JdbcConnectionPool;
import web.sem2.lab1.controllers.jdbc.JdbcConnector;
import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.exceptions.JdbcConnectionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Dao<T, PK> implements AutoCloseable, DaoInterface<T, PK> {
    protected EntityManager entityManager;
    protected Class<T> clazz;

    protected Dao(Class<T> clazz) throws DaoException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        this.clazz = clazz;
    }

    @Override
    public List<T> getAll() throws DaoException {
        TypedQuery<T> query = entityManager.createNamedQuery(clazz.getSimpleName() + ".getAll", clazz);
        return query.getResultList();
    }

    @Override
    public Optional<T> get(PK key) throws DaoException {
        TypedQuery<T> query = entityManager.createNamedQuery(clazz.getSimpleName() + ".getByPK", clazz);
        query.setParameter("id", key);
        List<T> result = query.getResultList();
        if (result == null || result.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Override
    public void create(T object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoException("can't create the INSERT query", e);
        }
    }

    @Override
    public void update(T object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoException("can't create the UPDATE query", e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoException("can't create the DELETE query", e);
        }
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
