
package com.tus.ca.flight.reservation.handlers;

import com.tus.ca.flight.reservation.enums.BookingClass;
import com.tus.ca.flight.reservation.model.AppContext;
import com.tus.ca.flight.reservation.model.Booking;
import com.tus.ca.flight.reservation.model.Invoice;
import com.tus.ca.flight.reservation.model.Passenger;
import com.tus.ca.flight.reservation.service.BookingService;
import com.tus.ca.flight.reservation.service.BookingServiceImpl;
import com.tus.ca.flight.reservation.service.CreditCardPayment;
import com.tus.ca.flight.reservation.service.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingHandler {

    BookingService bookingService;

    AppContext appContext;

    public BookingHandler(AppContext appContext){
        bookingService = new BookingServiceImpl(appContext);
        this.appContext = appContext;
    }

    public void bookingServiceMenu() {
        boolean shouldRun = true;
        while (shouldRun) {
            System.out.println("Flight Service Menu");
            System.out.println("====================");
            System.out.println("1. Search Route");
            System.out.println("2. Add Booking");
            System.out.println("3. Remove Booking");
            System.out.println("4. List All Bookings");
            System.out.println("5. Return to Main Menu");

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
                    boolean routeFound = bookingService.searchRoutes(origin, destination, dateOfJourney);
                    if(routeFound && choice == 2) {
                        System.out.println("Provide choose the flight for booking :");
                        int flightId = in.nextInt();
                        in.nextLine();
                        System.out.println("Provide no of passengers :");
                        int noOfPassengers = in.nextInt();
                        in.nextLine();
                        System.out.println("Provide booking class :");
                        BookingClass bookingClass = BookingClass.valueOf(in.nextLine());
                        var passengerIdList = new ArrayList<Integer>();  // Using LVTI , the compiler infer the type of the variable passengerIdList
                        for(int i=0;i<noOfPassengers;i++){
                            System.out.println("Provide passengerId for " + (i+1) + " passenger");
                            int passengerId = in.nextInt();
                            passengerIdList.add(passengerId);
                            in.nextLine();
                        }
                        System.out.println("Provide Payment Amount");
                        BigDecimal paymentAmount = in.nextBigDecimal();
                        in.nextLine();
                        System.out.println("Provide Payment Currency");
                        String paymentCurr = in.nextLine();
                        Payment creditCardPayment = new CreditCardPayment();
                        PaymentHandler paymentMethod = new PaymentHandler(String.valueOf(System.nanoTime()),paymentAmount,paymentCurr, LocalDateTime.now(),creditCardPayment);
                        paymentMethod.makePayment();
                        String bookingId = bookingService.makeBooking(flightId,origin,destination,dateOfJourney,noOfPassengers,passengerIdList,paymentMethod,bookingClass);
                        Invoice invoice = generateInvoice(paymentAmount,noOfPassengers,passengerIdList);
                        System.out.println("Booking confirmed with bookingId :: " + bookingId);
                        System.out.println("Invoice :: " + invoice.toString());
                    }
                    break;
                case 3: System.out.println("Provide BookingId you wish to remove");
                        String bookingId =  in.nextLine();
                        bookingService.cancelBooking(bookingId);
                case 4:
                        List<Booking> bookingsList = bookingService.listAllBookings();
                        if(bookingsList.isEmpty()) {
                            System.out.println("No Bookings Found");
                        } else {
                            bookingsList.stream().forEach(System.out::println);
                        }
                        break;
                default:
                    StringBuilder errorMsg = new StringBuilder();
                    errorMsg.append("Invalid Input Provided for Booking Service Menu");
                    errorMsg.append("Please try again choosing option from the menu below"); //StringBuilder feature example
                    System.out.println(errorMsg);
            }
        }
    }

    public Invoice generateInvoice(BigDecimal paymentAmount,Integer noOfPassenger,List<Integer> passengerIdList) {
        String invoiceId = generateUniqueInvoiceId();
        String passengerName = derivePassengerName(passengerIdList.get(0));
        Invoice.InvoiceItem invoiceItem = new Invoice.InvoiceItem(noOfPassenger,paymentAmount.divide(BigDecimal.valueOf(noOfPassenger)));
        return new Invoice(invoiceId,LocalDate.now(),passengerName,paymentAmount,List.of(invoiceItem));
    }

    private String generateUniqueInvoiceId() {
        long timestamp = System.currentTimeMillis();
        int randomNum = new Random().nextInt(1000);
        return  "Invoice-" + timestamp + "-" + randomNum;
    }


    public String derivePassengerName(Integer passengerId) {
        final List<Passenger> passengers = appContext.getList("passengers", Passenger.class);
        Passenger passenger = passengers.stream()
                .filter(p -> p.getPassengerId().equals(passengerId))
                .findFirst()
                .orElse(null);
        return passenger!= null ? passenger.getPassengerName():"";
    }
}
