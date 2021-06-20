package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.BillElement;

import javax.ejb.Remote;
import java.util.Collection;
import java.util.List;

@Remote
public interface BillElementDaoInterface {
    Collection<BillElement> getAll() throws DaoException;
    BillElement get(Integer key) throws DaoException;
    void create(BillElement object) throws DaoException;
    void update(BillElement object) throws DaoException;
    void delete(BillElement object) throws DaoException;
    List<BillElement> getByBill(Integer billId) throws DaoException;
}