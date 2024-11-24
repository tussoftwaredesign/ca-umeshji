package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.model.Passenger;

public interface PassengerService {
    public void addPassenger(Passenger passenger);
    public void deletePassenger(Passenger passenger);
    public void updatePassenger(Passenger passenger);
}
