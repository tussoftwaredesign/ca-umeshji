package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.enums.BookingClass;
import com.tus.ca.flight.reservation.model.PaymentMethod;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    public boolean searchRoutes(String origin, String destination, LocalDate dateOfJourney);

    public String makeBooking(String origin, String destination, LocalDate dateOfJourney, int noOfSeats, List<Integer> passengerIdList,
                              PaymentMethod paymentMethod, BookingClass bookingClass);
}
