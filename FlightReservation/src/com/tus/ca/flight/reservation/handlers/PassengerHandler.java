package com.tus.ca.flight.reservation.handlers;

import com.tus.ca.flight.reservation.enums.Gender;
import com.tus.ca.flight.reservation.model.AppContext;
import com.tus.ca.flight.reservation.model.Passenger;
import com.tus.ca.flight.reservation.service.PassengerService;
import com.tus.ca.flight.reservation.service.impl.PassengerServiceImpl;

import java.util.Scanner;

public class PassengerHandler {

    PassengerService passengerService;

    public PassengerHandler(AppContext flightReservationContext) {
        passengerService = new PassengerServiceImpl(flightReservationContext);
    }

    public  void passengerServiceMenu() {
        //System.out.println("Passenger Service Menu");
        boolean shouldRun = true;
        while (shouldRun) {
            System.out.println("Passenger Service Menu");
            System.out.println("====================");
            System.out.println("1. Add Passenger");
            System.out.println("2. Update Passenger");
            System.out.println("3. Remove Passenger");
            System.out.println("4. Search Passenger");
            System.out.println("5. List All Passenger");
            System.out.println("6. Return to Main Menu");
            System.out.println("====================");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Provide Passenger Id :");
                    int passengerId = in.nextInt();
                    in.nextLine();
                    System.out.println("Provide Passenger Name :");
                    String passengerName = in.nextLine();
                    System.out.println("Provide Passenger Email address :");
                    String passengerEmail = in.nextLine();
                    System.out.println("Provide Passenger Phone Number :");
                    String passengerPhone = in.nextLine();
                    System.out.println("Provide Passenger Address :");
                    String passengerAddress = in.nextLine();
                    System.out.println("Provide Passenger Age :");
                    Integer passengerAge = in.nextInt();
                    in.nextLine();
                    System.out.println("Provide Passenger Gender :");
                    Gender passengerGender = Gender.valueOf(in.nextLine());
                    Passenger passenger = new Passenger(passengerId,passengerName,passengerEmail,passengerPhone,passengerAddress,passengerAge ,passengerGender);
                    passengerService.addPassenger(passenger);
                    break;
                case 2:
                    System.out.println("Provide Passenger Number to update");
                    int passengerIdForUpdate = in.nextInt();
                    in.nextLine();
                    Passenger passengerToUpdate = passengerService.searchPassenger(passengerIdForUpdate);
                    if(passengerToUpdate != null) {
                        System.out.println("Provide new EmailId for " + passengerToUpdate);
                        String newEmailId = in.nextLine();
                        System.out.println("Provide new Phone Number for  " + passengerToUpdate);
                        String newPhoneNumber = in.nextLine();
                        passengerToUpdate.setPassengerEmail(newEmailId);
                        passengerToUpdate.setPassengerPhoneNumber(newPhoneNumber);
                        passengerService.updatePassenger(passengerToUpdate);
                    } else {
                        System.out.println("Passenger Number " + passengerToUpdate + " is not present in the system");
                    }
                    break;
                case 3:
                    System.out.println("Provide Passenger Number to remove");
                    int passengerIdToRemove = in.nextInt();
                    in.nextLine();
                    Passenger passengerToRemove = passengerService.searchPassenger(passengerIdToRemove);
                    if(passengerToRemove != null) {
                        passengerService.deletePassenger(passengerIdToRemove);
                    } else {
                        System.out.println("PassengerNumber " + passengerIdToRemove + " is not present in the system");
                    }
                    break;
                case 4:
                    System.out.println("Provide Passenger Number to Search");
                    int passengerNumberToSearch = in.nextInt();
                    in.nextLine();
                    final Passenger searchedPassenger = passengerService.searchPassenger(passengerNumberToSearch);
                    if(searchedPassenger != null) {
                        System.out.println("Found Passenger " + searchedPassenger);
                    } else {
                        System.out.println("PassengerNumber " + passengerNumberToSearch + " not found");
                    }
                    break;
                case 5:
                    passengerService.displayAllPassengers(passengerService.getAllPassengers());
                    break;
                case 6:
                    shouldRun = false;
                    break;
                default:
                    System.out.println("Invalid Input Provided for Passenger Menu");
                    System.out.println("Please try again choosing option from the menu below");
            }
        }

    }
}
