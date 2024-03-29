package spring.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class HibernatePSDao {

    public void save(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernatePSConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernatePSConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernatePSConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public <T> List<T> get(Class<T> type) {
        Session session = HibernatePSConfig.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        tx.commit();
        return data;
    }
}
