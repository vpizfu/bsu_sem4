package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.User;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface UserDaoInterface {
    Collection<User> getAll() throws DaoException;
    User get(String key) throws DaoException;
    void create(User object) throws DaoException;
    void update(User object) throws DaoException;
    void delete(User object) throws DaoException;
}
