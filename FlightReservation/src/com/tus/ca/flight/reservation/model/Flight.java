package com.tus.ca.flight.reservation.model;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Flight {
    private String carrierCode;
    private int flightNumber;
    private String origin;
    private String destination;
    private List<DayOfWeek> frequencies;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalDate effectStartDate;
    private LocalDate effectEndDate;
    private int routeId;

    public Flight(String carrierCode, String origin, String destination, int flightNumber, List<DayOfWeek> frequencies, LocalTime departureTime, LocalTime arrivalTime, LocalDate effectStartDate, LocalDate effectEndDate,int routeId) {
        this.carrierCode = carrierCode;
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.frequencies = frequencies;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.effectStartDate = effectStartDate;
        this.effectEndDate = effectEndDate;
        this.routeId = routeId;

    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
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

    public List<DayOfWeek> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(List<DayOfWeek> frequencies) {
        this.frequencies = frequencies;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getEffectStartDate() {
        return effectStartDate;
    }

    public void setEffectStartDate(LocalDate effectStartDate) {
        this.effectStartDate = effectStartDate;
    }

    public LocalDate getEffectEndDate() {
        return effectEndDate;
    }

    public void setEffectEndDate(LocalDate effectEndDate) {
        this.effectEndDate = effectEndDate;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getRouteId() {
        return routeId;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightNumber == flight.flightNumber && Objects.equals(carrierCode, flight.carrierCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrierCode, flightNumber);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "carrierCode='" + carrierCode + '\'' +
                ", flightNumber=" + flightNumber +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", frequencies=" + frequencies +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", effectStartDate=" + effectStartDate +
                ", effectEndDate=" + effectEndDate +
                ", routeId=" + routeId +
                '}';
    }
}
