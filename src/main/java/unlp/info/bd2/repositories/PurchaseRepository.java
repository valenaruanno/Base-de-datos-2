package unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import unlp.info.bd2.model.Purchase;

import java.util.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> getPurchasesByRouteId(Long id);
    Page<Purchase> findAllByUserUsername(String username, Pageable pageable);
    long countAllByDateBetween (Date from, Date to);
}

