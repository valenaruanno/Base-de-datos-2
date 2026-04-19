package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.Stop;

import java.util.List;

@Repository
public class StopRepositoryImpl implements StopRepository {

    @Autowired
    private SessionFactory sessionFactory;

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
}
