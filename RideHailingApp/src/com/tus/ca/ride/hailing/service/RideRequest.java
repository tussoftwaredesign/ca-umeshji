package com.tus.ca.ride.hailing.service;

import com.tus.ca.ride.hailing.enums.RideStatus;

import java.time.LocalDateTime;

public record RideRequest(String rideId, String userId, String driverId, LocalDateTime rideTime, RideStatus rideStatus) implements Ride {
    public RideRequest(String rideId, String userId, String driverId, LocalDateTime rideTime) {
        this(rideId, userId, driverId, rideTime, RideStatus.REQUESTED); // Default status is REQUESTED
    }
}
