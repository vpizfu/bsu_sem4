package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.BillElement;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BillElementDao extends Dao<BillElement, Integer> {
    public BillElementDao() throws DaoException {
        super(BillElement.class);
    }

    @Override
    public Optional<BillElement> get(Integer key) throws DaoException {
        throw new DaoException("can't get one element with composite primary key");
    }

    public List<BillElement> getByBill(Integer billId) throws DaoException {
        try {
            TypedQuery<BillElement> query = entityManager.createNamedQuery(clazz.getSimpleName() + ".getByPK", clazz);
            query.setParameter("id", billId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException("can't get all bill elements with the specified number", e);
        }
    }
}
