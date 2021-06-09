package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao<User, String> {
    private static final String USER_CREATE = "INSERT INTO users (login, password, is_admin) VALUES (?, ?, ?);";
    private static final String USER_UPDATE = "UPDATE users SET password = ?, is_admin = ? WHERE login = ?;";

    private final PreparedStatement USER_CREATE_STMT;
    private final PreparedStatement USER_UPDATE_STMT;

    public UserDao() throws DaoException {
        super("users", "login");

        try {
            USER_CREATE_STMT = conn.prepareStatement(USER_CREATE);
            USER_UPDATE_STMT = conn.prepareStatement(USER_UPDATE);
        } catch (SQLException e) {
            throw new DaoException("can't compile statements", e);
        }
    }
    
    @Override
    protected User mapRowToObj(ResultSet set) throws SQLException {
        User user = new User();
        user.setLogin(set.getString("login"));
        user.setPassword(set.getString("password"));
        user.setAdmin(set.getBoolean("is_admin"));
        return user;
    }

    @Override
    protected String getKeyFromObj(User obj) {
        return obj.getLogin();
    }

    @Override
    protected PreparedStatement createPreparedStmtForObj(User obj) throws SQLException {
        USER_CREATE_STMT.setString(1, obj.getLogin());
        USER_CREATE_STMT.setString(2, obj.getPassword());
        USER_CREATE_STMT.setBoolean(3, obj.isAdmin());
        return USER_CREATE_STMT;
    }

    @Override
    protected PreparedStatement updatePreparedStmtForObj(User obj) throws SQLException {
        USER_UPDATE_STMT.setString(3, obj.getLogin());
        USER_UPDATE_STMT.setString(1, obj.getPassword());
        USER_UPDATE_STMT.setBoolean(2, obj.isAdmin());
        return USER_UPDATE_STMT;
    }
}
