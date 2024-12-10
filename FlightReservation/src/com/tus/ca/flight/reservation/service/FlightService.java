package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.model.Flight;

import java.util.List;

public interface FlightService {

    public void addFlight(Flight flight);

    public void deleteFlight(Integer flightNumber);

    public void updateFlight(Flight flight);

    public Flight searchFlight(int flightNumber);

    public List<Flight> getAllFlights();

    public default void displayAllFlight(List<Flight> allFlights){
        generateDecorator();
        System.out.println("Display Flight Starts");
        displayFlight(allFlights);
        System.out.println("Display Flight Ends");
        generateDecorator();
    }

    public static void displayFlight(List<Flight> allFlights ) {
        // Using method reference (Reference to a Static Method)
        allFlights.stream().forEach(System.out::println);
    }

    private void generateDecorator() {
        System.out.println("=============================");
    }



}
