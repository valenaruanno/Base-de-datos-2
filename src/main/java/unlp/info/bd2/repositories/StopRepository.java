package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Stop;

import java.util.List;

public interface StopRepository {
    public Stop createStop(Stop stop);
    public Stop findStop(long id);
    public List<Stop> findStops();
    public void deleteStop(long id);
}
