package unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Supplier;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    //@Query("""
    //SELECT s
    //FROM Supplier s
    //JOIN s.services serv
    //JOIN serv.itemServiceList item
    //GROUP BY s
    //ORDER BY SUM(item.quantity) DESC
    //""")
    @Query("SELECT s FROM Supplier s JOIN s.services se JOIN se.itemServiceList i ON i.service = se GROUP BY s ORDER BY COUNT(i) DESC ")
    Page<Supplier> getTopNSuppliersInPurchases(Pageable pageable);
}

