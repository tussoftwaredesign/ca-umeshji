package com.tus.ca.flight.reservation.model;

import com.tus.ca.flight.reservation.enums.BookingClass;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private int flightNumber;
    private LocalDate dataOfJourney;
    private BookingClass bookingClass;
    private String origin;
    private String destination;
    private int noOfSeats;
    private int passengerId;
    private String paymentId;

    public Booking(int flightNumber, LocalDate dataOfJourney, BookingClass bookingClass, String origin, String destination, int noOfSeats, int passengerId, String paymentId) {
        this.flightNumber = flightNumber;
        this.dataOfJourney = dataOfJourney;
        this.bookingClass = bookingClass;
        this.origin = origin;
        this.destination = destination;
        this.noOfSeats = noOfSeats;
        this.passengerId = passengerId;
        this.paymentId = paymentId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDate getDataOfJourney() {
        return dataOfJourney;
    }

    public void setDataOfJourney(LocalDate dataOfJourney) {
        this.dataOfJourney = dataOfJourney;
    }

    public BookingClass getBookingClass() {
        return bookingClass;
    }

    public void setBookingClass(BookingClass bookingClass) {
        this.bookingClass = bookingClass;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return flightNumber == booking.flightNumber && noOfSeats == booking.noOfSeats && passengerId == booking.passengerId && Objects.equals(dataOfJourney, booking.dataOfJourney) && Objects.equals(paymentId, booking.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, dataOfJourney, noOfSeats, passengerId, paymentId);
    }
}


