package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.enums.BookingClass;
import com.tus.ca.flight.reservation.model.Booking;
import com.tus.ca.flight.reservation.model.PaymentMethod;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    public boolean searchRoutes(String origin, String destination, LocalDate dateOfJourney);

    public String makeBooking(Integer flightId, String origin, String destination, LocalDate dateOfJourney, int noOfSeats, List<Integer> passengerIdList,
                              PaymentMethod paymentMethod, BookingClass bookingClass);

    public List<Booking> listAllBookings();

    public void cancelBooking(String bookingId);
}
