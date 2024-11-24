package com.tus.ca.flight.reservation.service;

import com.tus.ca.flight.reservation.model.Flight;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public interface FlightService {

    public void addFlight(Flight flight);

    public void deleteFlight(Integer flightNumber);

    public void updateFlight(Flight flight);

    public Flight searchFlight(int flightNumber);


    public static void displayFlight(List<Flight> flightList ) {
        flightList.stream().forEach(System.out::println);
    }

    public default void flightMenu() {
        System.out.println("Flight Service ");
        boolean shouldRun = true;
        while (shouldRun) {
            System.out.println("1. Add Flight");
            System.out.println("2. Update Flight");
            System.out.println("3. Remove Flight");
            System.out.println("4. Return to Main Screen");
            Scanner scanner = new Scanner(System.in);
            int selectedOption = scanner.nextInt();
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH.mm");
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            scanner.nextLine();
            switch (selectedOption) {
                case 1:
                    //Flight flight = new Flight("AA","New York","London",103, Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.THURSDAY), LocalTime.of(13,30), LocalTime.of(15,30), LocalDate.of(2024, 2, 1),LocalDate.of(2024, 12, 31));
                    System.out.println("Provide Carrier Code :");
                    String carrierCode = scanner.nextLine();
                    System.out.println("Provide flight origin :");
                    String origin = scanner.nextLine();
                    System.out.println("Provide flight destination :");
                    String destination = scanner.nextLine();
                    System.out.println("Provide flight number :");
                    Integer flightNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Provide flight frequencies :");
                    List<DayOfWeek> frequencies = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
                    scanner.nextLine();
                    System.out.println("Provide flight departureTime :");
                    LocalTime departureTime = LocalTime.parse(scanner.nextLine(), formatterTime);
                    System.out.println("Provide flight arrivalTime :");
                    LocalTime arrivalTime = LocalTime.parse(scanner.nextLine(), formatterTime);
                    System.out.println("Provide flight effectStartDate :");
                    LocalDate effectStartDate = LocalDate.parse(scanner.nextLine(), formatDate);
                    System.out.println("Provide flight effectEndDate :");
                    LocalDate effectEndDate = LocalDate.parse(scanner.nextLine(), formatDate);
                    Flight flight = new Flight(carrierCode, origin, destination, flightNumber, frequencies, departureTime, arrivalTime, effectStartDate, effectEndDate);
                    addFlight(flight);
                    break;
                case 4:
                    shouldRun = false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedOption);
            }
        }

    }

}
