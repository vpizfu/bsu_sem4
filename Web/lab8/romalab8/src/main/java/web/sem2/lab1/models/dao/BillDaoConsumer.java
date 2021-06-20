package web.sem2.lab1.models.dao;

import web.sem2.lab1.models.entities.Bill;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;

public class BillDaoConsumer extends DaoConsumer<Bill, Integer> implements BillDaoInterface {
    @Override
    protected Integer getKey(Bill object) {
        return object.getId();
    }

    public BillDaoConsumer() {
        super("bill", Bill.class, new GenericType<ArrayList<Bill>>() {});
    }
}
