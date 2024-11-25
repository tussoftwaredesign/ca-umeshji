package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.model.Passenger;

import java.util.List;

public interface PassengerService {
    public void addPassenger(Passenger passenger);

    public void deletePassenger(Integer passengerId);

    public void updatePassenger(Passenger passenger);

    public Passenger searchPassenger(int passengerId);

    public List<Passenger> getAllPassengers();

    public default void displayAllPassengers(List<Passenger> allPassenger){
        System.out.println("Display Passenger List Starts");
        displayPassengers(allPassenger);
        System.out.println("Display Passenger List  Ends");
    }

    public static void displayPassengers(List<Passenger> allPassenger ) {
        allPassenger.stream().forEach(System.out::println);
    }
}
