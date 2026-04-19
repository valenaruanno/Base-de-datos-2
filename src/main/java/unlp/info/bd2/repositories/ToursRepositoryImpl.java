package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.*;

import java.util.Date;
import java.util.List;

@Repository
public class ToursRepositoryImpl implements ToursRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //PURCHASE
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


    //REVIEW
    @Override
    public Review createReview(Review review) {
        sessionFactory.getCurrentSession().save(review);
        return review;
    }

    @Override
    public Review findReview(long id) {
        return sessionFactory.getCurrentSession().get(Review.class, id);
    }

    @Override
    public List<Review> findReviews() {
        return sessionFactory.getCurrentSession().createQuery("FROM Review", Review.class).list();
    }

    @Override
    public void deleteReview(long id) {
        Review review = findReview(id);
        if (review != null) {
            sessionFactory.getCurrentSession().delete(review);
        }
    }


    //ROUTE
    @Override
    public Route createRoute(Route route) {
        sessionFactory.getCurrentSession().save(route);
        return route;
    }

    @Override
    public Route findRoute(long id) {
        return sessionFactory.getCurrentSession().get(Route.class, id);
    }

    @Override
    public List<Route> findRoutes() {
        return sessionFactory.getCurrentSession().createQuery("FROM Route", Route.class).list();
    }

    @Override
    public void deleteRoute(long id) {
        Route route = findRoute(id);
        if (route != null) {
            sessionFactory.getCurrentSession().delete(route);
        }
    }

    @Override
    public List<Route> getRoutesWithStop(Stop stop) {
        return sessionFactory.getCurrentSession().createQuery("SELECT r FROM Route r JOIN r.stops s WHERE s = :stop", Route.class)
                .setParameter("stop", stop).getResultList();
    }

    @Override
    public int getMaxStopOfRoutes() {
        return sessionFactory.getCurrentSession().createQuery("SELECT COUNT(s) FROM Route r JOIN r.stops s GROUP BY r ORDER BY COUNT (s) DESC", Integer.class)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public List<Route> getRoutesNotSell() {
        return sessionFactory.getCurrentSession().createQuery("SELECT r FROM Route r WHERE NOT EXISTS (SELECT 1 FROM Purchase p WHERE p.route = r)", Route.class)
                .list();
    }

    @Override
    public List<Route> getTop3RoutesWithMaxRating() {
        return sessionFactory.getCurrentSession().createQuery("SELECT p.route FROM Review re JOIN re.purchase p  GROUP BY p.route ORDER BY avg(re.rating) DESC", Route.class)
                .setMaxResults(3)
                .list();
    }


    //SERVICE
    @Override
    public Service createService(Service service) {
        sessionFactory.getCurrentSession().save(service);
        return service;
    }

    @Override
    public Service updateServicePrice(Long id, float price) {
        Service service = findService(id);
        if (service != null) {
            service.setPrice(price);
            sessionFactory.getCurrentSession().update(service);
        }
        return service;
    }

    @Override
    public Service findService(long id) {
        return sessionFactory.getCurrentSession().get(Service.class, id);
    }

    @Override
    public List<Service> findServices() {
        return sessionFactory.getCurrentSession().createQuery("FROM Service", Service.class).list();
    }

    @Override
    public void deleteService(long id) {
        Service service = findService(id);
        if (service != null) {
            sessionFactory.getCurrentSession().delete(service);
        }
    }

    @Override
    public Service getMostDemandedService() {
        return sessionFactory.getCurrentSession().createQuery("SELECT i.service FROM ItemService i GROUP BY i.service ORDER BY SUM(i.quantity) DESC", Service.class)
                .setMaxResults(1)
                .uniqueResult();
    }


    //STOP
    @Override
    public Stop createStop(Stop stop) {
        sessionFactory.getCurrentSession().save(stop);
        return stop;
    }

    @Override
    public Stop findStop(long id) {
        return sessionFactory.getCurrentSession().get(Stop.class, id);
    }

    @Override
    public List<Stop> findStops() {
        return sessionFactory.getCurrentSession().createQuery("FROM Stop", Stop.class).list();
    }

    @Override
    public void deleteStop(long id) {
        Stop stop = findStop(id);
        if (stop != null) {
            sessionFactory.getCurrentSession().delete(stop);
        }
    }


    //SUPPLIER
    @Override
    public Supplier createSupplier(Supplier supplier) {
        sessionFactory.getCurrentSession().save(supplier);
        return supplier;
    }

    @Override
    public Supplier findSupplier(long id) {
        return sessionFactory.getCurrentSession().get(Supplier.class, id);
    }

    @Override
    public List<Supplier> findSuppliers() {
        return sessionFactory.getCurrentSession().createQuery("FROM Supplier", Supplier.class).list();
    }

    @Override
    public void deleteSupplier(long id) {
        Supplier supplier = findSupplier(id);
        if (supplier != null) {
            sessionFactory.getCurrentSession().delete(supplier);
        }
    }

    @Override
    public List<Supplier> getTopNSuppliersInPurchases(int n) {
        return sessionFactory.getCurrentSession().createQuery("FROM Supplier s JOIN s.services srv JOIN srv.itemServiceList i JOIN i.purchase p GROUP BY s ORDER BY COUNT (i) DESC", Supplier.class)
                .setMaxResults(n).list();
    }


    //USER
    @Override
    public User createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public User findUser(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> findUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).list();
    }

    @Override
    public void deleteUser(long id) {
        User user = findUser(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public List<User> getUserSpendingMoreThan(float mount) {
        return sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT u FROM User u JOIN u.purchaseList p WHERE p.totalPrice >= :mount", User.class)
                .setParameter("mount", mount)
                .list();
    }

    @Override
    public List<TourGuideUser> getTourGuidesWithRating1() {
        return sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT p.user FROM Review r JOIN r.purchase p WHERE r.rating = 1 AND TYPE(p.user) = TourGuideUser", TourGuideUser.class)
                .list();
    }
}
