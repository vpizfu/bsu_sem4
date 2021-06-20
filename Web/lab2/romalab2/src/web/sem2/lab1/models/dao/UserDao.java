package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.User;

public class UserDao extends Dao<User, String> {
    public UserDao() throws DaoException {
        super(User.class);
    }
}
