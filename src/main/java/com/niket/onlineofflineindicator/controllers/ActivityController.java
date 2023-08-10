package com.niket.onlineofflineindicator.controllers;

import com.niket.onlineofflineindicator.records.GetLastUserActivitiesResponse;
import com.niket.onlineofflineindicator.records.RegisterHeartbeatRequest;
import com.niket.onlineofflineindicator.interfaces.IActivityService;
import com.niket.onlineofflineindicator.services.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ActivityController {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final IActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/v1/heartbeat")
    public void registerHeartbeat(@RequestBody RegisterHeartbeatRequest request) {
        logger.info("received heartbeat request for: " + request.customerId());
        activityService.registerHeartbeat(request.customerId());
    }

    @GetMapping("/v1/activities")
    public GetLastUserActivitiesResponse getLastUserActivities(@RequestParam("customerIds") List<Integer> customerIds) {
        logger.info("received getLastActivities request for: " + customerIds.toString());
        return activityService.getLastUserActivities(customerIds);
    }
}
