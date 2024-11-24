import com.tus.ca.flight.reservation.service.FlightService;
import com.tus.ca.flight.reservation.service.impl.FlightServiceImpl;

import java.util.Scanner;

public class FlightReservationApp {

    FlightService flightService;

    public FlightReservationApp() {
        flightService = new FlightServiceImpl();
    }

    public static void main(String[] args) {
        FlightReservationApp app = new FlightReservationApp();
        app.startProcessing();
    }

    public void startProcessing() {
        System.out.println("Welcome to Flight Reservation App");
        System.out.println("MENU");
        while(true) {
            System.out.println("1. Flight");
            System.out.println("2. Passenger");
            System.out.println("3. Schedules");
            System.out.println("4. Exit Application");
            System.out.print("Enter your selection : ");
            Scanner scanner = new Scanner(System.in);
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    flightService.flightMenu();
                    break;
                case 4:
                    System.out.print("Exiting the application");
                    System.exit(1);
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedOption);
            }
        }
    }
}