package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;

import javax.ejb.Stateless;

@Stateless
public class BillDao extends Dao<Bill, Integer> implements BillDaoInterface {
    public BillDao() throws DaoException {
        super(Bill.class, "id");
    }
}
