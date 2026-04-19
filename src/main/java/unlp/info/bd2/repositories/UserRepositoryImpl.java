package unlp.info.bd2.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public User findUser(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> findUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).list();
    }

    @Override
    public void deleteUser(long id) {
        User user = findUser(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public List<User> getUserSpendingMoreThan(float mount) {
        return sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT u FROM User u JOIN u.purchaseList p WHERE p.totalPrice >= :mount", User.class)
                .setParameter("mount", mount)
                .list();
    }

    @Override
    public List<TourGuideUser> getTourGuidesWithRating1() {
        return sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT p.user FROM Review r JOIN r.purchase p WHERE r.rating = 1 AND TYPE(p.user) = TourGuideUser", TourGuideUser.class)
                .list();
    }
}

