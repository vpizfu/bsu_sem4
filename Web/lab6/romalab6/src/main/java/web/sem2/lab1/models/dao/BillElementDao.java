package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.BillElement_;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Stateless
public class BillElementDao extends Dao<BillElement, Integer> implements BillElementDaoInterface {
    public BillElementDao() throws DaoException {
        super(BillElement.class, "billId");
    }

    @Override
    public BillElement get(Integer key) throws DaoException {
        throw new DaoException("can't get one element with composite primary key");
    }

    public List<BillElement> getByBill(Integer billId) throws DaoException {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BillElement> getAllCriteriaQuery = criteriaBuilder.createQuery(clazz);
            Root<BillElement> root = getAllCriteriaQuery.from(clazz);
            getAllCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get(BillElement_.billId), billId));
            TypedQuery<BillElement> getAllQuery = entityManager.createQuery(getAllCriteriaQuery);
            return getAllQuery.getResultList();
        } catch (Exception e) {
            throw new DaoException("can't get all bill elements with the specified number", e);
        }
    }
}
