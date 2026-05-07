package unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;
import unlp.info.bd2.model.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}

