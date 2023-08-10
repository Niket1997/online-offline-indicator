package com.niket.onlineofflineindicator.interfaces;

import com.niket.onlineofflineindicator.records.GetLastUserActivitiesResponse;

import java.util.List;

public interface IActivityService {
    void registerHeartbeat(int customerId);

    GetLastUserActivitiesResponse getLastUserActivities(List<Integer> customerIds);
}
