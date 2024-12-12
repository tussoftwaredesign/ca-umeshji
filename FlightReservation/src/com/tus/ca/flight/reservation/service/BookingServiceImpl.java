package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.enums.BookingClass;
import com.tus.ca.flight.reservation.exceptions.RouteNotFound;
import com.tus.ca.flight.reservation.handlers.PaymentHandler;
import com.tus.ca.flight.reservation.model.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BookingServiceImpl implements BookingService {

    AppContext appContext;

    public BookingServiceImpl(AppContext flightReservationContext){
        this.appContext = flightReservationContext; // this used here to refer the instance variable i.e (appContext)
    }
    @Override
    public boolean searchRoutes(String origin, String destination, LocalDate dateOfJourney) {
        Boolean routeFound = false;
        List<Flight> allFlights = this.appContext.getList("flights", Flight.class);
        try {
            routeFound = searchFlightDetails(origin,destination,dateOfJourney,allFlights);
        } catch(RouteNotFound ex){
            System.out.println("Exception while deriving route  "+ ex.getMessage());
        }
        return routeFound;
    }

    @Override
    public String makeBooking(Integer flightId, String origin, String destination, LocalDate dateOfJourney, int noOfSeats, List<Integer> passengerIdList, PaymentHandler paymentMethod, BookingClass bookingClass) {

        final List<Passenger> allPassengers = appContext.getList("passengers", Passenger.class);
        final List<Passenger> passengerList = allPassengers.stream().filter(passenger -> passengerIdList.stream().anyMatch(passengerId -> equals(passenger.getPassengerId()))).toList();
        String bookingId  = generateUniqueBookingId();

        Booking booking = new Booking(flightId,dateOfJourney,bookingClass,origin,destination,noOfSeats,passengerList,paymentMethod,bookingId);
        List<Booking> bookings = appContext.getList("bookings", Booking.class);
        if(bookings != null){
            appContext.getList("bookings", Booking.class).add(booking);
        } else {
            appContext.addList("bookings", List.of(booking));
        }

        return booking.getBookingId();


    }

    @Override
    public List<Booking> listAllBookings() {
        return appContext.getList("bookings",Booking.class) != null ? appContext.getList("bookings",Booking.class) : Collections.emptyList();
    }

    @Override
    public void cancelBooking(String bookingId) {
        boolean containsBooking = false;
        List<Booking> bookingList = appContext.getList("bookings",Booking.class);

        containsBooking = bookingList != null && !bookingList.isEmpty() && bookingList.stream().anyMatch(booking -> booking.getBookingId().equalsIgnoreCase(bookingId));

        if(containsBooking) {
            for (Booking booking : bookingList) {
                if (booking.getBookingId().equalsIgnoreCase(bookingId)) {
                    booking.setBookingStatus(false);
                    System.out.println("Booking cancelled successfully");
                }
            }
        } else {
            System.out.println("Booking does not exist");
        }
    }

    private String generateUniqueBookingId() {
        long timestamp = System.currentTimeMillis();
        int randomNum = new Random().nextInt(1000);
        return  "BKG-" + timestamp + "-" + randomNum;
    }


    public boolean searchFlightDetails(String origin, String destination, LocalDate dateOfJourney, List<Flight> allFlights) throws RouteNotFound {
        boolean isPresent = false;
        List<Flight> flights = allFlights.stream().filter(x -> x.getOrigin().equalsIgnoreCase(origin) && x.getDestination().equalsIgnoreCase(destination))
                .filter(x -> x.getEffectStartDate().isBefore(dateOfJourney) && x.getEffectEndDate().isAfter(dateOfJourney))
                .toList();
        if(flights.isEmpty()){
            throw new RouteNotFound("No route found");
        } else {
            System.out.println("Searched route starts");
            flights.stream().forEach(System.out::println);
            System.out.println("Searched route ends");
            isPresent = true;
        }
        return isPresent;
    }

}
