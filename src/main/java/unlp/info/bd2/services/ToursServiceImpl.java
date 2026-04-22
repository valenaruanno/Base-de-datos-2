package unlp.info.bd2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import unlp.info.bd2.model.*;
import unlp.info.bd2.repositories.*;
import unlp.info.bd2.utils.ToursException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ToursServiceImpl implements ToursService{

    private ToursRepository toursRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public ToursServiceImpl() {}
    public ToursServiceImpl(ToursRepository toursRepository) {
        this.toursRepository = toursRepository;
    }

    @Override
    @Transactional
    public User createUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber) throws ToursException {
        if (username == null || username.isEmpty()) {
            throw new ToursException("El username no puede estar vacío");
        }
        if (email == null || email.isEmpty()) {
            throw new ToursException("El email no puede estar vacío");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(fullName);
        user.setEmail(email);
        user.setBirthdate(birthdate);
        user.setPhoneNumber(phoneNumber);
        user.setActive(true);

        return userRepository.createUser(user);
    }

    @Override
    @Transactional
    public DriverUser createDriverUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber, String expedient) throws ToursException {
        if (username == null || username.isEmpty()) {
            throw new ToursException("El username no puede estar vacío");
        }
        if (expedient == null || expedient.isEmpty()) {
            throw new ToursException("El expediente no puede estar vacío");
        }

        DriverUser driverUser = new DriverUser();
        driverUser.setUsername(username);
        driverUser.setPassword(password);
        driverUser.setName(fullName);
        driverUser.setEmail(email);
        driverUser.setBirthdate(birthdate);
        driverUser.setPhoneNumber(phoneNumber);
        driverUser.setExpedient(expedient);
        driverUser.setActive(true);

        return (DriverUser) userRepository.createUser(driverUser);
    }

    @Override
    @Transactional
    public TourGuideUser createTourGuideUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber, String education) throws ToursException {
        if (username == null || username.isEmpty()) {
            throw new ToursException("El username no puede estar vacío");
        }
        if (education == null || education.isEmpty()) {
            throw new ToursException("La educación no puede estar vacía");
        }

        TourGuideUser tourGuideUser = new TourGuideUser();
        tourGuideUser.setUsername(username);
        tourGuideUser.setPassword(password);
        tourGuideUser.setName(fullName);
        tourGuideUser.setEmail(email);
        tourGuideUser.setBirthdate(birthdate);
        tourGuideUser.setPhoneNumber(phoneNumber);
        tourGuideUser.setEducation(education);
        tourGuideUser.setActive(true);

        return (TourGuideUser) userRepository.createUser(tourGuideUser);
    }

    @Override
    public User getUserById(Long id) throws ToursException {
        User u = userRepository.findUser(id);
        if (u == null) {
            throw new ToursException("El usuario no existe");
        }
        return u;
    }

    @Override
    public User getUserByUsername(String username) throws ToursException {
        User u = userRepository.findUserByUsername(username);
        if  (u == null) {
            throw new ToursException("El usuario no existe");
        }
        return u;
    }

    @Override
    public User updateUser(User user) throws ToursException {
        if (userRepository.findUser(user.getId()) == null) {
            throw new ToursException("El usuario no existe");
        }
        return userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(User user) throws ToursException {
        if (user == null) {
            throw new ToursException("El usuario no puede ser nulo");
        }
        if (user.getId() == null) {
            throw new ToursException("El usuario no tiene identificador");
        }
        userRepository.deleteUser(user.getId());
    }

    @Override
    @Transactional
    public Stop createStop(String name, String description) throws ToursException {
        if (name == null || name.isEmpty()) {
            throw new ToursException("El nombre de la parada no puede estar vacío");
        }

        Stop stop = new Stop();
        stop.setName(name);
        stop.setDescription(description);

        return stopRepository.createStop(stop);
    }

    @Override
    public List<Stop> getStopByNameStart(String name) {
        return List.of();
    }

    @Override
    @Transactional
    public Route createRoute(String name, float price, float totalKm, int maxNumberOfUsers, List<Stop> stops) throws ToursException {
        if (name == null || name.isEmpty()) {
            throw new ToursException("El nombre de la ruta no puede estar vacío");
        }
        if (price <= 0) {
            throw new ToursException("El precio debe ser mayor a 0");
        }
        if (totalKm <= 0) {
            throw new ToursException("Los km totales deben ser mayores a 0");
        }

        Route route = new Route();
        route.setName(name);
        route.setPrice(price);
        route.setTotalKm(totalKm);
        route.setMaxNumberUsers(maxNumberOfUsers);
        if (stops != null) {
            route.setStops(stops);
        }

        return routeRepository.createRoute(route);
    }

    @Override
    public Optional<Route> getRouteById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Route> getRoutesBelowPrice(float price) {
        return List.of();
    }

    @Override
    public void assignDriverByUsername(String username, Long idRoute) throws ToursException {

    }

    @Override
    public void assignTourGuideByUsername(String username, Long idRoute) throws ToursException {

    }

    @Override
    @Transactional
    public Supplier createSupplier(String businessName, String authorizationNumber) throws ToursException {
        if (businessName == null || businessName.isEmpty()) {
            throw new ToursException("El nombre del negocio no puede estar vacío");
        }
        if (authorizationNumber == null || authorizationNumber.isEmpty()) {
            throw new ToursException("El número de autorización no puede estar vacío");
        }

        Supplier supplier = new Supplier();
        supplier.setBusinessName(businessName);
        supplier.setAuthorizationNumber(authorizationNumber);

        return supplierRepository.createSupplier(supplier);
    }

    @Override
    @Transactional
    public Service addServiceToSupplier(String name, float price, String description, Supplier supplier) throws ToursException {
        if (name == null || name.isEmpty()) {
            throw new ToursException("El nombre del servicio no puede estar vacío");
        }
        if (price <= 0) {
            throw new ToursException("El precio del servicio debe ser mayor a 0");
        }
        if (supplier == null) {
            throw new ToursException("El proveedor no puede ser nulo");
        }

        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        service.setDescription(description);
        service.setSupplier(supplier);

        return serviceRepository.createService(service);
    }


    @Override
    public Service updateServicePriceById(Long id, float newPrice) throws ToursException {
        Service updated = serviceRepository.updateServicePrice(id, newPrice);
        if (updated == null) {
            throw new ToursException("No se pudo actualizar el servicio");
        }
        return updated;
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Supplier> getSupplierByAuthorizationNumber(String authorizationNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id) throws ToursException {
        return Optional.empty();
    }

    @Override
    public Purchase createPurchase(String code, Route route, User user) throws ToursException {
        return null;
    }


    @Override
    @Transactional
    public Purchase createPurchase(String code, Date date, Route route, User user) throws ToursException {
        if (code == null || code.isEmpty()) {
            throw new ToursException("El código de la compra no puede estar vacío");
        }
        if (route == null) {
            throw new ToursException("La ruta no puede ser nula");
        }
        if (user == null) {
            throw new ToursException("El usuario no puede ser nulo");
        }

        Purchase purchase = new Purchase();
        purchase.setCode(code);
        purchase.setDate(date != null ? date : new Date());
        purchase.setRoute(route);
        purchase.setUser(user);
        purchase.setTotalPrice(0);

        return purchaseRepository.createPurchase(purchase);
    }


    @Override
    @Transactional
    public ItemService addItemToPurchase(Service service, int quantity, Purchase purchase) throws ToursException {
        if (service == null) {
            throw new ToursException("El servicio no puede ser nulo");
        }
        if (quantity <= 0) {
            throw new ToursException("La cantidad debe ser mayor a 0");
        }
        if (purchase == null) {
            throw new ToursException("La compra no puede ser nula");
        }

        ItemService itemService = new ItemService();
        itemService.setService(service);
        itemService.setQuantity(quantity);
        itemService.setPurchase(purchase);

        purchase.addItem(itemService, service.getPrice() * quantity);
        service.addItem(itemService);

        return itemService;
    }

    @Override
    public Optional<Purchase> getPurchaseByCode(String code) {
        return Optional.empty();
    }

    @Override
    public void deletePurchase(Purchase purchase) throws ToursException {

    }

    @Override
    @Transactional
    public Review addReviewToPurchase(int rating, String comment, Purchase purchase) throws ToursException {
        if (rating < 1 || rating > 5) {
            throw new ToursException("La calificación debe estar entre 1 y 5");
        }
        if (purchase == null) {
            throw new ToursException("La compra no puede ser nula");
        }

        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setPurchase(purchase);

        return reviewRepository.createReview(review);
    }

    @Override
    @Transactional
    public void deleteRoute(Route route) throws ToursException {
        if (route == null) {
            throw new ToursException("La ruta no puede ser nula");
        }

        if (route.getId() == null) {
            throw new ToursException("La ruta no tiene identificador");
        }

        if (verifyPurchaseInRoute(route)){
            throw new ToursException("No se puede eliminar una ruta con compras asociadas");
        }

        routeRepository.deleteRoute(route.getId());
    }

    private boolean verifyPurchaseInRoute(Route route) {
        List<Purchase> purchases = purchaseRepository.getPurchasesByRouteId(route.getId());
        return (purchases != null && purchases.size() > 0);
    }

    @Override
    public List<Purchase> getAllPurchasesOfUsername(String username) {
        return List.of();
    }

    @Override
    public List<User> getUserSpendingMoreThan(float mount) {
        return List.of();
    }

    @Override
    public List<Supplier> getTopNSuppliersInPurchases(int n) {
        return List.of();
    }

    @Override
    public long getCountOfPurchasesBetweenDates(Date start, Date end) {
        return 0;
    }

    @Override
    public List<Route> getRoutesWithStop(Stop stop) {
        return List.of();
    }

    @Override
    public Long getMaxStopOfRoutes() {
        return 0L;
    }

    @Override
    public List<Route> getRoutsNotSell() {
        return List.of();
    }

    @Override
    public List<Route> getTop3RoutesWithMaxRating() {
        return List.of();
    }

    @Override
    public Service getMostDemandedService() {
        return null;
    }

    @Override
    public List<TourGuideUser> getTourGuidesWithRating1() {
        return List.of();
    }
}
