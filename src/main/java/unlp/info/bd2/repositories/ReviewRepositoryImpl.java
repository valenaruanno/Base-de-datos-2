package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.Review;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Review createReview(Review review) {
        sessionFactory.getCurrentSession().persist(review);
        return review;
    }

    @Override
    public Review findReview(long id) {
        return sessionFactory.getCurrentSession().get(Review.class, id);
    }

    @Override
    public List<Review> findReviews() {
        return sessionFactory.getCurrentSession().createQuery("FROM Review", Review.class).list();
    }

    @Override
    public void deleteReview(long id) {
        Review review = findReview(id);
        if (review != null) {
            sessionFactory.getCurrentSession().delete(review);
        }
    }
}

