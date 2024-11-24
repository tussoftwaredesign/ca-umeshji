package com.tus.ca.flight.reservation.service.impl;

import com.tus.ca.flight.reservation.model.Flight;
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

    public FlightServiceImpl() {
        flightList = new ArrayList<>();
        Flight flightOne = new Flight("AC","Vancouver","Ontaria",103, Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.FRIDAY), LocalTime.of(13,30), LocalTime.of(15,30), LocalDate.of(2024, 1, 1),LocalDate.of(2024, 12, 20));
        Flight flightTwo = new Flight("EI","Dublin","London",103, Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.THURSDAY), LocalTime.of(13,30), LocalTime.of(15,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31));
        flightList.add(flightOne);
        flightList.add(flightTwo);
    }

    @Override
    public void addFlight(Flight flight) {
        flightList.add(flight);
        System.out.println("Added Flight Successfully");
        FlightService.displayFlight(flightList);
    }

    @Override
    public void deleteFlight(Integer flightNumber) {

    }

    @Override
    public void updateFlight(Flight flight) {

    }

    @Override
    public Flight searchFlight(int flightNumber) {
        /* Iterate flight list*/
        for(Flight flight : flightList) {
            // Checking record by id Number.
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
            }
        }
        return null;
    }


}
