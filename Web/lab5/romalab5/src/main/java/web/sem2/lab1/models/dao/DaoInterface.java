package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface DaoInterface<T extends Serializable, PK extends Serializable> {
    Collection<T> getAll() throws DaoException;
    T get(PK key) throws DaoException;
    void create(T object) throws DaoException;
    void update(T object) throws DaoException;
    void delete(T object) throws DaoException;
}
