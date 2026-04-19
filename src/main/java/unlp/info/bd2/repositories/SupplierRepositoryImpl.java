package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.Supplier;

import java.util.List;

@Repository
public class SupplierRepositoryImpl implements SupplierRepository {

    @Autowired
    private SessionFactory sessionFactory;

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
}

