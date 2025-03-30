package com.tus.ca.ride.hailing.service;

import com.tus.ca.ride.hailing.enums.RideStatus;

import java.time.LocalDateTime;

public record RideCompletion(String rideId, String userId, String driverId, LocalDateTime rideTime, double fare, RideStatus rideStatus) implements Ride {
    public RideCompletion(String rideId, String userId, String driverId, LocalDateTime rideTime, double fare) {
        this(rideId, userId, driverId, rideTime, fare, RideStatus.COMPLETED); // Default status is COMPLETED
    }
}
