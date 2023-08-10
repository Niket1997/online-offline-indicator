package com.niket.onlineofflineindicator.records;

public record UserActivity(int customerId, long lastSeen, boolean isActive) {
}
