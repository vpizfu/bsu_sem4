package web.sem2.lab1.models.dao;

import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.entities.User;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;

public class UserDaoConsumer extends DaoConsumer<User, String> implements UserDaoInterface {
    @Override
    protected String getKey(User object) {
        return object.getLogin();
    }

    public UserDaoConsumer() {
        super("user", User.class, new GenericType<ArrayList<User>>() {});
    }
}
