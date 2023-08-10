package com.niket.onlineofflineindicator.services;

import com.niket.onlineofflineindicator.records.GetLastUserActivitiesResponse;
import com.niket.onlineofflineindicator.records.UserActivity;
import com.niket.onlineofflineindicator.interfaces.IActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ActivityService implements IActivityService {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void registerHeartbeat(int customerId) {
        logger.info("registering heartbeat for customer " + customerId);
    }

    @Override
    public GetLastUserActivitiesResponse getLastUserActivities(List<Integer> customerIds) {
        logger.info("getting last user activities for " + customerIds.toString());
        UserActivity userOneActivity = new UserActivity(1, 2, true);
        UserActivity userTwoActivity = new UserActivity(2, 2, false);
        return new GetLastUserActivitiesResponse(List.of(userOneActivity, userTwoActivity));
    }
}
