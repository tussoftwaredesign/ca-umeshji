package com.tus.ca.flight.reservation.model;

import com.tus.ca.flight.reservation.enums.Gender;

import java.util.Objects;

public class Passenger {
    public Integer passengerId;

    public String passengerName;

    public String passengerEmail;

    private String passengerPhoneNumber;

    private String passengerAddress;

    private Integer passengerAge;

    private Gender passengerGender;

    public Passenger(Integer passengerId, String passengerName, String passengerEmail, String passengerPhoneNumber, String passengerAddress, Integer passengerAge, Gender passengerGender) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.passengerPhoneNumber = passengerPhoneNumber;
        this.passengerAddress = passengerAddress;
        this.passengerAge = passengerAge;
        this.passengerGender = passengerGender;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerPhoneNumber() {
        return passengerPhoneNumber;
    }

    public void setPassengerPhoneNumber(String passengerPhoneNumber) {
        this.passengerPhoneNumber = passengerPhoneNumber;
    }

    public String getPassengerAddress() {
        return passengerAddress;
    }

    public void setPassengerAddress(String passengerAddress) {
        this.passengerAddress = passengerAddress;
    }

    public Integer getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(Integer passengerAge) {
        this.passengerAge = passengerAge;
    }

    public Gender getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(Gender passengerGender) {
        this.passengerGender = passengerGender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(passengerId, passenger.passengerId) && Objects.equals(passengerName, passenger.passengerName) && Objects.equals(passengerEmail, passenger.passengerEmail) && Objects.equals(passengerPhoneNumber, passenger.passengerPhoneNumber) && Objects.equals(passengerAddress, passenger.passengerAddress) && Objects.equals(passengerAge, passenger.passengerAge) && passengerGender == passenger.passengerGender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId, passengerName, passengerAge);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", passengerName='" + passengerName + '\'' +
                ", emailAddress='" + passengerEmail + '\'' +
                ", phoneNumber='" + passengerPhoneNumber + '\'' +
                ", address='" + passengerAddress + '\'' +
                ", age=" + passengerAge +
                ", gender=" + passengerGender +
                '}';
    }
}
