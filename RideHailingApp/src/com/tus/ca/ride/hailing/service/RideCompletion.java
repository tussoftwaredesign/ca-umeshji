package com.tus.ca.ride.hailing.service;

import java.time.LocalDateTime;

public record RideCompletion(String rideId, String userId, String driverId, LocalDateTime rideTime, double fare) implements Ride {}

