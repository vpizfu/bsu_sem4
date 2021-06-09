package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.BillElement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BillElementDao extends Dao<BillElement, Integer> {
    private static final String BILL_ELEMENT_CREATE = "INSERT INTO bills_drinks (bill_id, drink_id, drink_amount) VALUES (?, ?, ?);";
    private static final String BILL_ELEMENT_UPDATE = "UPDATE bills_drinks SET drink_amount = ? WHERE bill_id = ? AND drink_id = ?;";

    private final PreparedStatement BILL_ELEMENT_CREATE_STMT;
    private final PreparedStatement BILL_ELEMENT_UPDATE_STMT;

    public BillElementDao() throws DaoException {
        super("bills_drinks", "bill_id");

        try {
            BILL_ELEMENT_CREATE_STMT = conn.prepareStatement(BILL_ELEMENT_CREATE);
            BILL_ELEMENT_UPDATE_STMT = conn.prepareStatement(BILL_ELEMENT_UPDATE);
        } catch (SQLException e) {
            throw new DaoException("can't compile statements", e);
        }
    }

    @Override
    protected BillElement mapRowToObj(ResultSet set) throws SQLException {
        BillElement billElement = new BillElement();
        billElement.setBillId(set.getInt("bill_id"));
        billElement.setDrinkId(set.getInt("drink_id"));
        billElement.setDrinkAmount(set.getInt("drink_amount"));
        return billElement;
    }

    @Override
    protected Integer getKeyFromObj(BillElement obj) {
        return obj.getBillId();
    }

    @Override
    protected PreparedStatement createPreparedStmtForObj(BillElement obj) throws SQLException {
        BILL_ELEMENT_CREATE_STMT.setInt(1, obj.getBillId());
        BILL_ELEMENT_CREATE_STMT.setInt(2, obj.getDrinkId());
        BILL_ELEMENT_CREATE_STMT.setInt(3, obj.getDrinkAmount());
        return BILL_ELEMENT_CREATE_STMT;
    }

    @Override
    protected PreparedStatement updatePreparedStmtForObj(BillElement obj) throws SQLException {
        BILL_ELEMENT_UPDATE_STMT.setInt(2, obj.getBillId());
        BILL_ELEMENT_UPDATE_STMT.setInt(3, obj.getDrinkId());
        BILL_ELEMENT_UPDATE_STMT.setInt(1, obj.getDrinkAmount());
        return BILL_ELEMENT_UPDATE_STMT;
    }

    @Override
    public Optional<BillElement> get(Integer key) throws DaoException {
        throw new DaoException("can't get one element with composite primary key");
    }

    public List<BillElement> getByBill(Integer billId) throws DaoException {
        try {
            GET_BY_PK_STMT.setInt(1, billId);
            return getSome(GET_BY_PK_STMT);
        } catch (SQLException | DaoException e) {
            throw new DaoException("can't get all bill elements with the specified number", e);
        }
    }
}
