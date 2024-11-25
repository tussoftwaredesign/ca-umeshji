package com.tus.ca.flight.reservation.service.impl;

import com.tus.ca.flight.reservation.enums.BookingClass;
import com.tus.ca.flight.reservation.model.Flight;
import com.tus.ca.flight.reservation.model.FlightReservationContext;
import com.tus.ca.flight.reservation.model.PaymentMethod;
import com.tus.ca.flight.reservation.service.BookingService;

import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    FlightReservationContext flightReservationContext;

    public BookingServiceImpl(FlightReservationContext flightReservationContext){
        this.flightReservationContext = flightReservationContext; // used here to refer the instance variable i.e (flightReservationContext)
    }
    @Override
    public boolean searchRoutes(String origin, String destination, LocalDate dateOfJourney) {
        boolean isPresent = false;
        final List<Flight> flights = this.flightReservationContext.getList("flights", Flight.class).stream().
                filter(x -> x.getOrigin().equalsIgnoreCase(origin) && x.getDestination().equalsIgnoreCase(destination))
                .filter(x -> x.getEffectStartDate().isBefore(dateOfJourney) && x.getEffectEndDate().isAfter(dateOfJourney))
                .toList();
        if(flights.isEmpty()){
            System.out.println("No results found");
        } else {
            flights.stream().forEach(System.out::println);
            isPresent = true;
        }
        return isPresent;
    }

    @Override
    public String makeBooking(String origin, String destination, LocalDate dateOfJourney, int noOfSeats, List<Integer> passengerIdList, PaymentMethod paymentMethod, BookingClass bookingClass) {
        return "";
    }

}
