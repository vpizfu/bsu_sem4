package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class Dao<T, PK> implements AutoCloseable, DaoInterface<T, PK> {
    protected EntityManager entityManager;
    protected Class<T> clazz;
    private String pkName;

    protected Dao(Class<T> clazz, String pkName) throws DaoException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        this.clazz = clazz;
        this.pkName = pkName;
    }

    @Override
    public List<T> getAll() throws DaoException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> getAllCriteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = getAllCriteriaQuery.from(clazz);
        getAllCriteriaQuery.select(root);
        TypedQuery<T> getAllQuery = entityManager.createQuery(getAllCriteriaQuery);
        try {
            return getAllQuery.getResultList();
        } catch (Exception e) {
            throw new DaoException("can't get all entities", e);
        }
    }

    @Override
    public Optional<T> get(PK key) throws DaoException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> getAllCriteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = getAllCriteriaQuery.from(clazz);
        getAllCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get(pkName), key));
        TypedQuery<T> getAllQuery = entityManager.createQuery(getAllCriteriaQuery);
        List<T> result = getAllQuery.getResultList();
        if (result == null || result.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Override
    public void create(T object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoException("can't create the INSERT query", e);
        }
    }

    @Override
    public void update(T object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoException("can't create the UPDATE query", e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DaoException("can't create the DELETE query", e);
        }
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
