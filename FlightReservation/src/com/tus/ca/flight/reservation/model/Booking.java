package com.tus.ca.flight.reservation.model;

import com.tus.ca.flight.reservation.enums.BookingClass;
import com.tus.ca.flight.reservation.handlers.PaymentHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Booking {
    private int flightNumber;
    private LocalDate dataOfJourney;
    private BookingClass bookingClass;
    private String origin;
    private String destination;
    private int noOfSeats;
    private final List<Passenger> passengerList;
    private PaymentHandler paymentMethod;
    private String bookingId;
    boolean bookingStatus;

    public Booking(int flightNumber, LocalDate dataOfJourney, BookingClass bookingClass, String origin, String destination, int noOfSeats, List<Passenger> passengerList, PaymentHandler paymentMethod, String bookingId) {
        this.flightNumber = flightNumber;
        this.dataOfJourney = dataOfJourney;
        this.bookingClass = bookingClass;
        this.origin = origin;
        this.destination = destination;
        this.noOfSeats = noOfSeats;
        this.passengerList = passengerList;
        this.paymentMethod = paymentMethod;
        this.bookingId = bookingId;
        this.bookingStatus = true;
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

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public PaymentHandler getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentHandler paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return flightNumber == booking.flightNumber && noOfSeats == booking.noOfSeats && Objects.equals(dataOfJourney, booking.dataOfJourney) && bookingClass == booking.bookingClass && Objects.equals(origin, booking.origin) && Objects.equals(destination, booking.destination) && Objects.equals(passengerList, booking.passengerList) && Objects.equals(paymentMethod, booking.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, dataOfJourney, bookingClass, origin, destination, noOfSeats, passengerList, paymentMethod);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "flightNumber=" + flightNumber +
                ", dataOfJourney=" + dataOfJourney +
                ", bookingClass=" + bookingClass +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", passengerList=" + passengerList +
                ", paymentMethod=" + paymentMethod +
                ", bookingId='" + bookingId + '\'' +
                ", bookingStatus=" + bookingStatus +
                '}';
    }
}


