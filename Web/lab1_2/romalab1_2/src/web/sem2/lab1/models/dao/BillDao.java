package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDao extends Dao<Bill, Integer> {
    private static final String BILL_CREATE = "INSERT INTO bills (id, user_login, paid) VALUES (?, ?, ?);";
    private static final String BILL_UPDATE = "UPDATE bills SET user_login = ?, paid = ? WHERE id = ?;";

    private final PreparedStatement BILL_CREATE_STMT;
    private final PreparedStatement BILL_UPDATE_STMT;

    public BillDao() throws DaoException {
        super("bills", "id");

        try {
            BILL_CREATE_STMT = conn.prepareStatement(BILL_CREATE);
            BILL_UPDATE_STMT = conn.prepareStatement(BILL_UPDATE);
        } catch (SQLException e) {
            throw new DaoException("can't compile statements", e);
        }
    }

    @Override
    protected Bill mapRowToObj(ResultSet set) throws SQLException {
        Bill bill = new Bill();
        bill.setId(set.getInt("id"));
        bill.setUserLogin(set.getString("user_login"));
        bill.setPaid(set.getBoolean("paid"));
        return bill;
    }

    @Override
    protected Integer getKeyFromObj(Bill obj) {
        return obj.getId();
    }

    @Override
    protected PreparedStatement createPreparedStmtForObj(Bill obj) throws SQLException {
        BILL_CREATE_STMT.setInt(1, obj.getId());
        BILL_CREATE_STMT.setString(2, obj.getUserLogin());
        BILL_CREATE_STMT.setBoolean(3, obj.isPaid());
        return BILL_CREATE_STMT;
    }

    @Override
    protected PreparedStatement updatePreparedStmtForObj(Bill obj) throws SQLException {
        BILL_UPDATE_STMT.setInt(3, obj.getId());
        BILL_UPDATE_STMT.setString(1, obj.getUserLogin());
        BILL_UPDATE_STMT.setBoolean(2, obj.isPaid());
        return BILL_UPDATE_STMT;
    }
}
