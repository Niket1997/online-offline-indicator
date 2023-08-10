package com.niket.onlineofflineindicator.records;

public record UserActivity(int customerId, int lastSeen, boolean isActive) {
}
