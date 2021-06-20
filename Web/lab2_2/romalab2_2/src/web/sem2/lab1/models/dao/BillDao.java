package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;

public class BillDao extends Dao<Bill, Integer> {
    public BillDao() throws DaoException {
        super(Bill.class, "id");
    }
}
