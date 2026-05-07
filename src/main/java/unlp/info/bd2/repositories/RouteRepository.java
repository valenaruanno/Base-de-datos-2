package unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    Page<Route> findByStop(Stop stop, Pageable pageable);

    @Query("SELECT MAX(size(r.stops)) FROM Route r")
    long getMaxStopOfRoutes();

    @Query("SELECT r FROM Route r WHERE not exists(SELECT 1 FROM Purchase p WHERE r = p.route)")
    Page<Route> getRoutesNotSell();

    @Query("SELECT r FROM Review re JOIN re.purchase p JOIN p.route r GROUP BY (r) ORDER BY MAX(re.rating) DESC")
    Page<Route> getTop3RoutesWithMaxRating(Pageable pageable);
}

