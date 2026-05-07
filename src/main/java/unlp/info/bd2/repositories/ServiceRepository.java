package unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Service;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    @Query("SELECT s FROM Purchase p JOIN p.itemServiceList i JOIN i.service s GROUP BY (s) ORDER BY(SUM (i.quantity)) DESC")
    List<Service> getMostDemandedService();

    Service updateById(float newPrice);
}

