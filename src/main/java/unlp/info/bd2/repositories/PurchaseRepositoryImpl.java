package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.Purchase;

import java.util.Date;
import java.util.List;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Purchase createPurchase(Purchase purchase) {
        sessionFactory.getCurrentSession().save(purchase);
        return purchase;
    }

    @Override
    public Purchase findPurchase(long id) {
        return sessionFactory.getCurrentSession().get(Purchase.class, id);
    }

    @Override
    public List<Purchase> findPurchases() {
        return sessionFactory.getCurrentSession().createQuery("FROM Purchase", Purchase.class).list();
    }

    @Override
    public void deletePurchase(long id) {
        Purchase purchase = findPurchase(id);
        if (purchase != null) {
            sessionFactory.getCurrentSession().delete(purchase);
        }
    }

    @Override
    public List<Purchase> getPurchasesByRouteId(Long id) {
        return sessionFactory.getCurrentSession().createQuery("FROM Purchase p WHERE p.route.id = :routeId", Purchase.class)
                .setParameter("routeId", id)
                .list();

    }

    @Override
    public List<Purchase> getAllPurchasesOfUsername(String username) {
        return sessionFactory.getCurrentSession().createQuery("SELECT p FROM Purchase p WHERE p.user.username = :username", Purchase.class)
                .setParameter("username", username)
                .list();
    }

    @Override
    public int getCountOfPurchasesBetweenDates(Date start, Date end) {
        return sessionFactory.getCurrentSession().createQuery("SELECT COUNT(p) FROM Purchase p WHERE p.date >= :start and p.date <= :end", Long.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .uniqueResult().intValue();
    }
}

