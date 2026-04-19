package unlp.info.bd2.repositories;

import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;

import java.util.List;

public interface UserRepository {
    public User createUser(User user);
    public User findUser(long id);
    public List<User> findUsers();
    public void deleteUser(long id);
    List<User> getUserSpendingMoreThan(float mount);
    List<TourGuideUser> getTourGuidesWithRating1();
}

