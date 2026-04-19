package unlp.info.bd2.repositories;

import unlp.info.bd2.model.*;

import java.util.Date;
import java.util.List;

public interface ToursRepository {
    //USER
    public User createUser(User user);
    public User findUser(long id);
    public List<User> findUsers();
    public void deleteUser(long id);
    List<User> getUserSpendingMoreThan(float mount);
    List<TourGuideUser> getTourGuidesWithRating1();

    //PURCHASE
    public Purchase createPurchase(Purchase purchase);
    public Purchase findPurchase(long id);
    public List<Purchase> findPurchases();
    public void deletePurchase(long id);
    List<Purchase> getPurchasesByRouteId(Long id);
    List<Purchase> getAllPurchasesOfUsername(String username);
    int getCountOfPurchasesBetweenDates (Date start, Date end);

    //REVIEW
    public Review createReview(Review review);
    public Review findReview(long id);
    public List<Review> findReviews();
    public void deleteReview(long id);

    //ROUTE
    public Route createRoute(Route route);
    public Route findRoute(long id);
    public List<Route> findRoutes();
    public void deleteRoute(long id);
    List<Route> getRoutesWithStop(Stop stop);
    int getMaxStopOfRoutes();
    List<Route> getRoutesNotSell();
    List<Route> getTop3RoutesWithMaxRating();

    //SERVICE
    Service createService(Service service);
    Service updateServicePrice(Long id, float price);
    Service findService(long id);
    List<Service> findServices();
    void deleteService(long id);
    Service getMostDemandedService();

    //STOP
    public Stop createStop(Stop stop);
    public Stop findStop(long id);
    public List<Stop> findStops();
    public void deleteStop(long id);

    //SUPPLIER
    public Supplier createSupplier(Supplier supplier);
    public Supplier findSupplier(long id);
    public List<Supplier> findSuppliers();
    public void deleteSupplier(long id);
    List<Supplier> getTopNSuppliersInPurchases(int n);


}
