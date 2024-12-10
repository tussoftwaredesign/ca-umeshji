package com.tus.ca.flight.reservation.handlers;

import com.tus.ca.flight.reservation.model.Flight;
import com.tus.ca.flight.reservation.model.AppContext;
import com.tus.ca.flight.reservation.service.FlightService;
import com.tus.ca.flight.reservation.service.FlightServiceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FlightHandler {

    FlightService flightService;

    public FlightHandler(AppContext flightReservationContext) {
        flightService = new FlightServiceImpl(flightReservationContext);
    }

    public  void flightServiceMenu() {
        boolean shouldRun = true;
        while (shouldRun) {
            System.out.println("Flight Service Menu");
            System.out.println("====================");
            System.out.println("1. Add Flight");
            System.out.println("2. Update Flight");
            System.out.println("3. Remove Flight");
            System.out.println("4. Search Flight");
            System.out.println("5. List All Flights");
            System.out.println("6. Return to Main Menu");
            System.out.println("====================");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH.mm");
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            in.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Provide Carrier Code :");
                    String carrierCode = in.nextLine();
                    System.out.println("Provide flight origin :");
                    String origin = in.nextLine();
                    System.out.println("Provide flight destination :");
                    String destination = in.nextLine();
                    System.out.println("Provide flight number :");
                    int flightNumber = in.nextInt();
                    in.nextLine();
                    System.out.println("Provide flight frequencies :");
                    List<DayOfWeek> frequencies = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY); // converts an array into List
                    in.nextLine();
                    System.out.println("Provide flight departureTime in format (HH.mm) :");
                    LocalTime departureTime = LocalTime.parse(in.nextLine(), formatterTime);
                    System.out.println("Provide flight arrivalTime in format (HH.mm)  :");
                    LocalTime arrivalTime = LocalTime.parse(in.nextLine(), formatterTime);
                    System.out.println("Provide flight effectStartDate in format (yyyy-MM-dd) :");
                    LocalDate effectStartDate = LocalDate.parse(in.nextLine(), formatDate);
                    System.out.println("Provide flight effectEndDate  in format (yyyy-MM-dd) :");
                    LocalDate effectEndDate = LocalDate.parse(in.nextLine(), formatDate);
                    System.out.println("Provide flight routeId :");
                    int routeId = in.nextInt();
                    Flight flight = new Flight(carrierCode, origin, destination, flightNumber, frequencies, departureTime, arrivalTime, effectStartDate, effectEndDate, routeId);
                    flightService.addFlight(flight);
                    break;
                case 2:
                    System.out.println("Provide Flight number to update");
                    int flightNumberToUpdate = in.nextInt();
                    in.nextLine();
                    Flight flightToUpdate = flightService.searchFlight(flightNumberToUpdate);
                    if(flightToUpdate != null) {
                        System.out.println("Provide new departure time for flight  in format (HH.mm) : " + flightNumberToUpdate);
                        LocalTime newDepartureTime = LocalTime.parse(in.nextLine(), formatterTime);
                        System.out.println("Provide new arrival time for flight in format (HH.mm) :" + flightNumberToUpdate);
                        LocalTime newArrivalTime = LocalTime.parse(in.nextLine(), formatterTime);
                        flightToUpdate.setDepartureTime(newDepartureTime);
                        flightToUpdate.setArrivalTime(newArrivalTime);
                        flightService.updateFlight(flightToUpdate);
                    } else {
                        System.out.println("Flight Number " + flightNumberToUpdate + " is not present in the system");
                    }
                    break;
                case 3:
                    System.out.println("Provide Flight number to remove");
                    int flightNumberToRemove = in.nextInt();
                    in.nextLine();
                    Flight flightToRemove = flightService.searchFlight(flightNumberToRemove);
                    if(flightToRemove != null) {
                        flightService.deleteFlight(flightNumberToRemove);
                    } else {
                        System.out.println("Flight Number " + flightNumberToRemove + " is not present in the system");
                    }
                    break;
                case 4:
                    System.out.println("Provide Flight number to Search");
                    int flightNumberToSearch = in.nextInt();
                    in.nextLine();
                    final Flight searchedFlight = flightService.searchFlight(flightNumberToSearch);
                    if(searchedFlight != null) {
                        System.out.println("Found Flight " + searchedFlight);
                    } else {
                        System.out.println("Flight Number " + flightNumberToSearch + " not found");
                    }
                    break;
                case 5:
                    flightService.displayAllFlight(flightService.getAllFlights());
                    break;
                case 6:
                    shouldRun = false;
                    break;
                default:
                    System.out.println("Invalid Input Provided for Flight Service Menu");
                    System.out.println("Please try again choosing option from the menu below");
            }
        }

    }
}
