package com.tus.ca.ride.hailing.service;

import java.time.LocalDateTime;

public record RideRequest(String rideId, String userId, String driverId, LocalDateTime rideTime) implements Ride {}

