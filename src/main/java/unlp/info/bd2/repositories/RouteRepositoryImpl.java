package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

import java.util.List;

@Repository
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private SessionFactory sessionFactory;

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
}

