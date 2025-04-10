package com.tus.ca.ride.hailing.app;

import com.tus.ca.ride.hailing.enums.RideStatus;
import com.tus.ca.ride.hailing.exceptions.ExceptionHandler;
import com.tus.ca.ride.hailing.handler.RideAnalyzer;
import com.tus.ca.ride.hailing.model.Driver;
import com.tus.ca.ride.hailing.service.Ride;
import com.tus.ca.ride.hailing.service.RideCompletion;
import com.tus.ca.ride.hailing.service.RideRequest;
import com.tus.ca.ride.hailing.util.DirectoryUtility;
import com.tus.ca.ride.hailing.util.ExceptionUtility;
import com.tus.ca.ride.hailing.util.FileUtility;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

    /**
     * Main class for the Ride Hailing application.
     * Simulates ride requests, ride completions, and analyzes rides using Stream APIs.
     */
    public class Main {
        // Define a DateTimeFormatter for consistent formatting
        private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        private final Locale userLocale = Locale.getDefault(); // Get user's locale
        private ResourceBundle messages = ResourceBundle.getBundle("resources/messages", Locale.forLanguageTag("fr"));
        private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        private final ExceptionHandler exceptionHandler = new ExceptionHandler();
        protected Path logPath = Path.of("/var/opt/data/logs"); // Change this path as needed
        private List<Ride> rides;
        private List<Driver> drivers;
        private RideAnalyzer rideAnalyzer;

        public Main() {
            // Initialize rides and drivers
            this.rides = new ArrayList<>();
            this.drivers = FileUtility.readDriversFromFile("drivers.txt");
            this.rideAnalyzer = new RideAnalyzer(rides, drivers);
        }

        public static void main(String[] args) {
            new Main().run();
        }

        /**
         * Entry point to run the application.
         */
        public void run() {

            // Validate driver IDs using Predicate
            Predicate<String> isDriverValid = driverId -> drivers.stream()
                    .anyMatch(driver -> driver.driverId().equals(driverId));

            // Suppliers to generate ride requests and completions dynamically
            Supplier<RideRequest> createRideRequest = () -> new RideRequest("R1", "U1", "D1", LocalDateTime.now(), RideStatus.REQUESTED);
            Supplier<RideCompletion> createRideCompletion = () -> new RideCompletion("R1", "U1", "D1", LocalDateTime.now(), 25.0, RideStatus.COMPLETED);


            // Consumer to process and validate rides
            Consumer<Ride> processRide = ride -> {
                if (!isDriverValid.test(ride.driverId())) {
                    throw new IllegalArgumentException("Invalid driverId: " + ride.driverId());
                }
                rides.add(ride);
                System.out.println("Processed ride: " + ride);
            };

            // Tasks for concurrent ride processing
            List<Callable<Void>> tasks = List.of(
                    () -> {
                        exceptionHandler.executeSafely(() -> processRide.accept(createRideRequest.get()), "RideRequest R1", ExceptionUtility::logException);
                        return null;
                    },
                    () -> {
                        exceptionHandler.executeSafely(() -> processRide.accept(createRideCompletion.get()), "RideCompletion R1", ExceptionUtility::logException);
                        return null;
                    }
            );

            // Execute tasks and shut down executor
            executeTasks(tasks);

            // Analyze rides after processing
            System.out.println("Message: " + messages.getString("ride.analysis.start"));
            analyzeRides();

            // Write ride data to a file
            FileUtility.writeRidesToFile("ride_analysis.txt",rides);

            // list and watch a directory
            DirectoryUtility utility = new DirectoryUtility();
            utility.listFilesInDirectory(".");
            utility.watchDirectory(".");

        }

        /**
         * Executes tasks concurrently using the ExecutorService.
         *
         * @param tasks List of tasks to execute
         */
        private void executeTasks(List<Callable<Void>> tasks) {
            try {
                executor.invokeAll(tasks);
            } catch (Exception  e) {
                switch (e) {
                    case InterruptedException _ -> {
                        exceptionHandler.handleException(ExceptionUtility::logException, e);
                        Thread.currentThread().interrupt();
                    }
                    default -> ExceptionUtility.logException(e);
                }
            } finally {
                shutdownExecutor();
            }
        }

        /**
         * Gracefully shuts down the ExecutorService.
         */
        private void shutdownExecutor() {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                    System.err.println("Forcing ExecutorService shutdown...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                ExceptionUtility.logException(e);
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        /**
         * Analyzes and summarizes ride data using Stream APIs.
         */
        private void analyzeRides() {
            // Simulate ride processing
            LocalDateTime startDate = LocalDateTime.now().minusDays(1);
            LocalDateTime endDate = LocalDateTime.now();
            rideAnalyzer.displayFormattedRideTimes();
            rideAnalyzer.groupRidesByDriver();
            rideAnalyzer.partitionRides();
            rideAnalyzer.findRideWithHighestFare();
            rideAnalyzer.findRideWithLowestFare();
            rideAnalyzer.countTotalRideRequests();
            rideAnalyzer.findAnyRide();
            rideAnalyzer.findFirstRide();
            rideAnalyzer.validateRides();
            rideAnalyzer.checkAnyRideByDriver("D1");
            rideAnalyzer.checkNoRideByDriver("D99");
            rideAnalyzer.listAllRideTimes();
            rideAnalyzer.mapDriverIdsToTotalRides();
            rideAnalyzer.filterDistinctDriverIds();
            rideAnalyzer.listFirstThreeRides();
            rideAnalyzer.sortRidesByTime();
            rideAnalyzer.calculateTimeBetweenRides();
            rideAnalyzer.filterRidesByDateRange(startDate, endDate); // Filter rides within the last day
            rideAnalyzer.displayRideTimesInTimeZone("Europe/Dublin");

        }

    }