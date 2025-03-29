package com.tus.ca.ride.hailing.service;

import java.time.LocalDateTime;

sealed public interface Ride permits RideRequest, RideCompletion {
    String rideId();
    String userId();
    String driverId();
    LocalDateTime rideTime();
}
