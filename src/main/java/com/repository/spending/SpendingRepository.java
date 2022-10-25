package com.repository.spending;

import com.model.Spending;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SpendingRepository implements ISpendingRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Spending> findAll() {
        TypedQuery<Spending> query = em.createQuery("Select p from Spending p", Spending.class);
        return query.getResultList();
    }


    @Override
    public Spending findById(Long id) {
        TypedQuery<Spending> query = em.createQuery("Select p from Spending as p where p.id = :id", Spending.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Spending spending) {
            if (spending.getId() == null) {
                em.persist(spending);
            }else {
                em.merge(spending);
            }
    }

    @Override
    public void delete(Long id) {
        Spending spending = this.findById(id);
        em.remove(spending);
    }

    @Override
    public List<Spending> findByName(String name) {
        TypedQuery<Spending> query = em.createQuery("Select p from Spending as p where p.name like :name", Spending.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
