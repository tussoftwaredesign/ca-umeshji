package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.model.Passenger;

import java.util.List;

public sealed interface PassengerService permits PassengerServiceImpl {
    public void addPassenger(Passenger... passenger);

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
        allPassenger.stream().forEach(System.out::println);  //method reference feature (Reference to a static Method)
    }

    public static void displayPassengers(List<Passenger> allPassenger, String headerText) { //example of polymorphism feature (method overloading)
        System.out.println(headerText);
        allPassenger.stream().forEach(System.out::println);
    }
}
