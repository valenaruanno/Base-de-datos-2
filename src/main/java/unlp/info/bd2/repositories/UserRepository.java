package unlp.info.bd2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;
import unlp.info.bd2.utils.ToursException;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u JOIN u.purchaseList p WHERE p.totalPrice >= :mount")
    Page<User> getUserSpendingMoreThan(@Param("mount") float mount, Pageable pageable);

    @Query("SELECT DISTINCT u FROM TourGuideUser u JOIN u.purchaseList p JOIN p.review r WHERE r.rating = 1")
    Page<TourGuideUser> getTourGuidesWithRating1(Pageable pageable);
}

