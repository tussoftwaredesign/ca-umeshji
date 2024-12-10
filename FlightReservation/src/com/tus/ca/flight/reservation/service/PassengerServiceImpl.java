package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.enums.Gender;
import com.tus.ca.flight.reservation.model.AppContext;
import com.tus.ca.flight.reservation.model.Passenger;

import java.util.ArrayList;
import java.util.List;

final public class PassengerServiceImpl implements PassengerService {

    // Creating an empty ArrayList
    public List<Passenger> passengerList;

    AppContext flightReservationContext;

    public PassengerServiceImpl(AppContext flightReservationContext) {
        this.passengerList = new ArrayList<>();
        this.flightReservationContext = flightReservationContext;
        Passenger passengerOne = new Passenger(1,"Umeshchand Thakur","umeshchand.thakur@hotmail.com","101-123-456","14 Wingfield Stepaside, Dubin 18",40, Gender.MALE);
        Passenger passengerTwo = new Passenger(2,"Deepa Manral","deepa.manral@aol.com","101-123-456","14 Wingfield Stepaside, Dubin 18",35,Gender.FEMALE);
        passengerList.add(passengerOne);
        passengerList.add(passengerTwo);
        flightReservationContext.addList("passengers", passengerList);
    }

    @Override
    public void addPassenger(Passenger... passenger) {   // example of varargs
        passengerList.addAll(List.of(passenger));
        flightReservationContext.addList("passengers", passengerList);
        System.out.println("Added Passenger Successfully");
    }

    @Override
    public void deletePassenger(Integer passengerId) {
        passengerList.removeIf(p -> p.getPassengerId() ==  passengerId);
        flightReservationContext.addList("passengers", passengerList);
        System.out.println("Deleted Passenger Successfully");
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        Integer passengerId = passenger.getPassengerId();
        passengerList.removeIf(p -> p.getPassengerId() ==  passengerId);
        System.out.println("Updated Passenger Successfully");
        passengerList.add(passenger);
        flightReservationContext.addList("passengers", passengerList);
    }

    @Override
    public Passenger searchPassenger(int passengerId) {
        /* Iterate passenger list*/
        for(Passenger passenger : flightReservationContext.getList("passengers",Passenger.class)) {
            // Checking record by id Number.
            if (passenger.getPassengerId() == passengerId) {
                return passenger;
            }
        }
        return null;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        // Return a defensive copy when accessing all the passengers
        return new ArrayList<>(flightReservationContext.getList("passengers",Passenger.class));  //defensive copy features to prevent modification
    }
}
