package com.tus.ca.ride.hailing.service;

import com.tus.ca.ride.hailing.enums.RideStatus;

import java.time.LocalDateTime;

sealed public interface Ride permits RideRequest, RideCompletion {
    String rideId();
    String userId();
    String driverId();
    LocalDateTime rideTime();
    RideStatus rideStatus(); // Enum for ride status
}
