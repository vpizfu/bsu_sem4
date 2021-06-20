package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Bill;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface BillDaoInterface {
    Collection<Bill> getAll() throws DaoException;
    Bill get(Integer key) throws DaoException;
    void create(Bill object) throws DaoException;
    void update(Bill object) throws DaoException;
    void delete(Bill object) throws DaoException;
}
