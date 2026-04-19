package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Review;

import java.util.List;

public interface ReviewRepository {
    public Review createReview(Review review);
    public Review findReview(long id);
    public List<Review> findReviews();
    public void deleteReview(long id);
}

