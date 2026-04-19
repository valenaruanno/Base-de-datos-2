package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Service;

import java.util.List;

public interface ServiceRepository {
    Service createService(Service service);
    Service updateServicePrice(Long id, float price);
    Service findService(long id);
    List<Service> findServices();
    void deleteService(long id);
    Service getMostDemandedService();
}

