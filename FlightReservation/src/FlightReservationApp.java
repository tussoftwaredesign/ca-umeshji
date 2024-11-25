import com.tus.ca.flight.reservation.handlers.BookingHandler;
import com.tus.ca.flight.reservation.handlers.FlightHandler;
import com.tus.ca.flight.reservation.handlers.PassengerHandler;
import com.tus.ca.flight.reservation.model.FlightReservationContext;

import java.util.Scanner;

public class FlightReservationApp {

    FlightHandler flightHandler;

    PassengerHandler passengerHandler;

    BookingHandler bookingHandler;

    FlightReservationContext flightReservationContext;
    public FlightReservationApp() {
        flightReservationContext = new FlightReservationContext();
        flightHandler = new FlightHandler(flightReservationContext);
        passengerHandler = new PassengerHandler(flightReservationContext);
        bookingHandler = new BookingHandler(flightReservationContext);
    }

    public static void main(String[] args) {
        FlightReservationApp app = new FlightReservationApp();
        app.startProcessing();
    }

    public void startProcessing() {
        System.out.println("Flight Reservation App");
        boolean shouldAbort  = false;
        while(!shouldAbort) {
            System.out.println("      Main Menu     ");
            System.out.println("====================");
            System.out.println("1. Schedule Flight Service");
            System.out.println("2. Passenger Service");
            System.out.println("3. Booking Service");
            System.out.println("4. Exit Application");
            System.out.println("====================");
            System.out.print("Enter your choice: ");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    flightHandler.flightServiceMenu();
                    break;
                case 2:
                    passengerHandler.passengerServiceMenu();
                    break;
                case 3:
                    bookingHandler.bookingServiceMenu();
                    break;
                case 4:
                    System.out.print("Good Bye! Exiting the app");
                    shouldAbort = true;
                    break;
                default :
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
}