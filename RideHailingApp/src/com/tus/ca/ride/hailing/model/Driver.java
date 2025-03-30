package com.tus.ca.ride.hailing.model;

import com.tus.ca.ride.hailing.enums.VehicleType;

public record Driver(String driverId, String name, VehicleType vehicleType, double rating) {
    // Additional methods or logic can go here
}
