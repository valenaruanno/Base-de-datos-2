package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Purchase;

import java.util.Date;
import java.util.List;

public interface PurchaseRepository {
    public Purchase createPurchase(Purchase purchase);
    public Purchase findPurchase(long id);
    public List<Purchase> findPurchases();
    public void deletePurchase(long id);
    List<Purchase> getPurchasesByRouteId(Long id);
    List<Purchase> getAllPurchasesOfUsername(String username);
    int getCountOfPurchasesBetweenDates (Date start, Date end);
}

