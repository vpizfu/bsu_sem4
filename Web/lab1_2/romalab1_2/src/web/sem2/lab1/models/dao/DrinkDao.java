package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Drink;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrinkDao extends Dao<Drink, Integer> {
    private static final String DRINK_CREATE = "INSERT INTO drinks (id, name, cost) VALUES (?, ?, ?);";
    private static final String DRINK_UPDATE = "UPDATE drinks SET name = ?, cost = ? WHERE id = ?;";

    private final PreparedStatement DRINK_CREATE_STMT;
    private final PreparedStatement DRINK_UPDATE_STMT;

    public DrinkDao() throws DaoException {
        super("drinks", "id");

        try {
            DRINK_CREATE_STMT = conn.prepareStatement(DRINK_CREATE);
            DRINK_UPDATE_STMT = conn.prepareStatement(DRINK_UPDATE);
        } catch (SQLException e) {
            throw new DaoException("can't compile statements", e);
        }
    }

    @Override
    protected Drink mapRowToObj(ResultSet set) throws SQLException {
        Drink drink = new Drink();
        drink.setId(set.getInt("id"));
        drink.setName(set.getString("name"));
        drink.setCost(set.getDouble("cost"));
        return drink;
    }

    @Override
    protected Integer getKeyFromObj(Drink obj) {
        return obj.getId();
    }

    @Override
    protected PreparedStatement createPreparedStmtForObj(Drink obj) throws SQLException {
        DRINK_CREATE_STMT.setInt(1, obj.getId());
        DRINK_CREATE_STMT.setString(2, obj.getName());
        DRINK_CREATE_STMT.setDouble(3, obj.getCost());
        return DRINK_CREATE_STMT;
    }

    @Override
    protected PreparedStatement updatePreparedStmtForObj(Drink obj) throws SQLException {
        DRINK_UPDATE_STMT.setInt(3, obj.getId());
        DRINK_UPDATE_STMT.setString(1, obj.getName());
        DRINK_UPDATE_STMT.setDouble(2, obj.getCost());
        return DRINK_UPDATE_STMT;
    }
}
