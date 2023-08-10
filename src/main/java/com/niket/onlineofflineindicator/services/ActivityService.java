package com.niket.onlineofflineindicator.services;

import com.niket.onlineofflineindicator.constants.TimeConstants;
import com.niket.onlineofflineindicator.interfaces.IActivityService;
import com.niket.onlineofflineindicator.records.GetLastUserActivitiesResponse;
import com.niket.onlineofflineindicator.records.UserActivity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ActivityService implements IActivityService {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final @Qualifier("redisTemplate") RedisTemplate<Integer, Long> template;

    public ActivityService(RedisTemplate<Integer, Long> template) {
        this.template = template;
    }

    @Override
    public void registerHeartbeat(int customerId) {
        logger.info("registering heartbeat for customer " + customerId);
        template.opsForValue().set(customerId, Instant.now().getEpochSecond());
    }

    @Override
    public GetLastUserActivitiesResponse getLastUserActivities(List<Integer> customerIds) {
        logger.info("getting last user activities for " + customerIds.toString());
        List<UserActivity> userActivities = new ArrayList<>();
        for (int customerId : customerIds) {
            Long expiryTime = template.opsForValue().get(customerId);
            if (expiryTime == null) continue;

            userActivities.add(new UserActivity(customerId, expiryTime, Instant.now().getEpochSecond() < expiryTime + TimeConstants.HeartBeatExpiryDelay));
        }
        return new GetLastUserActivitiesResponse(userActivities);
    }
}
