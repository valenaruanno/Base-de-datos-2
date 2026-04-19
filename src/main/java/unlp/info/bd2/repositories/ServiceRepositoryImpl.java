package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.Service;

import java.util.List;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    @Autowired
    private SessionFactory sessionFactory;

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
}

