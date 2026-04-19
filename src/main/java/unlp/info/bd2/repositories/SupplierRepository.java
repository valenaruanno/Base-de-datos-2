package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Supplier;

import java.util.List;

public interface SupplierRepository {
    public Supplier createSupplier(Supplier supplier);
    public Supplier findSupplier(long id);
    public List<Supplier> findSuppliers();
    public void deleteSupplier(long id);
    List<Supplier> getTopNSuppliersInPurchases(int n);
}

