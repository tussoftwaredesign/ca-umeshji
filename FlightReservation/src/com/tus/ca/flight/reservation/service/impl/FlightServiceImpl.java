package com.tus.ca.flight.reservation.service.impl;

import com.tus.ca.flight.reservation.model.Flight;
import com.tus.ca.flight.reservation.model.FlightReservationContext;
import com.tus.ca.flight.reservation.service.FlightService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightServiceImpl implements FlightService {

    // Creating an empty ArrayList
    public List<Flight> flightList;

    FlightReservationContext flightReservationContext;

    public FlightServiceImpl(FlightReservationContext flightReservationContext) {
         this(flightReservationContext,new ArrayList<>()); // Constructor chaining here is possible because of 'this()' keyword
    }

    public FlightServiceImpl(FlightReservationContext flightReservationContext,List<Flight> flightList) {
        this.flightReservationContext = flightReservationContext;  //This is an example of constructor overloading
        this.flightList = flightList;
        Flight flightOne = new Flight("AC","Vancouver","Ontaria",103, Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.FRIDAY), LocalTime.of(13,30), LocalTime.of(15,30), LocalDate.of(2024, 1, 1),LocalDate.of(2024, 12, 20), 1);
        Flight flightTwo = new Flight("AC","Vancouver","Ontaria",105, Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.FRIDAY), LocalTime.of(15,30), LocalTime.of(18,30), LocalDate.of(2024, 1, 1),LocalDate.of(2024, 12, 20),  1);
        Flight flightThree = new Flight("EI","Dublin","London",104, Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.THURSDAY), LocalTime.of(13,30), LocalTime.of(15,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31), 2);
        Flight flightFour = new Flight("EI","Dublin","London",106, Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.THURSDAY), LocalTime.of(15,30), LocalTime.of(18,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31), 2);
        Flight flightFive = new Flight("AA","New York","Dallas",936, Arrays.asList(DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY), LocalTime.of(13,30), LocalTime.of(18,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31), 3);
        Flight flightSix = new Flight("AA","New York","Dallas",976, Arrays.asList(DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY), LocalTime.of(15,30), LocalTime.of(21,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31),  3);
        Flight flightSeven = new Flight("DL","CHICAGO","Texas",144, Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.THURSDAY), LocalTime.of(10,30), LocalTime.of(23,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31), 4);
        Flight flightEight = new Flight("DL","CHICAGO","Texas",146, Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.THURSDAY), LocalTime.of(11,00), LocalTime.of(23,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31), 4);
        flightList.add(flightOne);
        flightList.add(flightTwo);
        flightList.add(flightThree);
        flightList.add(flightFour);
        flightList.add(flightFive);
        flightList.add(flightSix);
        flightList.add(flightSeven);
        flightList.add(flightEight);
        flightReservationContext.addList("flights", flightList);
    }

    @Override
    public void addFlight(Flight flight) {
        flightList.add(flight);
        flightReservationContext.addList("flights", flightList);
        System.out.println("Added Flight Successfully");
    }

    @Override
    public void deleteFlight(Integer flightNumber) {
        flightList.removeIf(f -> f.getFlightNumber() ==  flightNumber);
        System.out.println("Deleted Flight Successfully");
        flightReservationContext.addList("flights", flightList);
    }

    @Override
    public void updateFlight(Flight flight) {
        Integer flightNumber = flight.getFlightNumber();
        flightList.removeIf(f -> f.getFlightNumber() ==  flightNumber);
        System.out.println("Updated Flight Successfully");
        flightList.add(flight);
        flightReservationContext.addList("flights", flightList);
    }

    @Override
    public Flight searchFlight(int flightNumber) {
        /* Iterate flight list*/
        for(Flight flight : flightReservationContext.getList("flights",Flight.class)) {
            // Checking record by flight number
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
            }
        }
        return null;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightReservationContext.getList("flights",Flight.class);
    }


}
