
package com.tus.ca.flight.reservation.handlers;

import com.tus.ca.flight.reservation.enums.BookingClass;
import com.tus.ca.flight.reservation.model.FlightReservationContext;
import com.tus.ca.flight.reservation.model.PaymentMethod;
import com.tus.ca.flight.reservation.service.BookingService;
import com.tus.ca.flight.reservation.service.impl.BookingServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingHandler {

    BookingService bookingService;

    public BookingHandler(FlightReservationContext flightReservationContext){
        bookingService = new BookingServiceImpl(flightReservationContext);
    }

    public void bookingServiceMenu() {
        boolean shouldRun = true;
        while (shouldRun) {
            System.out.println("Flight Service Menu");
            System.out.println("====================");
            System.out.println("1. Search Route");
            System.out.println("2. Add Booking");
            System.out.println("3. Remove Booking");
            System.out.println("4. Update Booking");
            System.out.println("5. List All Bookings");
            System.out.println("6. Return to Main Menu");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        in.nextLine();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            switch (choice) {
                case 1 , 2:
                    System.out.println("Provide Route Origin :");
                    String origin = in.nextLine();
                    System.out.println("Provide Route Destination :");
                    String destination = in.nextLine();
                    System.out.println("Provide Journey StartDate in format (yyyy-MM-dd) :");
                    LocalDate dateOfJourney = LocalDate.parse(in.nextLine(), formatDate);
                    boolean routeFound = bookingService.searchRoutes(origin,destination,dateOfJourney);
                    if(routeFound && choice == 2){
                        System.out.println("Provide choose the flight for booking :");
                        String flightId = in.nextLine();
                        System.out.println("Provide no of passengers :");
                        int noOfPassengers = in.nextInt();
                        in.nextLine();
                        System.out.println("Provide booking class :");
                        BookingClass bookingClass = BookingClass.valueOf(in.nextLine());
                        var passengerIdList = new ArrayList<Integer>();  // Using LVTI , the compiler infer the type of the variable passengerIdList
                        for(int i=0;i<noOfPassengers;i++){
                            System.out.println("Provide passengerId for " + (i+1) + "passenger");
                            int passengerId = in.nextInt();
                            passengerIdList.add(passengerId);
                            in.nextLine();
                        }
                        System.out.println("Provide Payment Amount");
                        BigDecimal paymentAmount = in.nextBigDecimal();
                        in.nextLine();
                        System.out.println("Provide Payment Currency");
                        String paymentCurr = in.nextLine();
                        PaymentMethod paymentMethod = new PaymentMethod(String.valueOf(System.nanoTime()),paymentAmount,paymentCurr, LocalDateTime.now());
                        bookingService.makeBooking(origin,destination,dateOfJourney,noOfPassengers,passengerIdList,paymentMethod,bookingClass);
                    }
                    break;
                default:
                    System.out.println("Invalid Input Provided for Booking Service Menu");
                    System.out.println("Please try again choosing option from the menu below");
            }
        }
    }
}
