package unlp.info.bd2.repositories;

import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Stop;

import java.util.List;

public interface RouteRepository {
    public Route createRoute(Route route);
    public Route findRoute(long id);
    public List<Route> findRoutes();
    public void deleteRoute(long id);
    List<Route> getRoutesWithStop(Stop stop);
    int getMaxStopOfRoutes();
    List<Route> getRoutesNotSell();
    List<Route> getTop3RoutesWithMaxRating();
}

